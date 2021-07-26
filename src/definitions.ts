export interface GeolocationPlugin {
  /**
   * Request and check geolocation permissions. You can define alert cotext.
   */
  requestPermission(options?: GeolocationPermission): Promise<{ state: PermissionState }>;
  /**
   * Start update location
   */
  startLocationUpdates(options?: GeololocationUpdates, callback?: GeolocationUpdatesCallback):Promise<void>;
  /**
   * Request and check geolocation permissions. You can define alert cotext.
   */
  stopLocationUpdtes():Promise<void>;
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
   * enable 
   */
  background?: GeolocationBackground
  /**
   * After location updates, upload data to server. `multipart-formdata`
   */
  connect?: GeolocationConnect
}
export interface GeolocationBackground {
  /**
   * Toggle use background Geolocation or not.
   * @default true
   */
  enable: boolean,
  /**
   * This is `Android` forground notification module. If you need to run background Geolocation on Android, you must define notification.
   */
  notification: GeolocationNotification
}
/**
 * This is 'Android' forground notification module. If you need to run background Geolocation on Android, you must define notification.
 */
export interface GeolocationNotification {
  /**
   * `Android` notification channel id
   * @default "LOCATION_SERVICE_CHANNEL"
   */
  channelID?: string,
  /**
   * `Android` notification cannel name
   * @default "Geolocation tracking notification"
   */
  channelName?: string,
  /**
   * `Android` notification header
   * @default "Geolocation tracker"
   */
  header?: string,
  /**
   * `Android` notification message
   * @default "Geolocation tracking now."
   */
  message?: string,
  /**
   * `Android` notification icon. Icon's path should be in `android/app/res` folder.
   * @default "minmap/ic_launcher"
   */
  icon?: string
}
/**
 * After location updates, upload data to server. `multipart-formdata`
 */
export interface GeolocationConnect {
  /**
   * Update url
   * @default null
   */
  url: string,
  /**
   * Update body. You can get latitude and longitude data as `@latitude` and `@longitude`.
   * @default null
   */
  body?: {[name:string]:string | number}
}
/**
 * Each time Geolocation updates, It fires. It's only for `forground state`.
 */
export type GeolocationUpdatesCallback = (data: {
  longitude: number,
  latitude: number
}) => void