package com.sensormanager

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap

class PressureRecord(reactContext: ReactApplicationContext) : BaseRecord(reactContext), SensorEventListener {
  private var pressureValue: Int = 0

  override val eventName = "pressure"
  override val sensorType = Sensor.TYPE_PRESSURE

  override fun onSensorChanged(event: SensorEvent) {
    if (event.sensor.type == sensorType) {
      pressureValue = event.values[0].toInt()
    }
    if (lastRefresh + eventFreq < System.currentTimeMillis()) {
      sendEvent()
    }
  }

  override fun createMap(): WritableMap {
    val map = Arguments.createMap()
    map.putInt("pressure", pressureValue)
    return map
  }

}