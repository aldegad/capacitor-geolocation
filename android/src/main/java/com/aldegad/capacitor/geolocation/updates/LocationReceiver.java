package com.aldegad.capacitor.geolocation.updates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class LocationReceiver extends BroadcastReceiver {
    private static String TAG = "Dev.Location.LocationReceiver";

    @Override
    public void onReceive(Context context, Intent _) {
        Intent intent = new Intent(context, LocationService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        }
        else {
            context.startService(intent);
        }
    }
}
