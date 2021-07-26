package com.aldegad.capacitor.geolocation.alert;

import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.aldegad.capacitor.geolocation.R;

public class AlertPlugin {
    private static AppCompatActivity activity = null;
    public static void add(AppCompatActivity _activity) {
        activity = _activity;
    }
    public static void present(String title, String message, String okText, String CancelText, AlertButtonCallback okCallback, AlertButtonCallback rejectCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppTheme_AlertDialog);
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

