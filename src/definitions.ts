export interface GeolocationPlugin {
  /**
   * Request and check geolocation permissions. You can define alert cotext.
   * @param options GeolocationOptions.RequestPermission
   */
  requestPermission(options?: GeolocationOptions.RequestPermission): Promise<{ state: PermissionState }>;
  startLocationUpdates(options?: GeolocationOptions.LocationUpdates, callback?: GeolocationOptions.LocationUpdatesCallback): Promise<void>;
  stopLocationUpdtes(): Promise<void>;
}
/**
 * Geolocation alert interface.
 * @interface
 */
export interface Alert {
  header?: string,
  message?: string,
  okText?: string,
  cancelText?: string
}
/**
 * My namespace.
 * @namespace
 */
export namespace GeolocationOptions {
  export interface Alert {
    header?: string,
    message?: string,
    okText?: string,
    cancelText?: string
  }
  export interface RequestPermission {
    /**
     * 
     * If user ignore geolocation permission, notice why this application needs geolocation permissions.
     */
    promptAlert?: Alert,
    /**
     * 
     * If user denied geolocation permission, notice why this application needs geolocation permissions and tells how to reset permissions.
     */
    deniedAlert?: Alert
  }
  export interface LocationUpdates {
    connect: {
      url: string,
      body: JSON
    }
  }
  export type LocationUpdatesCallback = (data: {
    longitude: number,
    latitude: number
  }) => {}
}