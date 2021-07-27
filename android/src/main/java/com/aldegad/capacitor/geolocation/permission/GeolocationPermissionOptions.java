package com.aldegad.capacitor.geolocation.permission;

import com.aldegad.capacitor.geolocation.alert.GeolocationAlertOptions;

public class GeolocationPermissionOptions {
    public GeolocationAlertOptions promptAlert = new GeolocationAlertOptions();
    public GeolocationAlertOptions deniedAlert = new GeolocationAlertOptions();
    public GeolocationPermissionOptions() {
        promptAlert.header = "Geolocation Permission Required";
        promptAlert.message = "This app needs to geolocation permission.\n Please granted geolocation permission.";
        promptAlert.cancelText = "cancel";
        promptAlert.okText = "ok";

        deniedAlert.header = "Geolocation Permission Rejected";
        deniedAlert.message = "This app needs to geolocation permission.\n Move to [permission -> geolocation]. And granted permission.";
        deniedAlert.cancelText = "denied";
        deniedAlert.okText = "setting";
    }
}
