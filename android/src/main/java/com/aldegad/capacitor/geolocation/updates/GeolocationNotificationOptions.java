package com.aldegad.capacitor.geolocation.updates;

import android.util.Log;

import com.getcapacitor.JSObject;

public class GeolocationNotificationOptions {
    private static String TAG = "aldegad.geolocation.updates.GeolocationNotificationOptions";

    public String channelID = "LOCATION_SERVICE_CHANNEL";
    public String channelName = "Geolocation tracking notification";
    public String header = "Geolocation tracker";
    public String message = "Geolocation tracking now.";
    public String icon = "mipmap/ic_launcher";
    public GeolocationNotificationOptions(){}
    public GeolocationNotificationOptions(JSObject options) {
        Log.d(TAG, options != null ? options.toString() : "null");
        if(options != null) {
            this.channelID = options.getString("channelID") != null ? options.getString("channelID") : this.channelID;
            this.channelName = options.getString("channelName") != null ? options.getString("channelName") : this.channelName;
            this.header = options.getString("header") != null ? options.getString("header") : this.header;
            this.message = options.getString("message") != null ? options.getString("message") : this.message;
            this.icon = options.getString("icon") != null ? options.getString("icon") : this.icon;
        }
    }
}
