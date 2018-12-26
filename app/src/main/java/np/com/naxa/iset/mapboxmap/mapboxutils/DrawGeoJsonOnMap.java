package np.com.naxa.iset.mapboxmap.mapboxutils;

import android.content.Context;
import android.util.Log;

import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import org.reactivestreams.Subscriber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Observable;
import java.util.stream.Stream;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class DrawGeoJsonOnMap {

    String TAG = "DrawGeoJsonOnMap";
    String filename = "" ;
    Context context;

    public void readAndDrawOnMapFromGeoJsonFile(Context context, MapboxMap mapboxMap, MapView mapView, String filename, Boolean point, int count){

        this.filename = filename;
        this.context = context;

        final String[] geoJsonString = {getGeoJsonFromFile.toString()};
        getGeoJsonFromFile.observeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DisposableSubscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "readAndDrawOnMapFromGeoJsonFile: "+ s);

                geoJsonString[0] = s;
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "readAndDrawOnMapFromGeoJsonFile: "+ t.getMessage());


            }

            @Override
            public void onComplete() {
                Log.d(TAG, "readAndDrawOnMapFromGeoJsonFile: "+ geoJsonString[0]);


            }
        });





    }

    Flowable<String> getGeoJsonFromFile = Flowable.generate(
            () -> new BufferedReader(new FileReader(filename)),
            (reader, emitter) -> {
                final String line = reader.readLine();
                if (line != null) {
                    emitter.onNext(line);
                } else {
                    emitter.onComplete();
                }
            },
            (BufferedReader reader) -> {
                reader.close();
            }
    );

}
