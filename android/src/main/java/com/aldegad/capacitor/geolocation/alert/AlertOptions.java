package com.aldegad.capacitor.geolocation.alert;

import com.getcapacitor.JSObject;

public class AlertOptions {
    private static String TAG = "aldegad.gelolocation.AlertOptions";

    public String header;
    public String message;
    public String cancelText;
    public String okText;
    public AlertOptions() {}
    public AlertOptions(String header, String message, String cancelText, String okText) {
        this.header = header != null ? header : this.header;
        this.message = message != null ? message : this.message;
        this.cancelText = cancelText != null ? cancelText : this.cancelText;
        this.okText = okText != null ? okText : this.okText;
    }
    public AlertOptions(JSObject options) {
        if(options != null) {
            this.header = options.getString("header", this.header);
            this.message = options.getString("message", this.message);
            this.cancelText = options.getString("cancelText", this.cancelText);
            this.okText = options.getString("okText", this.okText);
        }
    }
}