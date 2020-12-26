import * as React from "react";
import {
  StyleSheet,
  View,
  Text,
  DeviceEventEmitter,
  AppState,
} from "react-native";
import ManagerSensor from "rnative-sensor-manager";

enum AppStateStatus {
  ACTIVE = "active",
  INACTIVE = "inactive",
  BACKGROUND = "background",
}

interface TemperatureValue {
  temperature: number;
}

export const DeviceThermometer: React.FC = () => {
  const [appState, setAppSate] = React.useState<string>(AppState.currentState);
  const [
    temperatureValue,
    setTemperatureValue,
  ] = React.useState<TemperatureValue>();

  React.useEffect(() => {
    AppState.addEventListener("change", setAppSate);
    DeviceEventEmitter.addListener("temperature", setTemperatureValue);
    return () => {
      AppState.removeEventListener("change", setAppSate);
      DeviceEventEmitter.removeListener("temperature", setTemperatureValue);
    };
  }, []);

  React.useEffect(() => {
    if (appState === AppStateStatus.ACTIVE) ManagerSensor.startThermometer(300);
    if (appState === AppStateStatus.BACKGROUND) ManagerSensor.stopThermometer();
  }, [appState]);

  return (
    <View style={styles.container}>
      <Text>Temperature: {JSON.stringify(temperatureValue)}</Text>
    </View>
  );
};

export default DeviceThermometer;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
