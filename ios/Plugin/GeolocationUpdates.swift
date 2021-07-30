import Capacitor
import Foundation
import os
import UIKit
import CoreLocation

class GeolocationUpdates:NSObject, CLLocationManagerDelegate {
    static let shared = GeolocationUpdates();
    static var bridge: CAPBridgeProtocol!;
    let manager: CLLocationManager = CLLocationManager()
    
    var geolocationPermissionOptions: GeolocationPermissionOptions!;
    var geolocationPermissionCallback: ((GeolocationPermissionState) -> ())!;
    
    var geolocationUpdatesOptions: GeolocationUpdatesOptions!;
    var geolocationUpdatesCallback: (([String: String]) -> ())!;
    
    func requestPermission(options: GeolocationPermissionOptions, callback: @escaping (GeolocationPermissionState) -> ()) {
        self.geolocationPermissionOptions = options;
        self.geolocationPermissionCallback = callback;
        
        manager.delegate = self;
        manager.desiredAccuracy = kCLLocationAccuracyBest;
        // 앱이 suspend 상태일 때 위치정보를 수진받을 지에 대한 결정
        manager.allowsBackgroundLocationUpdates = true;
        // ?? 백그라운드에 있을때 무슨 정보?? 를 보여주는 것 같은데. 잘 모르겠음
        manager.showsBackgroundLocationIndicator = true;
        // manager가 자동으로 위치정보를 수신 일시 중지 할 수 있는지에 대한 결정
        manager.pausesLocationUpdatesAutomatically = false;
        
        // 폰 지피에스를 켰는가 체크.
        guard CLLocationManager.locationServicesEnabled() else {
            print("locationServices Disabled");
            self.deniedAlert();
            return;
        }
        // 권한이 거부가 되었는지를 체크
        if(checkPermission() == .denied) {
            self.deniedAlert();
            return;
        }
        // 앱 사용중과 관계 없이 항상 권한 요청 - 위 가드를 지우면 항상 쓸지를 안물어본다.(아님. 그냥 시간 지나면 알아서 물어봄)
        manager.requestAlwaysAuthorization()
    }
    func requestRequestPermission(state: GeolocationPermissionState) {
        geolocationPermissionCallback(state);
    }
    func startUpdates(options: GeolocationUpdatesOptions, callback: ([String: String]) -> ()) {
        geolocationUpdatesOptions = options;
        // 아주 중요한 위치 변화 감지 시작 - 앱 킬해도 작동. 단, 많은거리(약 500미터 이상) 움직여야 함
        manager.startMonitoringSignificantLocationChanges()
        // 위치변화 감지 시작
        manager.startUpdatingLocation()
    }
    func stopUpdates() {
        manager.stopMonitoringSignificantLocationChanges()
        manager.stopUpdatingLocation()
    }
    
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        // Handle changes if location permissions
        print("permission changed")
        let permission = checkPermission();
        switch permission {
            case .granted:
                self.requestRequestPermission(state: GeolocationPermissionState.granted);
            case .denied:
                self.deniedAlert();
            default:
                break;
        }
    }
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        //위치 감지가 되면 실행됨
        guard let first = locations.first else {
            return
        }
        
        print("\(first.coordinate.longitude) | \(first.coordinate.latitude)")
        
        let connectOptions = geolocationUpdatesOptions.connect;
        let url = URL(string: connectOptions.url)!
        let request = MultipartFormDataRequest(url: url)
        for (key, value) in connectOptions.body {
            var newValue = "\(value)";
            newValue = newValue.replacingOccurrences(of: "@longitude", with: "\(first.coordinate.longitude)");
            newValue = newValue.replacingOccurrences(of: "@latitude", with: "\(first.coordinate.latitude)");
            request.addTextField(named: key, value: "\(newValue)")
        }
        URLSession.shared.dataTask(with: request, completionHandler: {_,_,_ in }).resume()
    }
    
    func checkPermission() -> GeolocationPermissionState {
        switch CLLocationManager.authorizationStatus() {
            case .authorizedAlways, .authorizedWhenInUse:
                print("GPS: 권한 있음")
                return GeolocationPermissionState.granted;
            case .restricted, .notDetermined:
                print("GPS: 아직 선택하지 않음")
                return GeolocationPermissionState.prompt;
            case .denied:
                print("GPS: 권한 없음")
                return GeolocationPermissionState.denied;
            default:
                print("GPS: Default")
                return GeolocationPermissionState.prompt;
        }
    }
    func openSetting() {
        guard let settingsUrl = URL(string: UIApplication.openSettingsURLString) else {
            return
        }
        if UIApplication.shared.canOpenURL(settingsUrl) {
            UIApplication.shared.open(settingsUrl, completionHandler: { (success) in })
        }
    }
    func deniedAlert() {
        let alertOptions = self.geolocationPermissionOptions.deniedAlert;
        let promptAlert = UIAlertController( title: alertOptions.header, message: alertOptions.message, preferredStyle: .alert);
        let cancelButton = UIAlertAction(title: alertOptions.cancelText, style: .cancel, handler: { (action) in
            self.requestRequestPermission(state: GeolocationPermissionState.denied);
        });
        let okButton = UIAlertAction(title: alertOptions.okText, style: .destructive, handler: { (action) in
            NotificationCenter.default.addObserver(self, selector: #selector(self.onResume), name: UIApplication.willEnterForegroundNotification, object: nil)
            self.openSetting();
        });
        promptAlert.addAction(cancelButton);
        promptAlert.addAction(okButton);
        GeolocationUpdates.bridge?.viewController?.present(promptAlert, animated: true, completion: nil);
    }
    @objc func onResume() {
        NotificationCenter.default.removeObserver(self);
        self.requestPermission(options: self.geolocationPermissionOptions, callback: self.geolocationPermissionCallback);
    }
}
