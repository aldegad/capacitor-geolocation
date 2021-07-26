package com.aldegad.capacitor.geolocation.updates;

import com.getcapacitor.JSObject;

public class LocationConnectOptions {
    public String url = null;
    public JSObject body = null;
    public LocationConnectOptions() {}
    public LocationConnectOptions(JSObject options) {
        this.url = options.getString("url") != null ? options.getString("url") : this.url;
        this.body = options.getJSObject("body") != null ? options.getJSObject("body") : this.body;;
    }
}
