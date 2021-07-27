package com.aldegad.capacitor.geolocation.updates;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.getcapacitor.JSObject;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeolocationUpdates extends Service {
    private static String TAG = "aldegad.Geolocation.GeolocationUpdates";
    public static GeolocationUpdatesOptions geolocationUpdatesOptions;
    public static GeolocationUpdatesCallback geolocationUpdatesCallback;

    private static FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = GeolocationNotification.build(this, geolocationUpdatesOptions.notification);
            startForeground(1, notification);
        }
        _startUpdates(this, geolocationUpdatesOptions, geolocationUpdatesCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true);
        }
        _stopUpdates();
    }
    public static void startBackgroundUpdates(Context context) {
        if(fusedLocationClient != null) {
            Intent intent = new Intent(context, GeolocationUpdates.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            }
            else {
                context.startService(intent);
            }
        }
    }
    public static void startForgroundUpdates(Context context, GeolocationUpdatesOptions options, GeolocationUpdatesCallback callback) {
        geolocationUpdatesOptions = options;
        geolocationUpdatesCallback = callback;

        Log.d(TAG, "startForgroundUpdates: " + geolocationUpdatesOptions.notification.toString());

        Intent intent = new Intent(context, GeolocationUpdates.class);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        }
        else {
            context.startService(intent);
        }
    }
    public static void stopUpdates(Context context) {
        Intent intent = new Intent(context, GeolocationUpdates.class);
        if (Build.VERSION.SDK_INT >= 26) {
            context.stopService(intent);
        }
        else {
            context.stopService(intent);
        }
    }
    private void _startUpdates(Context packageContext, GeolocationUpdatesOptions options, GeolocationUpdatesCallback callback) {
        Boolean hasForegroundPermission = ActivityCompat.checkSelfPermission(packageContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if(!hasForegroundPermission) return;

        if(fusedLocationClient != null) fusedLocationClient.removeLocationUpdates(locationCallback);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(packageContext);
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                Log.d(TAG, "_startUpdates");
                for (Location location : locationResult.getLocations()) {
                    // Update location data
                    Double longitude = location.getLongitude();
                    Double latitude = location.getLatitude();

                    GeolocationConnect.uploadUpdates(options.connect, longitude, latitude);

                    if(callback != null) {
                        Log.d(TAG, "callback called");
                        JSObject res = new JSObject();
                        res.put("longitude", longitude);
                        res.put("latitude", latitude);
                        callback.run(res);
                    }

                    // 주소 부분. 딱히 아직은 쓸일이 없는 듯함. 근데 보통 이런거 플러그인 확장할 때 꼭 쓰이더라고. 그니깐 냅둠.
                    /* try {
                        Geocoder geocoder = new Geocoder(packageContext, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    } */
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }
    private void _stopUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
        fusedLocationClient = null;
    }
}
