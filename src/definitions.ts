export interface GeolocationPlugin {
  /**
   * Request and check geolocation permissions. You can define alert cotext.
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
   * Geolocation Background setting options.
   * enable @default true
   */
  background: GeolocationBackground
  /**
   * After location updates, upload data to server(multipart-formdata format).
   */
  connect: {
    url: string,
    body: JSON
  }
}
export interface GeolocationBackground {
  /**
   * Toggle use background Geolocation or not.
   * @default true
   */
  enable: boolean,
  /**
   * This is android forground notification module. If you need to run background Geolocation on Android, you must define notification.
   */
  notification: GeolocationNotification
}
/**
 * This is android forground notification module. If you need to run background Geolocation on Android, you must define notification.
 */
export interface GeolocationNotification {
  /**
   * <code>Android</code> notification channel 
   * @default "LOCATION_SERVICE_CHANNEL"
   */
  channelID: string,
  /**
   * <code>Android</code> support 
   * @default "LOCATION_SERVICE_CHANNEL"
   */
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