package com.aldegad.capacitor.geolocation.permission;

import com.aldegad.capacitor.geolocation.alert.AlertOptions;

public class LocationPermissionOptions {
    public AlertOptions promptAlert = new AlertOptions("Geolocation Permission Required", "This app needs to geolocation permission.\n Please granted geolocation permission.", "cancel", "ok");
    public AlertOptions deniedAlert = new AlertOptions("Geolocation Permission Rejected", "This app needs to geolocation permission.\n Move to [permission -> geolocation]. And granted permission.", "denied", "setting");
}
