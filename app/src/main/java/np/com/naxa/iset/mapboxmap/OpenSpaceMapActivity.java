package np.com.naxa.iset.mapboxmap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import np.com.naxa.iset.R;
import np.com.naxa.iset.mapboxmap.mapboxutils.DrawGeoJsonOnMap;
import np.com.naxa.iset.mapboxmap.openspace.MapCategoryListAdapter;
import np.com.naxa.iset.mapboxmap.openspace.MapCategoryModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// classes needed to add a marker
// classes to calculate a route

public class OpenSpaceMapActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener, MapboxMap.OnMapClickListener,
        LocationListener {

    private static final String TAG = "DemoActivity";
    @BindView(R.id.toolbar_general)
    Toolbar toolbarGeneral;
    @BindView(R.id.toggleButton)
    ToggleButton toggleButton;
    @BindView(R.id.point)
    Button btnPoint;
    @BindView(R.id.multipolygon)
    Button btnMultipolygon;
    @BindView(R.id.multiLineString)
    Button btnMultiLineString;
    @BindView(R.id.navigation)
    Button navigation;
    @BindView(R.id.floating_search_map_category)
    FloatingSearchView floatingSearchMapCategory;
    @BindView(R.id.recyclerViewMapCategory)
    RecyclerView recyclerViewMapCategory;

    @BindView(R.id.iv_sliding_layout_indicator)
    ImageView ivSlidingLayoutIndicator;

    private SlidingUpPanelLayout mLayout;
    private PermissionsManager permissionsManager;
    private MapView mapView;
    private MapboxMap mapboxMap;

    String filename = "";

    // variables for adding a marker
    private Marker destinationMarker;
    private LatLng originCoord;
    private LatLng destinationCoord;

    private Location originLocation;

    // variables for calculating and drawing a route
    private Point originPosition;
    private Point destinationPosition;
    private DirectionsRoute currentRoute;
    private NavigationMapRoute navigationMapRoute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Mapbox.getInstance(this, getResources().getString(R.string.access_token));
        Mapbox.getInstance(this, "pk.eyJ1Ijoic2FtaXJkYW5nYWwiLCJhIjoiY2pwZHNjaXNpMDJrNjNxbWFlaDZobnZ1MyJ9.ASQwLRwoQeTp3PkVqHh2hw");
        setContentView(R.layout.activity_open_space_map);
        ButterKnife.bind(this);

        mapView = (MapView) findViewById(R.id.mapView);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setChecked(true);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        setupToolBar();
        setupBottomSlidingPanel();
        setupListRecycler();


    }

    private void setupToolBar() {
        setSupportActionBar(toolbarGeneral);
        getSupportActionBar().setTitle("Open Spaces");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    private void setupBottomSlidingPanel() {
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);

                switch (newState.toString()){

                    case "COLLAPSED":
                        ivSlidingLayoutIndicator.setBackground(getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_white_24dp));
                        break;

                    case "DRAGGING":
                        ivSlidingLayoutIndicator.setBackground(getResources().getDrawable(R.drawable.ic_sliding_neutral_white_24dp));
                        break;

                    case "EXPANDED":
                        ivSlidingLayoutIndicator.setBackground(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_white_24dp));
                        break;
                }

            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

    }

    private void setupListRecycler() {
        MapCategoryListAdapter mapCategoryListAdapter = new MapCategoryListAdapter(R.layout.openspace_map_category_list_item_row_layout, null);
        recyclerViewMapCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewMapCategory.setAdapter(mapCategoryListAdapter);

        loadDataToList();
    }

    private void loadDataToList() {

        ArrayList<MapCategoryModel> mapCategoryModelArrayList = new ArrayList<MapCategoryModel>();

        mapCategoryModelArrayList.add(new MapCategoryModel("Hospital", getResources().getDrawable(R.drawable.ic_hospital)));
        mapCategoryModelArrayList.add(new MapCategoryModel("College", getResources().getDrawable(R.drawable.ic_college)));
        mapCategoryModelArrayList.add(new MapCategoryModel("Gas Station", getResources().getDrawable(R.drawable.ic_gas_station)));
        mapCategoryModelArrayList.add(new MapCategoryModel("Bus Station", getResources().getDrawable(R.drawable.ic_bus_station)));
        mapCategoryModelArrayList.add(new MapCategoryModel("Restaurant", getResources().getDrawable(R.drawable.ic_restaurant)));
        mapCategoryModelArrayList.add(new MapCategoryModel("Airport", getResources().getDrawable(R.drawable.ic_airport)));
        ((MapCategoryListAdapter) recyclerViewMapCategory.getAdapter()).replaceData(mapCategoryModelArrayList);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.7f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_disable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_enable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onMapReady(MapboxMap mapboxMap) {

        this.mapboxMap = mapboxMap;

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(27.657531140175244, 85.46161651611328)) // Sets the new camera position
                .zoom(11) // Sets the zoom
                .bearing(0) // Rotate the camera
                .tilt(30) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder

        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);

        enableLocationComponent();

//        originCoord = new LatLng(originLocation.getLatitude(), originLocation.getLongitude());
        mapboxMap.addOnMapClickListener(this);


        //show toast on click of show all button
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Is the toggle on?
                boolean on = ((ToggleButton) v).isChecked();

                if (on) {
                    // Enable
                    mapView.setStyleUrl(getResources().getString(R.string.mapbox_style_mapbox_streets));

                    enableLocationComponent();
                    // Load and Draw the GeoJSON
                    animateCameraPosition();


                    btnPoint.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnMultiLineString.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnMultipolygon.setTextColor(getResources().getColor(R.color.colorPrimary));

                } else {
                    // Disable
                    mapView.setStyleUrl(getResources().getString(R.string.mapbox_style_satellite));
                    enableLocationComponent();

                    animateCameraPosition();

                    btnPoint.setTextColor(getResources().getColor(R.color.colorAccent));
                    btnMultiLineString.setTextColor(getResources().getColor(R.color.colorAccent));
                    btnMultipolygon.setTextColor(getResources().getColor(R.color.colorAccent));

                    // Load and Draw the GeoJSON
                }
            }
        });
    }


    @Override
    public void onMapClick(@NonNull LatLng point) {
        if (destinationMarker != null) {
            mapboxMap.removeMarker(destinationMarker);
        }
        destinationCoord = point;
        destinationMarker = mapboxMap.addMarker(new MarkerOptions()
                .position(destinationCoord)
        );


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        originCoord = new LatLng(originLocation.getLatitude(), originLocation.getLongitude());
        originLocation = mapboxMap.getLocationComponent().getLocationEngine().getLastLocation();

        destinationPosition = Point.fromLngLat(destinationCoord.getLongitude(), destinationCoord.getLatitude());
        originPosition = Point.fromLngLat(originCoord.getLongitude(), originCoord.getLatitude());
        getRoute(originPosition, destinationPosition);


    }


    private void getRoute(Point origin, Point destination) {
        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        // You can get the generic HTTP info about the response
                        Log.d(TAG, "Response code: " + response.code());
                        if (response.body() == null) {
                            Log.e(TAG, "No routes found, make sure you set the right user and access token.");
                            return;
                        } else if (response.body().routes().size() < 1) {
                            Log.e(TAG, "No routes found");
                            return;
                        }

                        currentRoute = response.body().routes().get(0);

                        // Draw the route on the map
                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap, R.style.NavigationMapRoute);
                        }
                        navigationMapRoute.addRoute(currentRoute);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage());
                    }
                });
    }


    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent() {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            LocationComponentOptions options = LocationComponentOptions.builder(this)
                    .trackingGesturesManagement(true)
                    .accuracyColor(ContextCompat.getColor(this, R.color.colorAccent))
                    .build();

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Activate with options
            locationComponent.activateLocationComponent(this, options);

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);


            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.COMPASS);


//            originLocation = locationComponent.getLocationEngine().getLastLocation();

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "You need to provide location permission to show your current location", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();


    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    private void animateCameraPosition() {
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(27.7033, 85.4324)) // Sets the new camera position
                .zoom(15) // Sets the zoom
                .bearing(180) // Rotate the camera
//                .tilt(30) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder

        Log.d("MapBox", "animateCameraPosition: ");


        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 3000);
    }


    ArrayList<LatLng> points = null;

    int count = 0;
    boolean point = false;

    @OnClick({R.id.point, R.id.multipolygon, R.id.multiLineString, R.id.navigation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.point:
                filename = "educational_Institution_geojson.geojson";
                count++;
                point = true;
                DrawGeoJson drawGeoJson = new DrawGeoJson();
                drawGeoJson.execute();

                break;
            case R.id.multipolygon:
                filename = "wards.geojson";
                count++;
                point = false;

                DrawGeoJsonOnMap drawGeoJsonOnMap = new DrawGeoJsonOnMap();
                drawGeoJsonOnMap.readAndDrawOnMapFromGeoJsonFile(OpenSpaceMapActivity.this, mapboxMap, mapView, filename, point, count);

                DrawGeoJson drawGeoJson1 = new DrawGeoJson();
                drawGeoJson1.execute();

                break;
            case R.id.multiLineString:

                filename = "road_network.geojson";
                count++;
                point = false;
                DrawGeoJson drawGeoJson2 = new DrawGeoJson();
                drawGeoJson2.execute();
                break;

            case R.id.navigation:
                boolean simulateRoute = true;
                NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                        .directionsRoute(currentRoute)
                        .shouldSimulateRoute(simulateRoute)
                        .build();
                // Call this method with Context from within an Activity
                NavigationLauncher.startNavigation(OpenSpaceMapActivity.this, options);


                navigation.setEnabled(true);
                navigation.setBackgroundResource(R.color.mapbox_blue);


                break;
        }
    }


    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected boolean gps_enabled, network_enabled;

    @Override
    public void onLocationChanged(Location location) {
        originLocation = location;

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private class DrawGeoJson extends AsyncTask<Void, Void, List<LatLng>> {
        @Override
        protected List<LatLng> doInBackground(Void... voids) {

            try {
                // Load GeoJSON file
                InputStream inputStream = getAssets().open(filename);
                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                StringBuilder sb = new StringBuilder();
                int cp;
                while ((cp = rd.read()) != -1) {
                    sb.append((char) cp);
                }

                inputStream.close();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        GeoJsonSource source = new GeoJsonSource("geojson", sb.toString());

                        if (count == 1) {
                            mapboxMap.addSource(source);
                        }
                        if (count > 1) {
                            if (!point) {
                                mapboxMap.clear();
                            }
                            mapboxMap.removeLayer("geojson");
                            mapboxMap.removeSource("geojson");
                            mapboxMap.addSource(source);

                        }

                        LineLayer lineLayer = new LineLayer("geojson", "geojson");
                        lineLayer.setProperties(
                                PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                                PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                                PropertyFactory.lineWidth(2f),
                                PropertyFactory.lineColor(getResources().getColor(R.color.colorAccent))
                        );
                        mapboxMap.addLayer(lineLayer);
                    }
                });


                // Parse JSON
                JSONObject json = new JSONObject(sb.toString());
                JSONArray features = json.getJSONArray("features");
                points = new ArrayList<>();
                for (int i = 0; i < features.length(); i++) {
                    JSONObject feature = features.getJSONObject(i);

                    JSONObject geometry = feature.getJSONObject("geometry");
                    if (geometry != null) {
                        String type = geometry.getString("type");

                        // Our GeoJSON only has one feature: a line string
                        if (!TextUtils.isEmpty(type) && type.equalsIgnoreCase("Point")) {

                            // Get the Coordinates
                            JSONArray coords = geometry.getJSONArray("coordinates");

//                            for (int lc = 0; lc < coords.length(); lc++) {
                            LatLng latLng = new LatLng(coords.getDouble(1), coords.getDouble(0));
                            points.add(latLng);
//                            }
                        }
                    }

                }
            } catch (Exception exception) {
                Log.e("MAPBOX", "Exception Loading GeoJSON: " + exception.toString());
            }

            return points;
        }

        @Override
        protected void onPostExecute(List<LatLng> points) {
            super.onPostExecute(points);

            if (points.size() > 0) {

                // Draw polyline on map
//                mapboxMap.addPolyline(new PolylineOptions()
//                        .addAll(points)
//                        .color(Color.parseColor("#3bb2d0"))
//                        .width(2));

                if (point) {
                    for (int i = 0; i < points.size(); i++) {
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(points.get(i)))
                                .title("marker Title")
                                .snippet("Marker Snippet"));
                    }
                }

            }
        }
    }

}