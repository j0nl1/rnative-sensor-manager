import { NativeModules } from "react-native";

type SensorManagerTypes = {
  startOrientation(refreshFreq?: number): void;
  stopOrientation(): void;
  startLight(refreshFreq?: number): void;
  stopLight(): void;
  startThermometer(refreshFreq?: number): void;
  stopThermometer(): void;
  startProximity(refreshFreq?: number): void;
  stopProximity(): void;
  startPressure(refreshFreq?: number): void;
  stopPressure(): void;
};

const { SensorManager } = NativeModules;

export default SensorManager as SensorManagerTypes;
