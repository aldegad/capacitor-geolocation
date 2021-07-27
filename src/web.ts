import { WebPlugin } from '@capacitor/core';

import { GeolocationPermissionState, GeolocationPlugin } from './definitions';

export class GeolocationWeb extends WebPlugin implements GeolocationPlugin {
  async requestPermission() {
    let state:GeolocationPermissionState = GeolocationPermissionState.granted;
    return { state };
  }
  async startLocationUpdates() {}
  async stopLocationUpdates() {}
}
