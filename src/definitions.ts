export interface GeolocationPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
