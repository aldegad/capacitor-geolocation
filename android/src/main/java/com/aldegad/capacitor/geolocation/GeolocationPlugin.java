package com.aldegad.capacitor.geolocation;

import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.aldegad.capacitor.geolocation.alert.GeolocationAlertOptions;
import com.aldegad.capacitor.geolocation.alert.GeolocationAlert;
import com.aldegad.capacitor.geolocation.permission.GeolocationPermission;
import com.aldegad.capacitor.geolocation.permission.GeolocationPermissionOptions;
import com.aldegad.capacitor.geolocation.updates.GeolocationConnectOptions;
import com.aldegad.capacitor.geolocation.updates.GeolocationNotificationOptions;
import com.aldegad.capacitor.geolocation.updates.GeolocationUpdates;
import com.aldegad.capacitor.geolocation.updates.GeolocationUpdatesOptions;
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
        GeolocationPermissionOptions options = new GeolocationPermissionOptions();
        options.promptAlert = new GeolocationAlertOptions(call.getObject("promptAlert", null));
        options.deniedAlert = new GeolocationAlertOptions(call.getObject("deniedAlert", null));
        Log.d(TAG, options.promptAlert.header);
        GeolocationPermission.requestPermission(options, res -> {
            call.resolve(res);
        });
    }
    @PluginMethod(returnType = PluginMethod.RETURN_CALLBACK)
    public void startLocationUpdates(PluginCall call) {
        GeolocationUpdatesOptions options = new GeolocationUpdatesOptions();
        options.background = call.getBoolean("background", true);
        options.notification = new GeolocationNotificationOptions(call.getObject("notification", null));
        options.connect = new GeolocationConnectOptions(call.getObject("connect", null));
        call.setKeepAlive(true);

        GeolocationUpdates.startForgroundUpdates(getActivity(), options, res -> {
            call.resolve(res);
        });
    }
    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void stopLocationUpdates(PluginCall call) {
        GeolocationUpdates.stopUpdates(getContext());
        call.resolve();
    }

    @Override
    protected void handleOnStart() {
        super.handleOnStart();
        AppCompatActivity activity = getActivity();
        GeolocationAlert.add(activity);
        GeolocationPermission.add(activity);
    }

    @Override
    protected void handleOnResume() {
        super.handleOnResume();
        GeolocationPermission.onResume();
    }
}
