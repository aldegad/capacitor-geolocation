package com.aldegad.capacitor.geolocation.updates;

import android.util.Log;

import com.aldegad.capacitor.geolocation.connect.API;

import java.util.HashMap;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GeolocationConnect {
    private static String TAG = "aldegad.Gecolocation.GeolocationConnect";

    public static void uploadUpdates(GeolocationConnectOptions options, double longitude, double latitude) {
        Log.d(TAG, "uploadUpdates: " + longitude + " & " + latitude);
        Log.d(TAG, "uploadUpdates: " + options.url);
        Log.d(TAG, "uploadUpdates: " + options.body);

        if(options.url != null && options.url != "") {
            HashMap<String, RequestBody> params = new HashMap<>();

            if(options.body != null) {
                Iterator<String> keys = options.body.keys();
                 while(keys.hasNext()) {
                    String key = keys.next();
                    String value = options.body.getString(key);
                    if(value.indexOf("@longitude") > -1) {
                        value = value.replaceAll("@longitude", ""+longitude);
                    }
                    if(value.indexOf("@latitude") > -1) {
                        value = value.replaceAll("@latitude", ""+latitude);
                    }
                    Log.d(TAG, "param: " + key + " & " + value);
                    params.put(key, RequestBody.create(MediaType.parse("text/plain"), value));
                }
            } else {
                params.put("nothing", RequestBody.create(MediaType.parse("text/plain"), ""));
            }
            API.run(options.url, params,
                    response -> {

                    });

        }
    }
}
