package np.com.naxa.iset.mapboxmap.mapboxutils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.Polygon;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.expressions.Expression;
import com.mapbox.mapboxsdk.style.layers.CircleLayer;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonOptions;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Observable;
import java.util.stream.Stream;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import np.com.naxa.iset.R;

import static com.mapbox.mapboxsdk.style.expressions.Expression.all;
import static com.mapbox.mapboxsdk.style.expressions.Expression.division;
import static com.mapbox.mapboxsdk.style.expressions.Expression.exponential;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.gt;
import static com.mapbox.mapboxsdk.style.expressions.Expression.gte;
import static com.mapbox.mapboxsdk.style.expressions.Expression.has;
import static com.mapbox.mapboxsdk.style.expressions.Expression.interpolate;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.expressions.Expression.lt;
import static com.mapbox.mapboxsdk.style.expressions.Expression.rgb;
import static com.mapbox.mapboxsdk.style.expressions.Expression.stop;
import static com.mapbox.mapboxsdk.style.expressions.Expression.toNumber;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.circleColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.circleRadius;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconSize;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textSize;

public class DrawGeoJsonOnMap {

    String TAG = "DrawGeoJsonOnMap";
    String filename = "";
    Context context;
    MapboxMap mapboxMap;
    MapView mapView;
    ArrayList<LatLng> points = null;
    StringBuilder geoJsonString;


    public DrawGeoJsonOnMap(Context context, MapboxMap mapboxMap, MapView mapView) {
        this.context = context;
        this.mapboxMap = mapboxMap;
        this.mapView = mapView;
    }


    public void readAndDrawGeoSonFileOnMap(String geoJsonFileName, Boolean point, int count) {
        io.reactivex.Observable.just(geoJsonFileName)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String filename) {

                        // Load GeoJSON file
                        try {
                            InputStream inputStream = context.getAssets().open(filename);
                            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                            StringBuilder sb = new StringBuilder();
                            int cp;
                            while ((cp = rd.read()) != -1) {
                                sb.append((char) cp);
                            }
                            geoJsonString = sb;

                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        drawGeoJsonOnMap(geoJsonString, point, count);
                    }
                });
    }

    private void drawGeoJsonOnMap(StringBuilder geoJsonString, Boolean point, int count) {
        GeoJsonSource source = new GeoJsonSource("geojson", geoJsonString.toString());
        String type = "";
        if (count == 1) {
            mapboxMap.addSource(source);
        }
        if (count > 1) {
            try {

                JSONObject json = new JSONObject(geoJsonString.toString());
                JSONArray features = json.getJSONArray("features");
                JSONObject geometry = (features.getJSONObject(0)).getJSONObject("geometry");
                if (geometry != null) {
                    type = geometry.getString("type");
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            if (type.equals("Point") || type.equals("point") || type.equals("POINT")) {
                mapboxMap.clear();
            }
            GeoJsonSource source1 = new GeoJsonSource("geojson"+count, geoJsonString.toString());

//            mapboxMap.removeLayer("geojson");
//            mapboxMap.removeSource("geojson");
            mapboxMap.addSource(source1);

        }
        if(count == 0){
            LineLayer lineLayer = new LineLayer("geojson", "geojson");
            lineLayer.setProperties(
                    PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                    PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                    PropertyFactory.lineWidth(2f),
                    PropertyFactory.lineColor(context.getResources().getColor(R.color.colorAccent))
            );
            mapboxMap.addLayer(lineLayer);
        }
        if (count>1){
            LineLayer lineLayer = new LineLayer("geojson"+count, "geojson"+count);
            lineLayer.setProperties(
                    PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                    PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                    PropertyFactory.lineWidth(2f),
                    PropertyFactory.lineColor(context.getResources().getColor(R.color.colorAccent))
            );
            mapboxMap.addLayer(lineLayer);
        }



        if (type.equals("Point") || type.equals("point") || type.equals("POINT")) {
            plotMarkerOnMap(geoJsonString);
        }else {
            mapboxMap.setOnPolygonClickListener(new MapboxMap.OnPolygonClickListener() {
                @Override
                public void onPolygonClick(@NonNull Polygon polygon) {
                    Toast.makeText(context, (int) polygon.getId(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    private void plotMarkerOnMap(StringBuilder geoJsonString) {
        io.reactivex.Observable.just(geoJsonString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StringBuilder>() {
                    @Override
                    public void onNext(StringBuilder stringBuilder) {

                        try {
//                            // Parse JSON
//                            JSONObject json = new JSONObject(stringBuilder.toString());
//                            JSONArray features = json.getJSONArray("features");
//                            points = new ArrayList<>();
//                            for (int i = 0; i < features.length(); i++) {
//                                JSONObject feature = features.getJSONObject(i);
//
//                                JSONObject geometry = feature.getJSONObject("geometry");
//                                if (geometry != null) {
//                                    String type = geometry.getString("type");
//
//                                    // Our GeoJSON only has one feature: a line string
//                                    if (!TextUtils.isEmpty(type) && type.equalsIgnoreCase("Point")) {
//
//                                        // Get the Coordinates
//                                        JSONArray coords = geometry.getJSONArray("coordinates");
//
//                                        LatLng latLng = new LatLng(coords.getDouble(1), coords.getDouble(0));
//                                        points.add(latLng);
////                            }
//                                    }
//                                }
//
//                            }

                            addClusteredGeoJsonSource(stringBuilder.toString());
                            mapboxMap.addImage("cross-icon-id", BitmapUtils.getBitmapFromDrawable(
                                    context.getResources().getDrawable(R.drawable.ic_marker_hospital)));
                        } catch (Exception exception) {
                            Log.e("MAPBOX", "Exception Loading GeoJSON: " + exception.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
//                        if (points.size() > 0) {
//                            for (int i = 0; i < points.size(); i++) {
//                                mapboxMap.addMarker(new MarkerOptions()
//                                        .position(new LatLng(points.get(i)))
//                                        .title("marker Title")
//                                        .snippet("Marker Snippet"));
//                            }
//
//
                            mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
                                    Toast.makeText(context, marker.getTitle(), Toast.LENGTH_LONG).show();
                                    return true;
                                }
                            });



//                        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
//                                12.099, -79.045), 3));
//
//                        addClusteredGeoJsonSource();
//                        mapboxMap.addImage("cross-icon-id", BitmapUtils.getBitmapFromDrawable(
//                                context.getResources().getDrawable(R.drawable.ic_marker_hospital)));
                    }
                });


    }


    private void addClusteredGeoJsonSource(String geoJson) {

// Add a new source from the GeoJSON data and set the 'cluster' option to true.
        try {
            mapboxMap.addSource(
// Point to GeoJSON data. This example visualizes all M1.0+ geojson
                    new GeoJsonSource("earthquakes",
                            new String(geoJson),
                            new GeoJsonOptions()
                                    .withCluster(true)
                                    .withClusterMaxZoom(14)
                                    .withClusterRadius(50)
                    )
            );
        } catch (NullPointerException malformedUrlException) {
            Log.e("dataClusterActivity", "Check the URL " + malformedUrlException.getMessage());
        }


// Use the earthquakes GeoJSON source to create three layers: One layer for each cluster category.
// Each point range gets a different fill color.
        int[][] layers = new int[][] {
                new int[] {150, ContextCompat.getColor(context, R.color.mapboxRed)},
                new int[] {20, ContextCompat.getColor(context, R.color.mapboxYellow)},
                new int[] {0, ContextCompat.getColor(context, R.color.mapbox_blue)}
        };

//Creating a marker layer for single data points
        SymbolLayer unclustered = new SymbolLayer("unclustered-points", "earthquakes");

        unclustered.setProperties(
                iconImage("cross-icon-id"),
                iconSize(
                        division(
                                get("mag"), literal(4.0f)
                        )
                ),
                iconColor(
                        interpolate(exponential(1), get("mag"),
                                stop(2.0, rgb(0, 255, 0)),
                                stop(4.5, rgb(0, 0, 255)),
                                stop(7.0, rgb(255, 0, 0))
                        )
                )
        );
        mapboxMap.addLayer(unclustered);

        for (int i = 0; i < layers.length; i++) {
//Add clusters' circles
            CircleLayer circles = new CircleLayer("cluster-" + i, "earthquakes");
            circles.setProperties(
                    circleColor(layers[i][1]),
                    circleRadius(18f)
            );


            Expression pointCount = toNumber(get("point_count"));

// Add a filter to the cluster layer that hides the circles based on "point_count"
            circles.setFilter(
                    i == 0
                            ? all(has("point_count"),
                            gte(pointCount, literal(layers[i][0]))
                    ) : all(has("point_count"),
                            gt(pointCount, literal(layers[i][0])),
                            lt(pointCount, literal(layers[i - 1][0]))
                    )
            );
            mapboxMap.addLayer(circles);
        }

//Add the count labels
        SymbolLayer count = new SymbolLayer("count", "earthquakes");
        count.setProperties(
                textField(Expression.toString(get("point_count"))),
                textSize(12f),
                textColor(Color.WHITE),
                textIgnorePlacement(true),
                textAllowOverlap(true)
        );
        mapboxMap.addLayer(count);

    }


}
