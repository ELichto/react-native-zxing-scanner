import ZxingScanner from './NativeZxingScanner';

export function showQrReader(callback : (result : string) => void ): void{
  ZxingScanner.showQrReader(callback);
}
export default ZxingScanner;
