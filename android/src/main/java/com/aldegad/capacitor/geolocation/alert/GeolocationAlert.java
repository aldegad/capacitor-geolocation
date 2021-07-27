package com.aldegad.capacitor.geolocation.alert;

import android.app.AlertDialog;
import android.content.Context;

import com.aldegad.capacitor.geolocation.R;

public class GeolocationAlert {
    public static void present(Context context, String title, String message, String okText, String CancelText, GeolocationAlertButtonCallback okCallback, GeolocationAlertButtonCallback rejectCallback) {
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

