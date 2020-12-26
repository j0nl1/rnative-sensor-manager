package com.sensormanager

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap

class HumidityRecord(reactContext: ReactApplicationContext) : BaseRecord(reactContext), SensorEventListener {
    private var humidityPercent: Int = 0

    override val eventName = "humidity"
    override val sensorType = Sensor.TYPE_RELATIVE_HUMIDITY

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == sensorType) {
            humidityPercent = event.values[0].toInt()
        }
        if (lastRefresh + eventFreq < System.currentTimeMillis()) {
            sendEvent()
        }
    }

    override fun createMap(): WritableMap {
        val map = Arguments.createMap()
        map.putInt("humidity", humidityPercent)
        return map
    }

}

