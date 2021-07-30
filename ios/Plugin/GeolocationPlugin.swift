import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(GeolocationPlugin)
public class GeolocationPlugin: CAPPlugin {
    private let implementation = Geolocation()

    @objc func requestPermission(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            var options:GeolocationPermissionOptions = GeolocationPermissionOptions();
            options.deniedAlert = call.getObject("deniedAlert") != nil ? GeolocationAlertOptions(options: call.getObject("deniedAlert")) : options.deniedAlert;
            
            GeolocationUpdates.bridge = self.bridge;
            GeolocationUpdates.shared.requestPermission(options: options, callback: { (state) in
                print("permission callback called: \(state)");
                call.resolve(["state": "\(state)"]);
            });
        }
    }
    @objc func startLocationUpdates(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            call.keepAlive = true;
            var options:GeolocationUpdatesOptions = GeolocationUpdatesOptions();
            options.background = call.getBool("background", options.background);
            options.connect = GeolocationConnectOptions(options: call.getObject("connect") ?? nil);
            
            GeolocationUpdates.shared.startUpdates(options: options, callback: { (res) in
                call.resolve(res)
            });
        }
    }
    @objc func stopLocationUpdates(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            GeolocationUpdates.shared.stopUpdates()
            call.resolve()
        }
    }
}
