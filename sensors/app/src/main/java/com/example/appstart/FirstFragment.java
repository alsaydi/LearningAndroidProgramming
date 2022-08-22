package com.example.appstart;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.appstart.databinding.FragmentFirstBinding;

import java.util.List;
import java.util.Objects;

public class FirstFragment extends Fragment implements AdapterView.OnItemClickListener {
    private FragmentFirstBinding binding;
    private ListAdapter listAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        buildSensorsList();
        return binding.getRoot();

    }

    private void buildSensorsList() {
        SensorsViewModel sensorsViewModel = new ViewModelProvider(requireActivity()).get(SensorsViewModel.class);
        Activity activity = this.getActivity();
        sensorsViewModel.getSensors(this.requireActivity()).observe(getViewLifecycleOwner(), new Observer<List<Sensor>>() {
            @Override
            public void onChanged(List<Sensor> sensors) {
                listAdapter = new ListAdapter(sensors, activity);
                binding.sensorsView.setAdapter(listAdapter);
            }
        });
        binding.sensorsView.setOnItemClickListener(this);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(view1 ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_sensor_list_to_detail);
    }
}