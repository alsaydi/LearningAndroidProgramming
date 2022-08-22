package com.example.appstart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SensorsViewModel extends ViewModel {
    private final MutableLiveData<List<Sensor>> sensorsLiveData = new MutableLiveData<>();
    private long selectedSensorId;

    public LiveData<List<Sensor>> getSensors(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            sensorsLiveData.setValue(sensors);
        }
        return sensorsLiveData;
    }

    public void selectItem(long sensorId) {
        if(sensorsLiveData.getValue().isEmpty()) {
            return;
        }

        selectedSensorId = sensorId;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LiveData<Sensor> getSelectedSensor() {
        for(Sensor sensor : sensorsLiveData.getValue()) {
            if(sensor.getId() == selectedSensorId) {
                return new MutableLiveData<>(sensor);
            }
        }
        return null;
    }
}
