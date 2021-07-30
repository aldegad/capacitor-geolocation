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

    private Geolocation implementation = new Geolocation(); // 아직 용도를 잘 모르겠다. 실력이 부족한듯.

    @PluginMethod()
    public void requestPermission(PluginCall call) {
        GeolocationPermissionOptions options = new GeolocationPermissionOptions();
        options.promptAlert = call.getObject("promptAlert", null) != null ? new GeolocationAlertOptions(call.getObject("promptAlert", null)) : options.promptAlert;
        options.deniedAlert = call.getObject("deniedAlert", null) != null ? new GeolocationAlertOptions(call.getObject("deniedAlert", null)) : options.deniedAlert;
        GeolocationPermission.requestPermission(options, res -> {
            call.resolve(res);
        });
    }
    @PluginMethod(returnType = PluginMethod.RETURN_CALLBACK)
    public void startLocationUpdates(PluginCall call) {
        call.setKeepAlive(true);

        GeolocationUpdatesOptions options = new GeolocationUpdatesOptions();
        options.background = call.getBoolean("background", options.background);
        options.notification = new GeolocationNotificationOptions(call.getObject("notification", null));
        options.connect = new GeolocationConnectOptions(call.getObject("connect", null));

        GeolocationUpdates.startForgroundUpdates(getActivity(), options, res -> {
            call.resolve(res);
        });
    }
    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void stopLocationUpdates(PluginCall call) {
        GeolocationUpdates.stopUpdates(getActivity());
        call.resolve();
    }

    @Override
    protected void handleOnStart() {
        super.handleOnStart();
        AppCompatActivity activity = getActivity();
        GeolocationPermission.onCreate(activity);
    }

    @Override
    protected void handleOnResume() {
        super.handleOnResume();
        GeolocationPermission.onResume();
    }
}
