package com.aldegad.capacitor.geolocation.updates;

import com.getcapacitor.JSObject;

public class GeolocationConnectOptions {
    public String url = null;
    public JSObject body = null;
    public GeolocationConnectOptions() {}
    public GeolocationConnectOptions(JSObject options) {
        this.url = options.getString("url") != null ? options.getString("url") : this.url;
        this.body = options.getJSObject("body") != null ? options.getJSObject("body") : this.body;;
    }
}
