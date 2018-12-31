package np.com.naxa.iset.mapboxmap.mapboxutils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.Polygon;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                PropertyFactory.lineColor(context.getResources().getColor(R.color.colorAccent))
        );
        mapboxMap.addLayer(lineLayer);

        if (point) {
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
                            // Parse JSON
                            JSONObject json = new JSONObject(stringBuilder.toString());
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

                                        LatLng latLng = new LatLng(coords.getDouble(1), coords.getDouble(0));
                                        points.add(latLng);
//                            }
                                    }
                                }

                            }
                        } catch (Exception exception) {
                            Log.e("MAPBOX", "Exception Loading GeoJSON: " + exception.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (points.size() > 0) {
                            for (int i = 0; i < points.size(); i++) {
                                mapboxMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(points.get(i)))
                                        .title("marker Title")
                                        .snippet("Marker Snippet"));
                            }


                            mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
                                    Toast.makeText(context, marker.getTitle(), Toast.LENGTH_LONG).show();
                                    return true;
                                }
                            });
                        }
                    }
                });


    }


}
