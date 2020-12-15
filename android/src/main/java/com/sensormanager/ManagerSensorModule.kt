package com.sensormanager

import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.*


class SensorManagerModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
  private val orientationRecord: OrientationRecord = OrientationRecord(reactContext)

  override fun getName(): String {
        return "SensorManager"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @ReactMethod
    fun startOrientation(freq: Int?) {
      if (freq !== null) {
        orientationRecord.startTrack(freq)
      } else {
        orientationRecord.startTrack(1000)
      }
    }

    @ReactMethod
    fun stopOrientation() {
      orientationRecord.stopTrack()
    }

}
