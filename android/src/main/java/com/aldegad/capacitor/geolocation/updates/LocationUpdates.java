package com.aldegad.capacitor.geolocation.updates;

import android.content.Intent;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

public class LocationUpdates {
    private static String TAG = "Dev.Location.LocationUpdates";

    private static AppCompatActivity activity = null;

    public static void add(AppCompatActivity _activity) {
        activity = _activity;
    }
    public static void startUpdates(LocationUpdatesOptions locationUpdatesOptions, LocationUpdatesCallback locationUpdatesCallback) {
        LocationService.locationUpdatesOptions = locationUpdatesOptions;
        LocationService.locationUpdateCallback = locationUpdatesCallback;

        Intent intent = new Intent(activity, LocationService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            activity.startForegroundService(intent);
        }
        else {
            activity.startService(intent);
        }
    }
    public static void stopUpdates() {
        Intent intent = new Intent(activity, LocationService.class);
        activity.stopService(intent);
    }
}
