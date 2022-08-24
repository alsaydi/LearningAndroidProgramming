package com.example.appstart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appstart.databinding.FragmentSensorValuesBinding;

public class SensorValuesFragment extends Fragment implements SensorEventListener {

    private FragmentSensorValuesBinding binding;
    private SensorsViewModel sensorsViewModel;
    private Sensor sensor;
    private SensorManager sensorManager;


    public SensorValuesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSensorValuesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorsViewModel = new ViewModelProvider(this.getActivity()).get(SensorsViewModel.class);
        sensorsViewModel.getSelectedSensor().observe(getViewLifecycleOwner()
                , selectedSensor -> sensor = selectedSensor);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(sensor != null) {
            sensorManager.unregisterListener(this);
        }
        sensorManager = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(sensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        binding.event0.setText(String.valueOf(event.values[0]));
        binding.accuracy.setText(String.valueOf(event.accuracy));
        binding.time.setText(String.valueOf(event.timestamp));

        if(event.values.length>1) {
            binding.event1.setText(String.valueOf(event.values[1]));
        } if(event.values.length>2) {
            binding.event2.setText(String.valueOf(event.values[2]));
        } if(event.values.length>3) {
            binding.event3.setText(String.valueOf(event.values[3]));
        } if(event.values.length>4) {
            binding.event4.setText(String.valueOf(event.values[4]));
        } if(event.values.length>5) {
            binding.event5.setText(String.valueOf(event.values[5]));
        } if(event.values.length>6) {
            binding.event6.setText(String.valueOf(event.values[6]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}