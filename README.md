# @aldegad/capacitor-geolocation

This is a capacitor plugin, let you receive geolocation updates either forground and background. `Android` and `iOS` platforms are suppoerted.

## Warning

You can't use this now. It is under development.
## Install

```bash
npm install @aldegad/capacitor-geolocation
npx cap sync
```
### Examples

Quick Example:

```ts

import { Geolocation, GeolocationAlert, GeolocationConnect } from '@aldegad/capacitor-geolocation';

async startLocationUpdates() {
    const { state } = await Geolocation.requestPermission();

    if(state !== 'granted') return;

    Geolocation.startLocationUpdates(null, ({latitude, longitude}) => {
        console.log("location updates", `${latitude}/${longitude}`);
    });
}
stopLocationUpdates() {
    Geolocation.startLocationUpdates();
}
```

Full Example:

```ts

import { Geolocation, GeolocationAlert, GeolocationConnect } from '@aldegad/capacitor-geolocation';

async startLocationUpdates() {
    const permissionOptions:GeolocationPermissionOptions = {
        promptAlert: null,
        deniedAlert: null
    }
    const promptAlert:GeolocationAlertOptions = {
        header: '위치권한 필요',
        message: '이 앱은 원활한 사용을 위해 위치권한을 필요로 합니다.\n위치 권한을 허용해주세요.',
        cancelText: '거부',
        okText: '확인'
    }
    const deniedAlert:GeolocationAlertOptions = {
        header: '위치권한 거부됨',
        message: '이 앱은 원활한 사용을 위해 위치권한을 필요로 합니다.\n[권한 -> 위치]로 이동하여 권한을 허용해주세요.',
        cancelText: '거부',
        okText: '이동'
    }
    permissionOptions.promptAlert = promptAlert;
    permissionOptions.deniedAlert = deniedAlert;
    const { state } = await Geolocation.requestPermission(permissionOptions);
    
    if(state !== 'granted') return;
    
    const updatesOptions:GeololocationUpdatesOptions = {
        background: null,
        notification: null,
        connect: null
    }
    const background:boolean = true;
    const notification:GeolocationNotificationOptions = {
        channelID: 'LOCATION_SERVICE_CHANNEL',
        channelName: '근로자 안전 위치 관리',
        header: '근로자 안전 관리 시스템',
        message: '안전한 근무를 위해 위치관리 시스템을 작동 중 입니다.',
        icon: 'drawable/default_dark'
    }
    const connect:GeolocationConnectOptions = {
        url: 'http://3.35.5.135/KUNYOUNG/managementservice.asmx/Get_ServerLog',
        body: {
            user_id: 'ef34f3f3',
            user_position: 'User position is @latitude and @longitude'
        }
    }
    updatesOptions.background = background;
    updatesOptions.notification = notification;
    updatesOptions.connect = connect;
    Geolocation.startLocationUpdates(updatesOptions, ({latitude, longitude}) => {
        console.log();
    });
}
stopLocationUpdates() {
    Geolocation.startLocationUpdates();
}
```
## API

<docgen-index>

* [`requestPermission(...)`](#requestpermission)
* [`startLocationUpdates(...)`](#startlocationupdates)
* [`stopLocationUpdates()`](#stoplocationupdates)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### requestPermission(...)

```typescript
requestPermission(options?: GeolocationPermissionOptions | undefined) => Promise<{ state: GeolocationPermissionState; }>
```

Request and check geolocation permissions. You can define alert cotext.

| Param         | Type                                                                                  |
| ------------- | ------------------------------------------------------------------------------------- |
| **`options`** | <code><a href="#geolocationpermissionoptions">GeolocationPermissionOptions</a></code> |

**Returns:** <code>Promise&lt;{ state: <a href="#geolocationpermissionstate">GeolocationPermissionState</a>; }&gt;</code>

**Since:** 0.0.1

--------------------


### startLocationUpdates(...)

```typescript
startLocationUpdates(options?: GeololocationUpdatesOptions | undefined, callback?: GeolocationUpdatesCallback | undefined) => Promise<void>
```

Start location updates.

| Param          | Type                                                                                |
| -------------- | ----------------------------------------------------------------------------------- |
| **`options`**  | <code><a href="#geololocationupdatesoptions">GeololocationUpdatesOptions</a></code> |
| **`callback`** | <code><a href="#geolocationupdatescallback">GeolocationUpdatesCallback</a></code>   |

**Since:** 0.0.1

--------------------


### stopLocationUpdates()

```typescript
stopLocationUpdates() => Promise<void>
```

Stop location updates.

**Since:** 0.0.1

--------------------


### Interfaces


#### GeolocationPermissionOptions

Geolocation permission options.

| Prop              | Type                                                                        | Description                                                                                                                          | Since |
| ----------------- | --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ | ----- |
| **`promptAlert`** | <code><a href="#geolocationalertoptions">GeolocationAlertOptions</a></code> | If user ignore geolocation permission, notice why this application needs geolocation permissions.                                    | 0.0.1 |
| **`deniedAlert`** | <code><a href="#geolocationalertoptions">GeolocationAlertOptions</a></code> | If user denied geolocation permission, notice why this application needs geolocation permissions and tells how to reset permissions. | 0.0.1 |


#### GeolocationAlertOptions

Geolocation alert options.

| Prop             | Type                | Description       | Since |
| ---------------- | ------------------- | ----------------- | ----- |
| **`header`**     | <code>string</code> | Alert header      | 0.0.1 |
| **`message`**    | <code>string</code> | Alert message     | 0.0.1 |
| **`okText`**     | <code>string</code> | Alert ok text     | 0.0.1 |
| **`cancelText`** | <code>string</code> | Alert cancel text | 0.0.1 |


#### GeololocationUpdatesOptions

Geolocation updates options.

| Prop               | Type                                                                                      | Description                                                                                                                          | Default                                | Since |
| ------------------ | ----------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ | -------------------------------------- | ----- |
| **`background`**   | <code>boolean</code>                                                                      | Toggle background geolocation enable or not.                                                                                         | <code>true</code>                      | 0.0.1 |
| **`notification`** | <code><a href="#geolocationnotificationoptions">GeolocationNotificationOptions</a></code> | This is `Android` forground notification option. If you need to run background geolocation on Android, you must define notification. |                                        | 0.0.1 |
| **`connect`**      | <code><a href="#geolocationconnectoptions">GeolocationConnectOptions</a></code>           | After location updates, upload data to server. It uses `multipart-formdata`.                                                         | <code>Nothing to do is default.</code> | 0.0.1 |


#### GeolocationNotificationOptions

This is `Android` forground notification module. If you need to run background Geolocation on Android, you must define notification.

| Prop              | Type                | Description                                                                                                                          | Default                                          | Since |
| ----------------- | ------------------- | ------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------ | ----- |
| **`channelID`**   | <code>string</code> | `Android` notification channel id                                                                                                    | <code>"LOCATION_SERVICE_CHANNEL"</code>          | 0.0.1 |
| **`channelName`** | <code>string</code> | `Android` notification cannel name                                                                                                   | <code>"Geolocation tracking notification"</code> | 0.0.1 |
| **`header`**      | <code>string</code> | `Android` notification header                                                                                                        | <code>"Geolocation tracker"</code>               | 0.0.1 |
| **`message`**     | <code>string</code> | `Android` notification message                                                                                                       | <code>"Geolocation tracking now."</code>         | 0.0.1 |
| **`icon`**        | <code>string</code> | `Android` notification icon. Icon's path should be in `android/app/res` folder. Do not use image mine-type like `.png`, `.jpeg` etc. | <code>"minmap/ic_launcher"</code>                | 0.0.1 |


#### GeolocationConnectOptions

After location updates, upload data to server. `multipart-formdata`

| Prop       | Type                                               | Description                                                                           | Default           | Since |
| ---------- | -------------------------------------------------- | ------------------------------------------------------------------------------------- | ----------------- | ----- |
| **`url`**  | <code>string</code>                                | Update url                                                                            | <code>null</code> | 0.0.1 |
| **`body`** | <code>{ [name: string]: string \| number; }</code> | Update body. You can get latitude and longitude data as `@latitude` and `@longitude`. | <code>null</code> | 0.0.1 |


#### GeolocationUpdatesCallbackEvent

| Prop            | Type                | Description             | Since |
| --------------- | ------------------- | ----------------------- | ----- |
| **`longitude`** | <code>number</code> | User location longitude | 0.0.1 |
| **`latitude`**  | <code>number</code> | User location latitude  | 0.0.1 |


### Type Aliases


#### GeolocationUpdatesCallback

Each time Geolocation updates, It fires. It's only for `forground state`.

<code>(data: <a href="#geolocationupdatescallbackevent">GeolocationUpdatesCallbackEvent</a>): void</code>


### Enums


#### GeolocationPermissionState

| Members       | Value                  | Description                       |
| ------------- | ---------------------- | --------------------------------- |
| **`granted`** | <code>"granted"</code> | Geolocation permissions granted   |
| **`denied`**  | <code>"denied"</code>  | Geolocation permissions denied    |
| **`prompt`**  | <code>"prompt"</code>  | User has not yet set permissions. |

</docgen-api>