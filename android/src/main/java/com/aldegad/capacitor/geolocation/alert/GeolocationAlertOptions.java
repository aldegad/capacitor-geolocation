package com.aldegad.capacitor.geolocation.alert;

import com.getcapacitor.JSObject;

public class GeolocationAlertOptions {
    private static String TAG = "aldegad.gelolocation.AlertOptions";

    public String header;
    public String message;
    public String cancelText;
    public String okText;
    public GeolocationAlertOptions() {}
    public GeolocationAlertOptions(JSObject options) {
        if(options != null) {
            this.header = options.getString("header", this.header);
            this.message = options.getString("message", this.message);
            this.cancelText = options.getString("cancelText", this.cancelText);
            this.okText = options.getString("okText", this.okText);
        }
    }
}