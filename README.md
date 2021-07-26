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
requestPermission(options?: RequestPermission | undefined) => any
```

Request and check geolocation permissions. You can define alert cotext.

| Param         | Type                                                            | Description                                                           |
| ------------- | --------------------------------------------------------------- | --------------------------------------------------------------------- |
| **`options`** | <code><a href="#requestpermission">RequestPermission</a></code> | GeolocationOptions.<a href="#requestpermission">RequestPermission</a> |

**Returns:** <code>any</code>

--------------------


### startLocationUpdates(...)

```typescript
startLocationUpdates(options?: GeolocationOptions.LocationUpdates | undefined, callback?: GeolocationOptions.LocationUpdatesCallback | undefined) => any
```

| Param          | Type                                 |
| -------------- | ------------------------------------ |
| **`options`**  | <code>LocationUpdates</code>         |
| **`callback`** | <code>LocationUpdatesCallback</code> |

**Returns:** <code>any</code>

--------------------


### stopLocationUpdtes()

```typescript
stopLocationUpdtes() => any
```

**Returns:** <code>any</code>

--------------------


### Interfaces


#### RequestPermission

| Prop              | Type                                    | Description                                                                                                                          |
| ----------------- | --------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| **`promptAlert`** | <code><a href="#alert">Alert</a></code> | If user ignore geolocation permission, notice why this application needs geolocation permissions.                                    |
| **`deniedAlert`** | <code><a href="#alert">Alert</a></code> | If user denied geolocation permission, notice why this application needs geolocation permissions and tells how to reset permissions. |


#### Alert

Geolocation alert interface.

| Prop             | Type                |
| ---------------- | ------------------- |
| **`header`**     | <code>string</code> |
| **`message`**    | <code>string</code> |
| **`okText`**     | <code>string</code> |
| **`cancelText`** | <code>string</code> |

</docgen-api>
