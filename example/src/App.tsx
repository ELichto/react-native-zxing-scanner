import React from 'react';
import { Text, View, StyleSheet, Button } from 'react-native';
import { showQrReader } from 'react-native-zxing-scanner';

export default function App() {
  const [value, setValue] = React.useState<string | null>(null);

  function readQr(): void {
    showQrReader(setValue);
  }

  return (
    <View style={styles.container}>
      <Text>Result</Text>
      <Button title="readQR" onPress={readQr} />
      {value && <Text>{value}</Text>}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
