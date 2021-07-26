package com.aldegad.capacitor.geolocation.updates;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

import com.aldegad.capacitor.geolocation.R;
import com.aldegad.capacitor.geolocation.connect.API;
import com.getcapacitor.JSObject;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LocationService extends Service {
    private static String TAG = "Dev.Location.LocationService";

    public static FusedLocationProviderClient fusedLocationClient;
    public static LocationCallback locationCallback;

    public static LocationUpdatesCallback locationUpdateCallback;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        startForegroundService();
        startUpdates(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopUpdates();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startForegroundService() {
        Log.d(TAG, "startForegroundService");
        Intent notificationIntent = new Intent(this, getApplicationContext().getClass());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        String CHANNEL_ID = "LOCATION_SERVICE_CHANNEL";

        NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Geolocation tracking notification", NotificationManager.IMPORTANCE_LOW);
        notificationManager.createNotificationChannel(channel);

        Notification notification =
            new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Geolocation tracker")
                .setContentText("Geolocation tracking now.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                //.setTicker("Location Ticker") // 이게 머지?? 없어도 되긴되는데, 먼지 몰겠넹...
                .build();

        startForeground(1, notification);
    }

    public void startUpdates(Context context) {
        Boolean hasForegroundPermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if(!hasForegroundPermission) return;

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
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
                Log.d(TAG, "getUpdatedLocation");
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    try {
                        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1
                        );

                        Double longitude = addresses.get(0).getLongitude();
                        Double latitude = addresses.get(0).getLatitude();

                        locationUpload(longitude, latitude);

                        if(locationUpdateCallback != null) {
                            JSObject res = new JSObject();
                            res.put("longitude", longitude);
                            res.put("latitude", latitude);
                            locationUpdateCallback.run(res);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest,
            locationCallback,
            Looper.getMainLooper());
    }
    private static void locationUpload(double longitude, double latitude) {
        Log.d(TAG, "locationUpload: " + longitude + " & " + latitude);

        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("error_type", RequestBody.create(MediaType.parse("text/plain"), "100"));
        params.put("error_text", RequestBody.create(MediaType.parse("text/plain"), "Location: " + longitude + " & " + latitude));
        API.run("http://3.35.5.135/KUNYOUNG/managementservice.asmx/Get_ServerLog", params,
            response -> {

            });
    }
    public static void stopUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
