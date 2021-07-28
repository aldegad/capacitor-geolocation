export interface GeolocationPlugin {
  /**
   * Request and check geolocation permissions. You can define alert cotext.
   * @since 0.0.1
   */
  requestPermission(options?: GeolocationPermissionOptions): Promise<{ state: GeolocationPermissionState }>;
  /**
   * Start location updates.
   * @since 0.0.1
   */
  startLocationUpdates(options?: GeololocationUpdatesOptions, callback?: GeolocationUpdatesCallback):Promise<void>;
  /**
   * Stop location updates.
   * @since 0.0.1
   */
   stopLocationUpdates():Promise<void>;
}
/**
 * Geolocation permission options.
 */
export interface GeolocationPermissionOptions {
  /**
   * If user ignore geolocation permission, notice why this application needs geolocation permissions.
   * @since 0.0.1
   */
  promptAlert?: GeolocationAlertOptions,
  /**
   * If user denied geolocation permission, notice why this application needs geolocation permissions and tells how to reset permissions.
   * @since 0.0.1
   */
  deniedAlert?: GeolocationAlertOptions
}
/**
 * Geolocation alert options.
 * @since 0.0.1
 */
export interface GeolocationAlertOptions {
  /**
   * Alert header
   * @since 0.0.1
   */
  header?: string,
  /**
   * Alert message
   * @since 0.0.1
   */
  message?: string,
  /**
   * Alert ok text
   * @since 0.0.1
   */
  okText?: string,
  /**
   * Alert cancel text
   * @since 0.0.1
   */
  cancelText?: string
}
/**
 * Geolocation updates options.
 */
export interface GeololocationUpdatesOptions {
  /**
   * Toggle background geolocation enable or not.
   * @default true
   * @since 0.0.1
   */
  background?: boolean
  /**
   * This is `Android` forground notification option. If you need to run background geolocation on Android, you must define notification.
   * @since 0.0.1
   */
  notification?: GeolocationNotificationOptions
  /**
   * After location updates, upload data to server. It uses `multipart-formdata`.
   * @default Nothing to do is default.
   * @since 0.0.1
   */
  connect?: GeolocationConnectOptions
}
/**
 * This is `Android` forground notification module. If you need to run background Geolocation on Android, you must define notification.
 * @since 0.0.1
 */
export interface GeolocationNotificationOptions {
  /**
   * `Android` notification channel id
   * @since 0.0.1
   * @default "LOCATION_SERVICE_CHANNEL"
   */
  channelID?: string,
  /**
   * `Android` notification cannel name
   * @since 0.0.1
   * @default "Geolocation tracking notification"
   */
  channelName?: string,
  /**
   * `Android` notification header
   * @since 0.0.1
   * @default "Geolocation tracker"
   */
  header?: string,
  /**
   * `Android` notification message
   * @since 0.0.1
   * @default "Geolocation tracking now."
   */
  message?: string,
  /**
   * `Android` notification icon. Icon's path should be in `android/app/res` folder. Do not use image mine-type like `.png`, `.jpeg` etc.
   * @since 0.0.1
   * @default "mipmap/ic_launcher"
   */
  icon?: string
}
/**
 * After location updates, upload data to server. `multipart-formdata`
 */
export interface GeolocationConnectOptions {
  /**
   * Update url
   * @default null
   * @since 0.0.1
   */
  url: string,
  /**
   * Update body. You can get latitude and longitude data as `@latitude` and `@longitude`.
   * @default null
   * @since 0.0.1
   */
  body?: {[name:string]:string | number}
}
export interface GeolocationUpdatesCallbackEvent {
  /**
   * User location longitude
   * @since 0.0.1
   */
  longitude: number,
  /**
   * User location latitude
   * @since 0.0.1
   */
  latitude: number
}
/**
 * Each time Geolocation updates, It fires. It's only for `forground state`.
 * @since 0.0.1
 */
export type GeolocationUpdatesCallback = (data: GeolocationUpdatesCallbackEvent) => void
/**
 * Gecolocation permission State
 */
 export enum GeolocationPermissionState {
  /**
   * Geolocation permissions granted
   */
  granted = "granted",
  /**
   * Geolocation permissions denied
   */
  denied = "denied",
  /**
   * User has not yet set permissions.
   */
  prompt = "prompt"
}