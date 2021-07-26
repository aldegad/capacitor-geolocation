import { WebPlugin } from '@capacitor/core';

import type { GeolocationPlugin } from './definitions';

export class GeolocationWeb extends WebPlugin implements GeolocationPlugin {
  async requestPermission() {
    let state:any;
    return { state };
  }
  async startLocationUpdates() {}
  async stopLocationUpdtes() {}
}
