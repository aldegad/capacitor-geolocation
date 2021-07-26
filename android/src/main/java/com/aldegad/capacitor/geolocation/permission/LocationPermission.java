package com.aldegad.capacitor.geolocation.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.aldegad.capacitor.geolocation.alert.AlertPlugin;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;

public class LocationPermission {
    private static String TAG = "aldegad.geolocation.permission.LocationPermission";

    private static AppCompatActivity activity = null;
    private static boolean isOpenSetting = false;
    private static LocationPermissionOptions locationPermissionOptions = null;
    private static LocationPermissionCallback locationPermissionCallback = null;
    private static ActivityResultLauncher<String> locationPermissionLauncher = null;

    public static void add(AppCompatActivity _activity) {
        activity = _activity;
        locationPermissionLauncher = activity.registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), isGranted -> {
                Log.d(TAG, "locationPermissionLauncher: " + isGranted);
                Boolean PermissionDenied = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION);
                if (isGranted) {
                    requestPermission(locationPermissionOptions, locationPermissionCallback);
                } else if (!PermissionDenied) {
                    AlertPlugin.present(
                        locationPermissionOptions.promptAlert.header,
                        locationPermissionOptions.promptAlert.message,
                        locationPermissionOptions.promptAlert.okText,
                        locationPermissionOptions.promptAlert.cancelText,
                        () -> requestPermission(locationPermissionOptions, locationPermissionCallback),
                        () -> returnRequestPermission(PermissionState.PROMPT));
                } else {
                    AlertPlugin.present(
                        locationPermissionOptions.deniedAlert.header,
                        locationPermissionOptions.deniedAlert.message,
                        locationPermissionOptions.deniedAlert.okText,
                        locationPermissionOptions.deniedAlert.cancelText,
                        () -> openSetting(),
                        () -> returnRequestPermission(PermissionState.DENIED));
                }
            }
        );
    }

    public static void onResume() {
        if (isOpenSetting) {
            isOpenSetting = false;
            requestPermission(locationPermissionOptions, locationPermissionCallback);
        }
    }

    public static void openSetting() {
        isOpenSetting = true;
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void returnRequestPermission(PermissionState state) {
        JSObject res = new JSObject();
        res.put("state", state);
        locationPermissionCallback.run(res);
    }

    public static void requestPermission(LocationPermissionOptions _options, LocationPermissionCallback _locationPermissionCallback) {

        locationPermissionOptions = _options;
        locationPermissionCallback = _locationPermissionCallback;

        Boolean hasPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        Boolean PermissionDenied = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION);

        // 포그라운드 권한 체크
        if (!hasPermission) {
            Log.d(TAG, "PermissionDenied: " + PermissionDenied);
            if (!PermissionDenied) {
                // 포그라운드 위치 권한을 체크 안함 - 권한 팝업
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            } else {
                // 포그라운드 위치 권한이 거부된 상태 - 권한 설명 및 권한 팝업
                AlertPlugin.present(
                    locationPermissionOptions.deniedAlert.header,
                    locationPermissionOptions.deniedAlert.message,
                    locationPermissionOptions.deniedAlert.okText,
                    locationPermissionOptions.deniedAlert.cancelText,
                    () -> openSetting(),
                    () -> returnRequestPermission(PermissionState.DENIED));
            }
        } else {
            // 포그라운드 서비스를 사용하면 백그라운드 gps 사용안함.(사용하는 것도 애매함. 인터벌이 너무 길다)
            returnRequestPermission(PermissionState.GRANTED);
        }
    }
}
