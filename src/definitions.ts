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
 * @interface
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
 * @interface
 */
export interface GeolocationAlert {
  header?: string,
  message?: string,
  okText?: string,
  cancelText?: string
}
/**
 * Geolocation updates options.
 * @interface
 */
export interface GeololocationUpdates {
  /**
   * After location updates, upload data to server.(multipart-formdata format)
   * @param {string} url - Location update url.
   */
  connect: {
    url: string,
    body: JSON
  }
}
export type GeolocationUpdatesCallback = (data: {
  longitude: number,
  latitude: number
}) => {}