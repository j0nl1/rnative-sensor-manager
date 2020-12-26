import * as React from "react";
import { View, StyleSheet } from "react-native";

export const App: React.FC = () => {
  return <View style={styles.container}></View>;
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
