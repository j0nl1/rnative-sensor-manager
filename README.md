# rnative-sensor-manager

This package is a wrapper for using sensor native modules

## Installation

```sh
npm install rnative-sensor-manager
```

## API Usage

### Orientation

```js
import SensorManager from "rnative-sensor-manager";
SensorManager.startOrientation(100);
DeviceEventEmitter.addListener("orientation", (data) => {
  /**
   * data.azimuth
   * data.pitch
   * data.roll
   **/
});
SensorManager.stopOrientation();
```

### Light

```js
import SensorManager from "rnative-sensor-manager";
SensorManager.startLight(100);
DeviceEventEmitter.addListener("light", (data) => {
  /**
   * data.lux
   **/
});
SensorManager.stopLight();
```

## License

MIT
