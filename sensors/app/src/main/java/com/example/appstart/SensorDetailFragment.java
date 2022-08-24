package com.example.appstart;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appstart.databinding.FragmentSensorDetailBinding;

public class SensorDetailFragment extends Fragment {

    private FragmentSensorDetailBinding binding;

    public static SensorDetailFragment newInstance() {
        return new SensorDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSensorDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SensorsViewModel sensorsViewModel = new ViewModelProvider(requireActivity()).get(SensorsViewModel.class);
        sensorsViewModel.getSelectedSensor().observe(getViewLifecycleOwner(), sensor -> {
            if(sensor == null) {
                return;
            }
            binding.sensorName.setText(String.format("%d - %s\n", sensor.getType(), sensor.getName()));
            binding.sensorMinDelay.setText(String.valueOf(sensor.getMinDelay()));
            binding.sensorPower.setText(String.valueOf(sensor.getPower()));
            binding.sensorRange.setText(String.valueOf(sensor.getMaximumRange()));
            binding.sensorResolution.setText(String.valueOf(sensor.getResolution()));
            binding.sensorVendor.setText(String.valueOf(sensor.getVendor()));
            binding.sensorVersion.setText(String.valueOf(sensor.getVersion()));
        });

        binding.buttonSensors.setOnClickListener(view1 -> NavHostFragment.findNavController(SensorDetailFragment.this)
                .navigate(R.id.detail_fragment_to_list_fragment));

        binding.showValues.setOnClickListener(view1 -> NavHostFragment.findNavController(SensorDetailFragment.this)
                .navigate(R.id.detail_fragment_to_values));

    }
}