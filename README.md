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

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

## Docs from JSDoc comments!

This content is from the JSDOC comments on top of
the `HapticsPlugin` interface. All the API data below
is generated from comments from its methods, interfaces
and enums.

Take a look at the test [HapticsPlugin interface source file](https://github.com/ionic-team/capacitor-docgen/blob/master/src/test/fixtures/definitions.ts).

### requestPermission(...)

```typescript
requestPermission(options?: GeolocationPermission | undefined) => any
```

Request and check geolocation permissions. You can define alert cotext.

| Param         | Type                                                                    |
| ------------- | ----------------------------------------------------------------------- |
| **`options`** | <code><a href="#geolocationpermission">GeolocationPermission</a></code> |

**Returns:** <code>any</code>

--------------------


### startLocationUpdates(...)

```typescript
startLocationUpdates(options?: GeololocationUpdates | undefined, callback?: GeolocationUpdatesCallback | undefined) => any
```

Start update location

| Param          | Type                                                                  |
| -------------- | --------------------------------------------------------------------- |
| **`options`**  | <code><a href="#geololocationupdates">GeololocationUpdates</a></code> |
| **`callback`** | <code>GeolocationUpdatesCallback</code>                               |

**Returns:** <code>any</code>

--------------------


### stopLocationUpdtes()

```typescript
stopLocationUpdtes() => any
```

Request and check geolocation permissions. You can define alert cotext.

**Returns:** <code>any</code>

--------------------


### Interfaces


#### GeolocationPermission

Geolocation permission options.

| Prop              | Type                                                          | Description                                                                                                                          |
| ----------------- | ------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| **`promptAlert`** | <code><a href="#geolocationalert">GeolocationAlert</a></code> | If user ignore geolocation permission, notice why this application needs geolocation permissions.                                    |
| **`deniedAlert`** | <code><a href="#geolocationalert">GeolocationAlert</a></code> | If user denied geolocation permission, notice why this application needs geolocation permissions and tells how to reset permissions. |


#### GeolocationAlert

Geolocation alert options.

| Prop             | Type                |
| ---------------- | ------------------- |
| **`header`**     | <code>string</code> |
| **`message`**    | <code>string</code> |
| **`okText`**     | <code>string</code> |
| **`cancelText`** | <code>string</code> |


#### GeololocationUpdates

Geolocation updates options.

| Prop             | Type                                                                    | Description                                                         |
| ---------------- | ----------------------------------------------------------------------- | ------------------------------------------------------------------- |
| **`background`** | <code><a href="#geolocationbackground">GeolocationBackground</a></code> | Geolocation Background setting options. enable                      |
| **`connect`**    | <code><a href="#geolocationconnect">GeolocationConnect</a></code>       | After location updates, upload data to server. `multipart-formdata` |


#### GeolocationBackground

| Prop               | Type                                                                        | Description                                                                                                                          | Default           |
| ------------------ | --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ | ----------------- |
| **`enable`**       | <code>boolean</code>                                                        | Toggle use background Geolocation or not.                                                                                            | <code>true</code> |
| **`notification`** | <code><a href="#geolocationnotification">GeolocationNotification</a></code> | This is `Android` forground notification module. If you need to run background Geolocation on Android, you must define notification. |                   |


#### GeolocationNotification

This is 'Android' forground notification module. If you need to run background Geolocation on Android, you must define notification.

| Prop              | Type                | Description                                                                     | Default                                          |
| ----------------- | ------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------ |
| **`channelID`**   | <code>string</code> | `Android` notification channel id                                               | <code>"LOCATION_SERVICE_CHANNEL"</code>          |
| **`channelName`** | <code>string</code> | `Android` notification cannel name                                              | <code>"Geolocation tracking notification"</code> |
| **`header`**      | <code>string</code> | `Android` notification header                                                   | <code>"Geolocation tracker"</code>               |
| **`message`**     | <code>string</code> | `Android` notification message                                                  | <code>"Geolocation tracking now."</code>         |
| **`icon`**        | <code>string</code> | `Android` notification icon. Icon's path should be in `android/app/res` folder. | <code>"minmap/ic_launcher"</code>                |


#### GeolocationConnect

After location updates, upload data to server. `multipart-formdata`

| Prop       | Type                                               | Description                                                                           | Default           |
| ---------- | -------------------------------------------------- | ------------------------------------------------------------------------------------- | ----------------- |
| **`url`**  | <code>string</code>                                | Update url                                                                            | <code>null</code> |
| **`body`** | <code>{ [name: string]: string \| number; }</code> | Update body. You can get latitude and longitude data as `@latitude` and `@longitude`. | <code>null</code> |

</docgen-api>
