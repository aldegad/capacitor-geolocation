package com.aldegad.capacitor.geolocation.permission;

import com.aldegad.capacitor.geolocation.alert.AlertOptions;

public class LocationPermissionOptions {
    public AlertOptions promptAlert = new AlertOptions("위치권한 필요", "이 앱은 원활한 사용을 위해 위치권한을 필요로 합니다.\n 위치 권한을 허용해주세요.", "거부", "확인");
    public AlertOptions deniedAlert = new AlertOptions("위치권한 거부됨", "이 앱은 원활한 사용을 위해 위치권한을 필요로 합니다.\n [권한 -> 위치]로 이동하여 위치권한을 허용해주세요.", "거부", "이동");
}
