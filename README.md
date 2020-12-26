# rnative-sensor-manager

This package is a wrapper for using sensor native modules

[![GitHub license](https://img.shields.io/github/license/j0nl1/rnative-sensor-manager.svg?color=blue&style=for-the-badge)](./LICENSE)
[![open bugs](https://img.shields.io/github/issues-raw/j0nl1/rnative-sensor-manager/bug.svg?color=d73a4a&label=open%20bugs&style=for-the-badge)](https://github.com/j0nl1/rnative-sensor-manager/issues?utf8=%E2%9C%93&q=is%3Aissue+is%3Aopen+label%3Abug)
[![npm](https://img.shields.io/npm/v/rnative-sensor-manager.svg?color=green&style=for-the-badge)](https://www.npmjs.com/package/rnative-sensor-manager)
[![npm downloads](https://img.shields.io/npm/dw/rnative-sensor-manager.svg?label=npm%20downloads&style=for-the-badge)](https://npmcharts.com/compare/rnative-sensor-manager?minimal=true)
[![total npm downloads](https://img.shields.io/npm/dt/rnative-sensor-manager.svg?label=total%20npm%20downloads&style=for-the-badge)](https://npmcharts.com/compare/rnative-sensor-manager?minimal=true)

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

### Thermometer

```js
import SensorManager from "rnative-sensor-manager";
SensorManager.startThermometer(100);
DeviceEventEmitter.addListener("temperature", (data) => {
  /**
   * data.temperature
   **/
});
SensorManager.stopThermometer();
```

### Proximity

```js
import SensorManager from "rnative-sensor-manager";
SensorManager.startProximity(100);
DeviceEventEmitter.addListener("proximity", (data) => {
  /**
   * data.proximity
   **/
});
SensorManager.stopProximity();
```

### Pressure

```js
import SensorManager from "rnative-sensor-manager";
SensorManager.startPressure(100);
DeviceEventEmitter.addListener("pressure", (data) => {
  /**
   * data.pressure
   **/
});
SensorManager.stopPressure();
```

### Humidity

```js
import SensorManager from "rnative-sensor-manager";
SensorManager.startHumidity(100);
DeviceEventEmitter.addListener("humidity", (data) => {
  /**
   * data.humidity
   **/
});
SensorManager.stopHumidity();
```

## Acknowledgements

- [`react-native-sensor-manager`](https://www.npmjs.com/package/react-native-sensor-manager) has been a great source of inspiration for this project.

## License

[MIT](./LICENSE)
