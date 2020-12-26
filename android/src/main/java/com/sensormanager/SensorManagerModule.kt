package com.sensormanager

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class SensorManagerModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
  private val orientationRecord: OrientationRecord = OrientationRecord(reactContext)
  private val thermometerRecord: ThermometerRecord = ThermometerRecord(reactContext)
  private val proximityRecord: ProximityRecord = ProximityRecord(reactContext)
  private val humidityRecord: HumidityRecord = HumidityRecord(reactContext)
  private val pressureRecord: PressureRecord = PressureRecord(reactContext)
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

     @ReactMethod
    fun startThermometer(freq: Int?) {
      thermometerRecord.start(freq ?: defaultRefresh)
    }

    @ReactMethod
    fun stopThermometer() {
      thermometerRecord.stop()
    }

     @ReactMethod
    fun startProximity(freq: Int?) {
      proximityRecord.start(freq ?: defaultRefresh)
    }

    @ReactMethod
    fun stopProximity() {
      proximityRecord.stop()
    }

     @ReactMethod
    fun startPressure(freq: Int?) {
      pressureRecord.start(freq ?: defaultRefresh)
    }

    @ReactMethod
    fun stopPressure() {
      pressureRecord.stop()
    }

     @ReactMethod
    fun startHumidity(freq: Int?) {
      humidityRecord.start(freq ?: defaultRefresh)
    }

    @ReactMethod
    fun stopHumidity() {
      humidityRecord.stop()
    }

}