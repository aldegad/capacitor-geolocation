package com.aldegad.capacitor.geolocation.updates;

import com.getcapacitor.JSObject;

public class LocationBackgroundOptions {
    public boolean enable = true;
    public LocationNotificationOptions notification = new LocationNotificationOptions();
    public LocationBackgroundOptions() {}
    public LocationBackgroundOptions(JSObject options) {
        this.enable = options.getBool("enable") != null ? options.getBool("enable") : this.enable;
        this.notification = options.getJSObject("notification") != null ? new LocationNotificationOptions(options.getJSObject("notification")) : this.notification;
    }
}
