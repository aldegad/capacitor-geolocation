# @aldegad/capacitor-geolocation

This is a capacitor plugin, let you receive geolocation updates either forground and background. Android and iOS platforms are suppoerted.

## Install

```bash
npm install @aldegad/capacitor-geolocation
npx cap sync
```

## API

<docgen-index>

* [`requestPermission(...)`](#requestpermission)
* [`startLocationUpdates(...)`](#startlocationupdates)
* [`stopLocationUpdtes()`](#stoplocationupdtes)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### requestPermission(...)

```typescript
requestPermission(options?: GeolocationPermission | undefined) => Promise<{ state: GeolocationPermissionState; }>
```

Request and check geolocation permissions. You can define alert cotext.

| Param         | Type                                                                    |
| ------------- | ----------------------------------------------------------------------- |
| **`options`** | <code><a href="#geolocationpermission">GeolocationPermission</a></code> |

**Returns:** <code>Promise&lt;{ state: <a href="#geolocationpermissionstate">GeolocationPermissionState</a>; }&gt;</code>

**Since:** 0.0.1

--------------------


### startLocationUpdates(...)

```typescript
startLocationUpdates(options?: GeololocationUpdates | undefined, callback?: GeolocationUpdatesCallback | undefined) => Promise<void>
```

Start location updates.

| Param          | Type                                                                              |
| -------------- | --------------------------------------------------------------------------------- |
| **`options`**  | <code><a href="#geololocationupdates">GeololocationUpdates</a></code>             |
| **`callback`** | <code><a href="#geolocationupdatescallback">GeolocationUpdatesCallback</a></code> |

**Since:** 0.0.1

--------------------


### stopLocationUpdtes()

```typescript
stopLocationUpdtes() => Promise<void>
```

Stop location updates.

**Since:** 0.0.1

--------------------


### Interfaces


#### GeolocationPermission

Geolocation permission options.

| Prop              | Type                                                          | Description                                                                                                                          | Since |
| ----------------- | ------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ | ----- |
| **`promptAlert`** | <code><a href="#geolocationalert">GeolocationAlert</a></code> | If user ignore geolocation permission, notice why this application needs geolocation permissions.                                    | 0.0.1 |
| **`deniedAlert`** | <code><a href="#geolocationalert">GeolocationAlert</a></code> | If user denied geolocation permission, notice why this application needs geolocation permissions and tells how to reset permissions. | 0.0.1 |


#### GeolocationAlert

Geolocation alert options.

| Prop             | Type                | Description       | Since |
| ---------------- | ------------------- | ----------------- | ----- |
| **`header`**     | <code>string</code> | Alert header      | 0.0.1 |
| **`message`**    | <code>string</code> | Alert message     | 0.0.1 |
| **`okText`**     | <code>string</code> | Alert ok text     | 0.0.1 |
| **`cancelText`** | <code>string</code> | Alert cancel text | 0.0.1 |


#### GeololocationUpdates

Geolocation updates options.

| Prop             | Type                                                                    | Description                                                                  | Since |
| ---------------- | ----------------------------------------------------------------------- | ---------------------------------------------------------------------------- | ----- |
| **`background`** | <code><a href="#geolocationbackground">GeolocationBackground</a></code> | Geolocation Background setting options.                                      | 0.0.1 |
| **`connect`**    | <code><a href="#geolocationconnect">GeolocationConnect</a></code>       | After location updates, upload data to server. It uses `multipart-formdata`. | 0.0.1 |


#### GeolocationBackground

| Prop               | Type                                                                        | Description                                                                                                                          | Default           | Since |
| ------------------ | --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ | ----------------- | ----- |
| **`enable`**       | <code>boolean</code>                                                        | Toggle use background Geolocation or not.                                                                                            | <code>true</code> |       |
| **`notification`** | <code><a href="#geolocationnotification">GeolocationNotification</a></code> | This is `Android` forground notification module. If you need to run background Geolocation on Android, you must define notification. |                   | 0.0.1 |


#### GeolocationNotification

This is `Android` forground notification module. If you need to run background Geolocation on Android, you must define notification.

| Prop              | Type                | Description                                                                     | Default                                          | Since |
| ----------------- | ------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------ | ----- |
| **`channelID`**   | <code>string</code> | `Android` notification channel id                                               | <code>"LOCATION_SERVICE_CHANNEL"</code>          | 0.0.1 |
| **`channelName`** | <code>string</code> | `Android` notification cannel name                                              | <code>"Geolocation tracking notification"</code> | 0.0.1 |
| **`header`**      | <code>string</code> | `Android` notification header                                                   | <code>"Geolocation tracker"</code>               | 0.0.1 |
| **`message`**     | <code>string</code> | `Android` notification message                                                  | <code>"Geolocation tracking now."</code>         |       |
| **`icon`**        | <code>string</code> | `Android` notification icon. Icon's path should be in `android/app/res` folder. | <code>"minmap/ic_launcher"</code>                |       |


#### GeolocationConnect

After location updates, upload data to server. `multipart-formdata`

| Prop       | Type                                               | Description                                                                           | Default           |
| ---------- | -------------------------------------------------- | ------------------------------------------------------------------------------------- | ----------------- |
| **`url`**  | <code>string</code>                                | Update url                                                                            | <code>null</code> |
| **`body`** | <code>{ [name: string]: string \| number; }</code> | Update body. You can get latitude and longitude data as `@latitude` and `@longitude`. | <code>null</code> |


### Type Aliases


#### GeolocationUpdatesCallback

Each time Geolocation updates, It fires. It's only for `forground state`.

<code>(data: { longitude: number; latitude: number; }): void</code>


### Enums


#### GeolocationPermissionState

| Members       | Value                  | Description                       |
| ------------- | ---------------------- | --------------------------------- |
| **`granted`** | <code>"granted"</code> | Geolocation permissions granted   |
| **`denied`**  | <code>"denied"</code>  | Geolocation permissions denied    |
| **`prompt`**  | <code>"prompt"</code>  | User has not yet set permissions. |

</docgen-api>
