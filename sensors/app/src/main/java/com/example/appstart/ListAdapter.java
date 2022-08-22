package com.example.appstart;

import android.app.Activity;
import android.hardware.Sensor;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class ListAdapter extends BaseAdapter implements android.widget.ListAdapter {

    private final List<Sensor> sensors;
    private final Activity activity;

    public ListAdapter(List<Sensor> sensors, Activity activity) {
        this.sensors = sensors;
        this.activity = activity;
    }

    @Override
    public boolean hasStableIds() { return true; }


    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return i >= 0 && i < sensors.size();
    }

    @Override
    public int getCount() {
        return sensors.size();
    }

    @Override
    public Object getItem(int i) {
        if(sensors == null || i < 0 || i >= sensors.size()) {
            return null;
        }
        return sensors.get(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public long getItemId(int i) {
        Sensor sensor = (Sensor) getItem(i);
        if (sensor == null) {
            return -1L;
        }
        return sensor.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.sensors_list, parent, false);
        }
        TextView sensorName = convertView.findViewById(R.id.sensor_name);
        sensorName.setText(((Sensor)getItem(position)).getName());
        return convertView;
    }

    @Override
    public boolean isEmpty() {
        return sensors == null || sensors.isEmpty();
    }
}
