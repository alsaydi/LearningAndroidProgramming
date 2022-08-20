package com.example.appstart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.appstart.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    private SensorManager sensorManager;
    private ListView sensorListView;
    private ListAdapter listAdapter;
    private List<Sensor> sensors;

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
        sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager != null) {
            sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            listAdapter = new ListAdapter(sensors, this.getActivity());
            binding.sensorsView.setAdapter(listAdapter);
        }
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

}