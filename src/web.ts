import { WebPlugin } from '@capacitor/core';

import { GeolocationPermissionState, GeolocationPlugin } from './definitions';

export class GeolocationWeb extends WebPlugin implements GeolocationPlugin {
  async requestPermission() {
    let state:any = GeolocationPermissionState.granted;
    return { state };
  }
  async startLocationUpdates() {}
  async stopLocationUpdtes() {}
}
