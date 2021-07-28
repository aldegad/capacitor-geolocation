package com.aldegad.capacitor.geolocation.alert;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.aldegad.capacitor.geolocation.R;

public class GeolocationAlert {
    private static String TAG = "aldegad.geolocation.alert.GeolocationAlert";

    public static void present(Context context, String title, String message, String okText, String CancelText, GeolocationAlertButtonCallback okCallback, GeolocationAlertButtonCallback rejectCallback) {

        Log.d(TAG, "present: " + title);
        Log.d(TAG, "present: " + message);
        Log.d(TAG, "present: " + okText);
        Log.d(TAG, "present: " + CancelText);

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppTheme_AlertDialog);

        builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(okText, (dialog, id) -> {
                okCallback.run();
            })
            .setNegativeButton(CancelText, (dialog, id) -> {
                rejectCallback.run();
            })
            .setOnCancelListener(dialog -> {
                rejectCallback.run();
            });
        builder.create();
        builder.show();
    }
}

