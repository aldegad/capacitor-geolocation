export interface GeolocationPlugin {
  /**
   * Request and check geolocation permissions. You can define alert cotext.
   * @param options GeolocationOptions.RequestPermission
   */
  requestPermission(options?: GeolocationPermission): Promise<{ state: PermissionState }>;
  startLocationUpdates(options?: GeololocationUpdates, callback?: GeolocationUpdatesCallback): Promise<void>;
  stopLocationUpdtes(): Promise<void>;
}
/**
 * Geolocation permission options.
 */
export interface GeolocationPermission {
  /**
   * 
   * If user ignore geolocation permission, notice why this application needs geolocation permissions.
   */
  promptAlert?: GeolocationAlert,
  /**
   * 
   * If user denied geolocation permission, notice why this application needs geolocation permissions and tells how to reset permissions.
   */
  deniedAlert?: GeolocationAlert
}
/**
 * Geolocation alert options.
 */
export interface GeolocationAlert {
  header?: string,
  message?: string,
  okText?: string,
  cancelText?: string
}
/**
 * Geolocation updates options.
 */
export interface GeololocationUpdates {
  /**
   * This is android forground notification module. If you need to run background Geolocation on Android, you must define notification.
   */
  notification: GeolocationNotification
  /**
   * After location updates, upload data to server(multipart-formdata format).
   */
  connect: {
    url: string,
    body: JSON
  }
}
/**
 * This is android forground notification module. If you need to run background Geolocation on Android, you must define notification.
 */
export interface GeolocationNotification {
  /**
   * Toggle use background Geolocation or not.
   * @default true
   */
  enable: boolean,
  /**
   * <code>Android</code> support 
   * @default true
   */
  channelID: string,
  channelName: string,
  header: string,
  message: string,
  icon: string
}
export interface GeolocationConnect {

}
export type GeolocationUpdatesCallback = (data: {
  longitude: number,
  latitude: number
}) => {}