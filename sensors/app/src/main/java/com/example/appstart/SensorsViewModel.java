package com.example.appstart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Objects;

public class SensorsViewModel extends ViewModel {
    private final MutableLiveData<List<Sensor>> sensorsLiveData = new MutableLiveData<>();
    private int sensorType;

    public LiveData<List<Sensor>> getSensors(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            sensorsLiveData.setValue(sensors);
        }
        return sensorsLiveData;
    }

    public void selectItem(long sensorType) {
        if(Objects.requireNonNull(sensorsLiveData.getValue()).isEmpty()) {
            return;
        }

        this.sensorType = (int)sensorType;
    }

    public LiveData<Sensor> getSelectedSensor() {
        for(Sensor sensor : Objects.requireNonNull(sensorsLiveData.getValue())) {
            if(sensor.getType() == sensorType) {
                return new MutableLiveData<>(sensor);
            }
        }
        return null;
    }
}
