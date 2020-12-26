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

interface HumidityEvent {
  humidity: number;
}

const DeviceHumidity: React.FC = () => {
  const [appState, setAppSate] = React.useState<string>(AppState.currentState);
  const [humidityPercent, setHumidityPercent] = React.useState<HumidityEvent>();

  React.useEffect(() => {
    AppState.addEventListener("change", setAppSate);
    DeviceEventEmitter.addListener("humidity", setHumidityPercent);
    return () => {
      AppState.removeEventListener("change", setAppSate);
      DeviceEventEmitter.removeListener("humidity", setHumidityPercent);
    };
  }, []);

  React.useEffect(() => {
    if (appState === AppStateStatus.ACTIVE) ManagerSensor.startHumidity(300);
    if (appState === AppStateStatus.BACKGROUND) ManagerSensor.stopHumidity();
  }, [appState]);

  return (
    <View style={styles.container}>
      <Text>Humidity: {JSON.stringify(humidityPercent)}</Text>
    </View>
  );
};

export default DeviceHumidity;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
