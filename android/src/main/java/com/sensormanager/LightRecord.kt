package com.sensormanager

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap

class LightRecord(reactContext: ReactApplicationContext) : BaseRecord(reactContext), SensorEventListener {
  private var lightValue: Int = 0

  override val eventName = "light"
  override val sensorType = Sensor.TYPE_LIGHT

  override fun onSensorChanged(event: SensorEvent) {
    if (event.sensor.type == sensorType) {
      lightValue = event.values[0].toInt()
    }
    if (lastRefresh + eventFreq < System.currentTimeMillis()) {
      sendEvent()
    }
  }

  override fun createMap(): WritableMap {
    val map = Arguments.createMap()
    map.putInt("lux", lightValue)
    return map
  }

}
