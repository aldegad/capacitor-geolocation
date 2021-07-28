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
            let value = call.getString("value") ?? ""
            call.resolve([
                "value": implementation.echo(value)
            ])
        }
    }
    @objc func startLocationUpdates(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            call.keepAlive = true;
            let value = call.getString("value") ?? ""
            call.resolve([
                "value": implementation.echo(value)
            ])
        }
    }
    @objc func stopLocationUpdates(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            let value = call.getString("value") ?? ""
            call.resolve([
                "value": implementation.echo(value)
            ])
        }
    }
}
