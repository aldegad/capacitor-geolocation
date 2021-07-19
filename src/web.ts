import { WebPlugin } from '@capacitor/core';

import type { GeolocationPlugin } from './definitions';

export class GeolocationWeb extends WebPlugin implements GeolocationPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
