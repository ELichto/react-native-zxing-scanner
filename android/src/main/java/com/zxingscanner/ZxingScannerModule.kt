package com.zxingscanner

import com.facebook.react.module.annotations.ReactModule
import android.content.Context
import android.content.SharedPreferences
import com.zxingscanner.NativeZxingScannerSpec
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ActivityEventListener
import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Callback
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

@ReactModule(name = ZxingScannerModule.NAME)
class ZxingScannerModule(reactContext: ReactApplicationContext) :
  NativeZxingScannerSpec(reactContext) {

  override fun getName(): String {
    return NAME
  }

    private var mCallback: Callback? = null

  override fun showQrReader(callback: Callback) {

    val reactContext = getReactApplicationContext()
        mCallback = callback
        val integrator = IntentIntegrator(getCurrentActivity())
        integrator.setOrientationLocked(true)
        integrator.setBeepEnabled(false)
        integrator.setCaptureActivity(ContinuousCaptureActivity::class.java)
        integrator.initiateScan()
        reactContext.addActivityEventListener(object : ActivityEventListener {
            override fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
                val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

println("Contents: ${result.contents}")
println("Barcode Image Path: ${result.barcodeImagePath}")
                mCallback?.invoke(result?.contents ?: "test")
                reactContext.removeActivityEventListener(this)
            }

            override fun onNewIntent(intent: Intent?) {
                // No-op
            }
        })

  }

  companion object {
    const val NAME = "ZxingScanner"
  }
}
