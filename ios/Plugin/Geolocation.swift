import Foundation

@objc public class Geolocation: NSObject {
    @objc public func echo(_ value: String) -> String {
        return value
    }
}

public enum GeolocationPermissionState:String {
    case granted = "granted"
    case denied = "denied"
    case prompt = "prompt"
}
