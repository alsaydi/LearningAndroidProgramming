package com.example.appstart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appstart.databinding.FragmentSensorDetailBinding;

public class SensorDetailFragment extends Fragment {

    private SensorDetailViewModel mViewModel;
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

        binding.buttonSensors.setOnClickListener(view1 -> NavHostFragment.findNavController(SensorDetailFragment.this)
                .navigate(R.id.detail_fragment_to_list_fragment));
    }
}