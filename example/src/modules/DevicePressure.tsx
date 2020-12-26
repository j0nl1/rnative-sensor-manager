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

export const DevicePressure: React.FC = () => {
  const [appState, setAppSate] = React.useState<string>(AppState.currentState);
  const [pressure, setPressure] = React.useState();

  React.useEffect(() => {
    AppState.addEventListener("change", setAppSate);
    DeviceEventEmitter.addListener("pressure", setPressure);
    return () => {
      AppState.removeEventListener("change", setAppSate);
      DeviceEventEmitter.removeListener("pressure", setPressure);
    };
  }, []);

  React.useEffect(() => {
    if (appState === AppStateStatus.ACTIVE) ManagerSensor.startPressure(300);
    if (appState === AppStateStatus.BACKGROUND) ManagerSensor.stopPressure();
  }, [appState]);

  return (
    <View style={styles.container}>
      <Text>Pressure: {JSON.stringify(pressure)}</Text>
    </View>
  );
};

export default DevicePressure;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
