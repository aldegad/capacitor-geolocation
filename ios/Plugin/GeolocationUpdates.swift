import Capacitor
import Foundation
import os
import UIKit
import CoreLocation

class GeolocationUpdates:NSObject, CLLocationManagerDelegate {
    
    var manager: CLLocationManager = CLLocationManager()
    var bridge: CAPBridgeProtocol?;
    
    init(bridge:CAPBridgeProtocol) {
        self.bridge = bridge;
    }
    
    func requestPermission() -> GeolocationPermissionState {
        manager.delegate = self;
        manager.desiredAccuracy = kCLLocationAccuracyBest;
        // 앱 사용중에만 권한 요청
        //manager.requestWhenInUseAuthorization()
        // 앱이 suspend 상태일 때 위치정보를 수진받을 지에 대한 결정
        manager.allowsBackgroundLocationUpdates = true;
        // ?? 백그라운드에 있을때 무슨 정보?? 를 보여주는 것 같은데. 잘 모르겠음
        manager.showsBackgroundLocationIndicator = true;
        // manager가 자동으로 위치정보를 수신 일시 중지 할 수 있는지에 대한 결정
        manager.pausesLocationUpdatesAutomatically = false;
        
        // 이 가드를 걸어야 앱 킬을 했을 때 서비스를 사용할지를 물어본다... 이유는 모르겠지만, 아이폰 정책 문제인듯?
        // 이거때문이 아니었던 듯. 뭐지? 어떤애가 권한을 체크하는건지 다시 봐야됨.
        let promptAlert = UIAlertController();
        let cancel = UIAlertAction(title: "cancel", style: .cancel) { (action) in
            
        }
        let ok = UIAlertAction(title: "ok", style: .destructive) { (action) in
            
        }
        promptAlert.title = "Geolocation Permission Required";
        promptAlert.message = "This app needs to geolocation permission.\nPlease granted geolocation permission";
        promptAlert.addAction(cancel);
        promptAlert.addAction(ok);
        bridge!.viewController!.present(promptAlert, animated: true, completion: nil);
        /*
        guard CLLocationManager.locationServicesEnabled() else {
            return GeolocationPermissionState.denied;
        }
        
        // 앱 사용중과 관계 없이 항상 권한 요청 - 위 가드를 지우면 항상 쓸지를 안물어본다.(아님. 그냥 시간 지나면 알아서 물어봄)
        manager.requestAlwaysAuthorization()
        
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
                return GeolocationPermissionState.denied;
        } */
        return GeolocationPermissionState.granted;
    }
    func startUpdates() {
        // 아주 중요한 위치 변화 감지 시작 - 앱 킬해도 작동. 단, 500미터 이상 움직여야 함 - 500미터 이상 움직이면 다시 살아남
        manager.startMonitoringSignificantLocationChanges()
        // 위치변화 감지 시작
        manager.startUpdatingLocation()
    }
    func stopUpdates() {
        manager.stopUpdatingLocation()
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        //위치 감지가 되면 실행됨
        guard let first = locations.first else {
            return
        }
         
        print("\(first.coordinate.longitude) | \(first.coordinate.latitude)")
        
        let url = URL(string: "http://3.35.5.135/KUNYOUNG/managementservice.asmx/Get_ServerLog")!
        let request = MultipartFormDataRequest(url: url)
        request.addTextField(named: "error_type", value: "100")
        request.addTextField(named: "error_text", value: "Hello iOS")
        URLSession.shared.dataTask(with: request, completionHandler: {_,_,_ in }).resume()
    }
}
