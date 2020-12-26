import * as React from "react";
import { View, StyleSheet } from "react-native";

import DeviceLight from "./modules/DeviceLight";
import DeviceProximity from "./modules/DeviceProximity";
import DeviceOrientation from "./modules/DeviceOrientation";
import DeviceThermometer from "./modules/DeviceThermometer";
import DevicePressure from "./modules/DevicePressure";
import DeviceHumidity from "./modules/DeviceHumidity";

export const App: React.FC = () => {
  return (
    <View style={styles.container}>
      <DeviceOrientation />
      <DeviceLight />
      <DeviceThermometer />
      <DeviceHumidity />
      <DeviceProximity />
      <DevicePressure />
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
