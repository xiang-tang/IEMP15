package com.example.pest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UerListAdapter extends ArrayAdapter<User> {
    private Context mcontext;
    int mResource;

    public UerListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        mcontext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getUserName();
        int points = getItem(position).getTotalPoints();
        int times = getItem(position).getReportTimes();
        User user = new User(name,points,times);
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tname = (TextView)convertView.findViewById(R.id.l_name);
        TextView tpoints = (TextView)convertView.findViewById(R.id.l_points);
        TextView ttimes = (TextView)convertView.findViewById(R.id.l_times);

        tname.setText(name);
        tpoints.setText(String.valueOf(points));
        ttimes.setText(String.valueOf(times));

        return convertView;





    }
}
