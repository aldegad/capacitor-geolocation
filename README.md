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

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### requestPermission(...)

```typescript
requestPermission(options?: GeolocationOptions.RequestPermission | undefined) => any
```

Request and check geolocation permissions. You can define alert cotext.

| Param         | Type                           | Description                          |
| ------------- | ------------------------------ | ------------------------------------ |
| **`options`** | <code>RequestPermission</code> | GeolocationOptions.RequestPermission |

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

</docgen-api>
