import * as React from "react";
import {
  StyleSheet,
  View,
  Text,
  DeviceEventEmitter,
  AppState,
} from "react-native";
import SensorManager from "rnative-sensor-manager";

enum AppStateStatus {
  ACTIVE = "active",
  INACTIVE = "inactive",
  BACKGROUND = "background",
}

interface OrientationAngles {
  azimuth: number;
  pitch: number;
  roll: number;
}

export const DeviceOrientation: React.FC = () => {
  const [appState, setAppSate] = React.useState<string>(AppState.currentState);
  const [orientation, setOrientation] = React.useState<OrientationAngles>();

  React.useEffect(() => {
    AppState.addEventListener("change", setAppSate);
    DeviceEventEmitter.addListener("orientation", setOrientation);
    return () => {
      AppState.removeEventListener("change", setAppSate);
      DeviceEventEmitter.removeListener("orientation", setOrientation);
    };
  }, []);

  React.useEffect(() => {
    if (appState === AppStateStatus.ACTIVE) SensorManager.startOrientation(300);
    if (appState === AppStateStatus.BACKGROUND) SensorManager.stopOrientation();
  }, [appState]);

  return (
    <View style={styles.container}>
      <Text>Orientation: {JSON.stringify(orientation)}</Text>
    </View>
  );
};

export default DeviceOrientation;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
