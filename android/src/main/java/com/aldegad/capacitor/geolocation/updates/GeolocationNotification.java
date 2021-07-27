package com.aldegad.capacitor.geolocation.updates;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class GeolocationNotification {
    private static String TAG = "aldegad.geolocation.updates.GeolocationNotification";
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Notification build(Context packageContext, GeolocationNotificationOptions geolocationNotificationOptions) {
        Intent notificationIntent = new Intent(packageContext, packageContext.getClass());
        PendingIntent pendingIntent = PendingIntent.getActivity(packageContext, 0, notificationIntent, 0);

        String CHANNEL_ID = geolocationNotificationOptions.channelID;

        NotificationManager notificationManager = packageContext.getSystemService(NotificationManager.class);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, geolocationNotificationOptions.channelName, NotificationManager.IMPORTANCE_LOW);
        notificationManager.createNotificationChannel(channel);

        Log.d(TAG, "startForegroundService: " + packageContext.getPackageName());
        Log.d(TAG, "startForegroundService: " + geolocationNotificationOptions.channelID);
        Log.d(TAG, "startForegroundService: " + geolocationNotificationOptions.channelName);
        Log.d(TAG, "startForegroundService: " + geolocationNotificationOptions.header);
        Log.d(TAG, "startForegroundService: " + geolocationNotificationOptions.message);
        Log.d(TAG, "startForegroundService: " + geolocationNotificationOptions.icon);

        Notification notification =
                new Notification.Builder(packageContext, CHANNEL_ID)
                        .setContentTitle(geolocationNotificationOptions.header)
                        .setContentText(geolocationNotificationOptions.message)
                        .setSmallIcon(packageContext.getResources().getIdentifier(geolocationNotificationOptions.icon, "image", packageContext.getPackageName()))
                        .setContentIntent(pendingIntent)
                        //.setTicker("Location Ticker") // 이게 머지?? 없어도 되긴되는데, 먼지 몰겠넹...
                        .build();
        return notification;
    }
}
