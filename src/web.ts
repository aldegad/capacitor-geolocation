import { WebPlugin } from '@capacitor/core';

import { GeolocationPermissionState, GeolocationPlugin } from './definitions';

export class GeolocationWeb extends WebPlugin implements GeolocationPlugin {
  async requestPermission() {
    const state = GeolocationPermissionState.denied;
    return { state };
  }
  async startLocationUpdates() {}
  async stopLocationUpdtes() {}
}
