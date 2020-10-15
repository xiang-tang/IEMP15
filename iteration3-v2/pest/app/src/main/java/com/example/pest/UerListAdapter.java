package com.example.pest;

import android.content.Context;
import android.graphics.Color;
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
    String uname ;


    public UerListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects,String uname) {
        super(context, resource, objects);
        mcontext = context;
        mResource = resource;
        this.uname = uname;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getUserName();
        int points = getItem(position).getTotalPoints();
        int times = getItem(position).getReportTimes();
        int rank = getItem(position).getRank();
        User user = new User(name,points,times);
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView tname = (TextView)convertView.findViewById(R.id.l_name);
        TextView tpoints = (TextView)convertView.findViewById(R.id.l_points);
        TextView ttimes = (TextView)convertView.findViewById(R.id.l_times);
        TextView trank = (TextView)convertView.findViewById(R.id.l_rank);

        if (uname.equals(name)&& uname != null){
            tname.setText(name);
            tname.setBackgroundColor(Color.parseColor("#F1B27D"));
            tpoints.setText(String.valueOf(points));
            tpoints.setBackgroundColor(Color.parseColor("#F1B27D"));
            ttimes.setText(String.valueOf(times));
            ttimes.setBackgroundColor(Color.parseColor("#F1B27D"));
            trank.setText(String.valueOf(rank));
            trank.setBackgroundColor(Color.parseColor("#F1B27D"));
        }else {
        tname.setText(name);
        tpoints.setText(String.valueOf(points));
        ttimes.setText(String.valueOf(times));
        trank.setText(String.valueOf(rank));
        }
        return convertView;

    }
}
