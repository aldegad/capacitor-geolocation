import Foundation
import Capacitor

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
public struct GeolocationPermissionOptions {
    var deniedAlert: GeolocationAlertOptions = GeolocationAlertOptions();
    init() {
        deniedAlert.header = "Geolocation Permission Rejected";
        deniedAlert.message = "This app needs to geolocation permission.\n Move to [Privacy -> Locaation Service]. And granted permissions.";
        deniedAlert.cancelText = "denied";
        deniedAlert.okText = "setting";
    }
}
public struct GeolocationAlertOptions {
    var header: String = "";
    var message: String = "";
    var okText: String = "";
    var cancelText: String = "";
    init() {}
    init(options: JSObject?) {
        if(options != nil) {
            header = options!["header"] as? String ?? header;
            message = options!["message"] as? String ?? message;
            cancelText = options!["cancelText"] as? String ?? cancelText;
            okText = options!["okText"] as? String ?? okText;
        }
    }
}
public struct GeolocationUpdatesOptions {
    var background: Bool = true;
    var connect: GeolocationConnectOptions = GeolocationConnectOptions();
    init() {}
}
public struct GeolocationConnectOptions {
    var url: String = "";
    var body: JSObject = [:];
    init() {}
    init(options: JSObject?) {
        if(options != nil) {
            url = options!["url"] as? String ?? url;
            body = options!["body"] as? JSObject ?? body;
        }
    }
}
