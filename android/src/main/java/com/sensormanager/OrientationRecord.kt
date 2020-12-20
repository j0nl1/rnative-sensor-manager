package com.sensormanager

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.facebook.react.bridge.*

class OrientationRecord(reactContext: ReactApplicationContext) : BaseRecord(reactContext), SensorEventListener {

  private val sensorManager: SensorManager = reactContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
  private val accelerometerReading = FloatArray(3)
  private val magnetometerReading = FloatArray(3)

  override val eventName = "orientation"

  private val rotationMatrix = FloatArray(9)
  private val orientationAngles = FloatArray(3)

  override fun onSensorChanged(event: SensorEvent) {
    if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
      System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.size)
    } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
      System.arraycopy(event.values, 0, magnetometerReading, 0, magnetometerReading.size)
    }
    if (lastRefresh + eventFreq < System.currentTimeMillis()) {
      updateOrientation()
      sendEvent()
    }
  }

  override fun start(refreshFreq: Int) {
    eventFreq = refreshFreq
    sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
      sensorManager.registerListener(
        this,
        accelerometer,
        SensorManager.SENSOR_DELAY_NORMAL
      )
    }
    sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also { magneticField ->
      sensorManager.registerListener(
        this,
        magneticField,
        SensorManager.SENSOR_DELAY_NORMAL
      )
    }
  }

  override fun createMap(): WritableMap {
    val orientationMap = Arguments.createMap()

    var azimuth = (Math.toDegrees(orientationAngles[0].toDouble()) % 360.0f)
    var pitch = (Math.toDegrees(orientationAngles[1].toDouble()) % 360.0f)
    var roll = (Math.toDegrees(orientationAngles[2].toDouble()) % 360.0f)

    if (azimuth < 0) azimuth = 360 - (0 - azimuth)
    if (pitch < 0) pitch = 360 - (0 - pitch)
    if (roll < 0) roll = 360 - (0 - roll)

    orientationMap.putDouble("azimuth", azimuth)
    orientationMap.putDouble("pitch", pitch)
    orientationMap.putDouble("roll", roll)
    return orientationMap
  }

  private fun updateOrientation() {
    SensorManager.getRotationMatrix(
      rotationMatrix,
      null,
      accelerometerReading,
      magnetometerReading
    )
    SensorManager.getOrientation(rotationMatrix, orientationAngles)
    lastRefresh = System.currentTimeMillis()
  }

}
