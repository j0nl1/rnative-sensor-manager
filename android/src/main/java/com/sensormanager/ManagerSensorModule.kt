package com.sensormanager

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class SensorManagerModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
  private val orientationRecord: OrientationRecord = OrientationRecord(reactContext)
  private val lightRecord: LightRecord = LightRecord(reactContext)
  private val defaultRefresh: Int = 1000

  override fun getName(): String {
        return "SensorManager"
    }

    @ReactMethod
    fun startOrientation(freq: Int?) {
      orientationRecord.start(freq ?: defaultRefresh)
    }

    @ReactMethod
    fun stopOrientation() {
      orientationRecord.stop()
    }

    @ReactMethod
    fun startLight(freq: Int?) {
      lightRecord.start(freq ?: defaultRefresh)
    }

    @ReactMethod
    fun stopLight() {
      lightRecord.stop()
    }

}