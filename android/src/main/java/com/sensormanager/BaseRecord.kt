package com.sensormanager

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

abstract class BaseRecord(reactContext: ReactApplicationContext) : SensorEventListener {
  private val mReactContext: ReactContext = reactContext
  private val sensorManager: SensorManager = reactContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

  abstract val eventName: String

  open val sensorType: Int = 0
  open var eventFreq: Int = 0
  open var lastRefresh: Long = 0

  open fun start(refreshFreq: Int) {
    eventFreq = refreshFreq
    sensorManager.getDefaultSensor(sensorType)?.also { sensor ->
      sensorManager.registerListener(
        this,
        sensor,
        SensorManager.SENSOR_DELAY_NORMAL
      )
    }
  }

  open fun stop() {
    sensorManager.unregisterListener(this)
  }

  open fun sendEvent() {
    try {
      mReactContext
        .getJSModule(RCTDeviceEventEmitter::class.java)
        .emit(eventName, createMap())
    } catch (e: RuntimeException) {
      Log.e("ERROR", "java.lang.RuntimeException: Trying to invoke JS before CatalystInstance has been set!")
    }
  }

  abstract fun createMap(): WritableMap

  override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

}
