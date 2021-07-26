export namespace GeolocationOptions {
  export interface Alert {
    header?: string,
    message?: string,
    okText?: string,
    cancelText?: string
  }
  export interface RequestPermission {
    promptAlert?: Alert,
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
export interface GeolocationPlugin {
  /**
   * Request GPS Permission. You can define alert cotext.
   * @param options GeolocationOptions.RequestPermission
   */
  requestPermission(options?: GeolocationOptions.RequestPermission): Promise<{ state: PermissionState }>;
  startLocationUpdates(options?: GeolocationOptions.LocationUpdates, callback?: GeolocationOptions.LocationUpdatesCallback): Promise<void>;
  stopLocationUpdtes(): Promise<void>;
}
