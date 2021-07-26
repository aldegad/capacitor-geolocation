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

### requestPermission(...)

```typescript
requestPermission(options?: GeolocationPermission | undefined) => any
```

Request and check geolocation permissions. You can define alert cotext.

| Param         | Type                                                                    | Description                          |
| ------------- | ----------------------------------------------------------------------- | ------------------------------------ |
| **`options`** | <code><a href="#geolocationpermission">GeolocationPermission</a></code> | GeolocationOptions.RequestPermission |

**Returns:** <code>any</code>

--------------------


### startLocationUpdates(...)

```typescript
startLocationUpdates(options?: GeololocationUpdates | undefined, callback?: GeolocationUpdatesCallback | undefined) => any
```

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

| Prop          | Type                                     | Description                                                               |
| ------------- | ---------------------------------------- | ------------------------------------------------------------------------- |
| **`connect`** | <code>{ url: string; body: any; }</code> | After location updates, upload data to server.(multipart-formdata format) |

</docgen-api>
