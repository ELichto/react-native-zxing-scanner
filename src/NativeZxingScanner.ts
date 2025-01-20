import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  showQrReader(callback: (s: string) => void): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ZxingScanner');
