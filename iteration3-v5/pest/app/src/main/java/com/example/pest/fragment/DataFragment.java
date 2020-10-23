package com.example.pest.fragment;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.PestInfor;
import com.example.pest.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DataFragment extends Fragment {
    View v;
    BarChart barChart;
    Geocoder geocoder;
    List<Address> addresses;
    ArrayList<Integer> turtleNumList ;
    ArrayList<Integer> gooseNumList ;
    ArrayList<Integer> goatNumList ;
    ArrayList<Integer> piegeonNumList ;
    ArrayList<Integer> ratNumList;
    ArrayList<Integer> spaNumList;
    ArrayList<String> turtleAddList;
    ArrayList<String> gooseAddList;
    ArrayList<String> goatAddList;
    ArrayList<String> piegeonAddList;
    ArrayList<String> ratAddList;
    ArrayList<String>spaAddList;

    BarDataSet barDataSet;
    BarData barData;
    ArrayList<BarEntry> pests;
    TextView tl;

    Spinner spin;
    PieChart pieChart;
    PieDataSet pieDataSet;
    PieData pieData;
    ArrayList<PieEntry> pieEntries;

    Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      /*  turtleNumList = new ArrayList<>();
        gooseNumList = new ArrayList<>();
        goatNumList = new ArrayList<>();
        piegeonNumList = new ArrayList<>();
        turtleAddList = new ArrayList<>();
        gooseAddList = new ArrayList<>();
        goatAddList = new ArrayList<>();
        piegeonAddList = new ArrayList<>();*/
        getActivity().setTitle("InvasiPests-Charts");
        v = inflater.inflate(R.layout.fragment_data, container, false);
      //  tl = v.findViewById(R.id.test);
        Bundle bundle =this.getArguments();
        button = v.findViewById(R.id.data_btn);
        int countTurtle = bundle.getInt("turtle");
        int countGoat = bundle.getInt("goat");
        int countGoose = bundle.getInt("goose");
        int countPiegeon = bundle.getInt("piegeon");
        int countrat = bundle.getInt("rat");
        int countspa = bundle.getInt("spar");


        String turtleAdd = bundle.getString("turtlradd");
        String gooseAdd = bundle.getString("canadaAdd");

        goatNumList = bundle.getIntegerArrayList("goatnum");
        piegeonNumList =bundle.getIntegerArrayList("pieNum");
        ratNumList = bundle.getIntegerArrayList("ratNum");
        spaNumList = bundle.getIntegerArrayList("spaNum");

        goatAddList = bundle.getStringArrayList("goatAdd");
        piegeonAddList = bundle.getStringArrayList("pieAdd");
        ratAddList = bundle.getStringArrayList("ratAdd");
        spaAddList = bundle.getStringArrayList("spaAdd");

        pests = new ArrayList<>();
        geocoder = new Geocoder(getActivity(), Locale.getDefault());


        ArrayList<String> pestInfor = new ArrayList<>();
        pestInfor.add("Turtle");
        pestInfor.add("Goat");
        pestInfor.add("Goose");
        pestInfor.add("Pigeon");

        String[] pestname = new String[]{"Turtle","Goat","Canada Goose","Pigeon","Rat","Sparrpw"};


        pests.add(new BarEntry(0, countTurtle));
        pests.add(new BarEntry(1, countGoat));
        pests.add(new BarEntry(2, countGoose));
        pests.add(new BarEntry(3, countPiegeon));
        pests.add(new BarEntry(4, countrat));
        pests.add(new BarEntry(5, countspa));


        barDataSet = new BarDataSet(pests, "Pest");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(20f);
        barData = new BarData(barDataSet);
        barChart = v.findViewById(R.id.barchart);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(pestname));
        xAxis.setLabelCount(pestname.length );
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextSize(9f);



        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Pest Total Number");
        barChart.animateY(1000);
        barChart.invalidate();

        spin = v.findViewById(R.id.data_spin);
        pieChart = v.findViewById(R.id.data_pie);

        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(countTurtle,turtleAdd));

        pieDataSet = new PieDataSet(pieEntries,"Turtle");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(20f);

        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setText("Pest Total Number");
        pieChart.animate();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = spin.getSelectedItem().toString();
                pieEntries = new ArrayList<>();
                if (name.equals("Goat")){
                    for (int i = 0; i < goatAddList.size(); i ++){
                        pieEntries.add(new PieEntry(goatNumList.get(i),goatAddList.get(i)));
                    }
                }
                else if (name.equals("Canada Goose")){
                        pieEntries.add(new PieEntry(countGoose,gooseAdd));
                }
                else if (name.equals("Pigeon")){
                    for (int i = 0; i < piegeonAddList.size(); i ++){
                        pieEntries.add(new PieEntry(piegeonNumList.get(i),piegeonAddList.get(i)));
                    }
                }
                else if (name.equals("Rat")){
                    for (int i = 0; i < ratAddList.size(); i ++){
                        pieEntries.add(new PieEntry(ratNumList.get(i),ratAddList.get(i)));
                    }
                }
                else if (name.equals("Sparrow")){
                    for (int i = 0; i < spaAddList.size(); i ++){
                        pieEntries.add(new PieEntry(spaNumList.get(i),spaAddList.get(i)));
                    }
                }

                else {
                    pieEntries.add(new PieEntry(countTurtle,turtleAdd));
                }

                pieDataSet = new PieDataSet(pieEntries,name);
                pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                pieDataSet.setValueTextColor(Color.BLACK);
                pieDataSet.setValueTextSize(20f);
                pieData.setValueFormatter(new PercentFormatter());
               // pieChart.setUsePercentValues(true);
                pieData = new PieData(pieDataSet);
                pieChart.setData(pieData);
                pieChart.getDescription().setText("Pest Total Number");
                pieChart.animate();
                pieChart.invalidate();
            }
        });

        return v;
    }

    /*public void getData() {
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Red-eared Slider Turtle")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa = pe.getLatitud();
                        String turtleL0 = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        countTurtle += num;
                        turtleNumList.add(num);
                        try {
                            addresses = geocoder.getFromLocation(turtleLaf, turtleLao, 1);
                            String postalCode = addresses.get(0).getPostalCode();
                            turtleAddList.add(postalCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Goat")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa = pe.getLatitud();
                        String turtleL0 = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        countGoat += num;
                        goatNumList.add(num);
                        try {
                            addresses = geocoder.getFromLocation(turtleLaf, turtleLao, 1);
                            String postalCode = addresses.get(0).getPostalCode();
                            goatAddList.add(postalCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Pigeon")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa = pe.getLatitud();
                        String turtleL0 = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        countPiegeon += num;
                        piegeonNumList.add(num);
                        try {
                            addresses = geocoder.getFromLocation(turtleLaf, turtleLao, 1);
                            String postalCode = addresses.get(0).getPostalCode();
                            piegeonAddList.add(postalCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Canada Goose")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa = pe.getLatitud();
                        String turtleL0 = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        countGoose += num;
                        gooseNumList.add(num);
                        try {
                            addresses = geocoder.getFromLocation(turtleLaf, turtleLao, 1);
                            String postalCode = addresses.get(0).getPostalCode();
                            gooseAddList.add(postalCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error));
    }*/

    public void barchart() {
      /*  pests.add(new BarEntry(2, countTurtle));
        pests.add(new BarEntry(4, countGoat));
        pests.add(new BarEntry(6, countGoose));
        pests.add(new BarEntry(8, countPiegeon));*/

        barDataSet = new BarDataSet(pests, "Pest");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barData = new BarData(barDataSet);
        barChart = v.findViewById(R.id.barchart);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Pest Total Number");
        barChart.animateY(1000);
        barChart.invalidate();
    }




}