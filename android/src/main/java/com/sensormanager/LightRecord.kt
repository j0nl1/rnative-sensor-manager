package com.managersensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

class LightRecord(reactContext: ReactApplicationContext) : SensorEventListener {
  private val mReactContext: ReactContext = reactContext

  private val sensorManager: SensorManager = reactContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

  private var lastRefresh: Long = 0
  private var eventFreq: Int = 0
  private var lightValue: Int = 0

  override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

  override fun onSensorChanged(event: SensorEvent) {
    if (event.sensor.type == Sensor.TYPE_LIGHT) {
      lightValue = event.values[0].toInt()
    }
    if (lastRefresh + eventFreq < System.currentTimeMillis()) {
      sendEvent()
    }
  }

  @RequiresApi(Build.VERSION_CODES.KITKAT)
  fun start(refreshFreq: Int) {
    eventFreq = refreshFreq
    sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)?.also { lightSensor ->
      sensorManager.registerListener(
        this,
        lightSensor,
        SensorManager.SENSOR_DELAY_NORMAL,
        SensorManager.SENSOR_DELAY_UI
      )
    }
  }

  fun stop() {
    sensorManager.unregisterListener(this)
  }

  private fun sendEvent() {
    try {
      mReactContext
        .getJSModule(RCTDeviceEventEmitter::class.java)
        .emit("light", createOrientationMap())
    } catch (e: RuntimeException) {
      Log.e("ERROR", "java.lang.RuntimeException: Trying to invoke JS before CatalystInstance has been set!")
    }
  }

  private fun createOrientationMap(): WritableMap {
    val orientationMap = Arguments.createMap()
    orientationMap.putInt("lux", lightValue)
    return orientationMap
  }

}
