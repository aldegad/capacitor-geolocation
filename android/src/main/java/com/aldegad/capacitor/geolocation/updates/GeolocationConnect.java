package com.aldegad.capacitor.geolocation.updates;

import android.util.Log;

import com.aldegad.capacitor.geolocation.connect.API;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GeolocationConnect {
    private static String TAG = "aldegad.Gecolocation.GeolocationConnec";

    public static void uploadUpdates(double longitude, double latitude) {
        Log.d(TAG, "locationUpload: " + longitude + " & " + latitude);

        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("error_type", RequestBody.create(MediaType.parse("text/plain"), "100"));
        params.put("error_text", RequestBody.create(MediaType.parse("text/plain"), "Location: " + longitude + " & " + latitude));
        API.run("http://3.35.5.135/KUNYOUNG/managementservice.asmx/Get_ServerLog", params,
                response -> {

                });
    }
}
