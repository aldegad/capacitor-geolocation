package com.aldegad.capacitor.geolocation;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.aldegad.capacitor.geolocation.alert.AlertOptions;
import com.aldegad.capacitor.geolocation.alert.AlertPlugin;
import com.aldegad.capacitor.geolocation.permission.LocationPermission;
import com.aldegad.capacitor.geolocation.permission.LocationPermissionOptions;
import com.aldegad.capacitor.geolocation.updates.LocationUpdates;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Geolocation")
public class GeolocationPlugin extends Plugin {
    private static String TAG = "aldegad.gelolocation.GeolocationPlugin";

    private Geolocation implementation = new Geolocation();

    @PluginMethod()
    public void requestPermission(PluginCall call) {
        LocationPermissionOptions options = new LocationPermissionOptions();
        options.promptAlert = new AlertOptions(call.getObject("promptAlert", null));
        options.deniedAlert = new AlertOptions(call.getObject("deniedAlert", null));
        Log.d(TAG, options.promptAlert.header);
        LocationPermission.requestPermission(options, res -> {
            call.resolve(res);
        });
    }
    @PluginMethod(returnType = PluginMethod.RETURN_CALLBACK)
    public void startLocationUpdates(PluginCall call) {
        call.setKeepAlive(true);
        LocationUpdates.startUpdates(res -> {
            call.resolve(res);
        });
    }
    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void stopLocationUpdates(PluginCall call) {
        LocationUpdates.stopUpdates();
        call.resolve();
    }

    @Override
    protected void handleOnStart() {
        super.handleOnStart();
        AppCompatActivity activity = getActivity();
        AlertPlugin.add(activity);
        LocationPermission.add(activity);
        LocationUpdates.add(activity);
    }

    @Override
    protected void handleOnResume() {
        super.handleOnResume();
        LocationPermission.onResume();
    }
}
