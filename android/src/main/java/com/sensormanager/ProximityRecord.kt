package com.sensormanager

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap

class ProximityRecord(reactContext: ReactApplicationContext) : BaseRecord(reactContext), SensorEventListener {
  private var proximityValue: Int = 0

  override val eventName = "proximity"
  override val sensorType = Sensor.TYPE_PROXIMITY

  override fun onSensorChanged(event: SensorEvent) {
    if (event.sensor.type == sensorType) {
      proximityValue = event.values[0].toInt()
    }
    if (lastRefresh + eventFreq < System.currentTimeMillis()) {
      sendEvent()
    }
  }

  override fun createMap(): WritableMap {
    val map = Arguments.createMap()
    map.putInt("proximity", proximityValue)
    return map
  }

}
