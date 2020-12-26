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

export const DeviceProximity: React.FC = () => {
  const [appState, setAppSate] = React.useState<string>(AppState.currentState);
  const [proximity, setProximity] = React.useState();

  React.useEffect(() => {
    AppState.addEventListener("change", setAppSate);
    DeviceEventEmitter.addListener("proximity", setProximity);
    return () => {
      AppState.removeEventListener("change", setAppSate);
      DeviceEventEmitter.removeListener("proximity", setProximity);
    };
  }, []);

  React.useEffect(() => {
    if (appState === AppStateStatus.ACTIVE) ManagerSensor.startProximity(300);
    if (appState === AppStateStatus.BACKGROUND) ManagerSensor.stopProximity();
  }, [appState]);

  return (
    <View style={styles.container}>
      <Text>Proximity: {JSON.stringify(proximity)}</Text>
    </View>
  );
};

export default DeviceProximity;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
