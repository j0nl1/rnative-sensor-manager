package com.sensormanager

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap

class ThermometerRecord(reactContext: ReactApplicationContext) : BaseRecord(reactContext), SensorEventListener {
  private var temperatureValue: Int = 0

  override val eventName = "temperature"
  override val sensorType = Sensor.TYPE_AMBIENT_TEMPERATURE

  override fun onSensorChanged(event: SensorEvent) {
    if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
      temperatureValue = event.values[0].toInt()
    }
    if (lastRefresh + eventFreq < System.currentTimeMillis()) {
      sendEvent()
    }
  }

  override fun createMap(): WritableMap {
    val map = Arguments.createMap()
    map.putInt("temperature", temperatureValue)
    return map
  }

}

