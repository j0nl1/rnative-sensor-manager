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

interface LightValue {
  lux: number;
}

export const DeviceLight: React.FC = () => {
  const [appState, setAppSate] = React.useState<string>(AppState.currentState);
  const [lightValue, setLightValue] = React.useState<LightValue>();

  React.useEffect(() => {
    AppState.addEventListener("change", setAppSate);
    DeviceEventEmitter.addListener("light", setLightValue);
    return () => {
      AppState.removeEventListener("change", setAppSate);
      DeviceEventEmitter.removeListener("light", setLightValue);
    };
  }, []);

  React.useEffect(() => {
    if (appState === AppStateStatus.ACTIVE) ManagerSensor.startLight(300);
    if (appState === AppStateStatus.BACKGROUND) ManagerSensor.stopLight();
  }, [appState]);

  return (
    <View style={styles.container}>
      <Text>Light: {JSON.stringify(lightValue)}</Text>
    </View>
  );
};

export default DeviceLight;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
