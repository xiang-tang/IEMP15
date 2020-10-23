package com.example.pest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.PestInfor;
import com.amplifyframework.datastore.generated.model.Report;
import com.example.pest.fragment.DataFragment;
import com.example.pest.fragment.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AnalysisActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment select;
    private MapFragment map;
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

    ArrayList<HashMap> userre;
    SharedPreferences preferences;


    int countTurtle = 0;
    int countGoat = 0;
    int countGoose = 0;
    int countPiegeon = 0;
    int countRat = 0;
    int countSpa = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        bottomNavigationView = findViewById(R.id.bot_nav);
        turtleNumList = new ArrayList<>();
        gooseNumList = new ArrayList<>();
        goatNumList = new ArrayList<>();
        piegeonNumList = new ArrayList<>();
        turtleAddList = new ArrayList<>();
        gooseAddList = new ArrayList<>();
        goatAddList = new ArrayList<>();
        piegeonAddList = new ArrayList<>();
        ratNumList = new ArrayList<>();
        spaNumList = new ArrayList<>();
        ratAddList = new ArrayList<>();
        spaAddList = new ArrayList<>();
        userre = new ArrayList<>();
        preferences  = getSharedPreferences("userRecord",MODE_PRIVATE);
        String userName = preferences.getString("uname","");

        Amplify.API.query(
                ModelQuery.list(Report.class, Report.USER_NAME.ne("")),
                response -> {
                    for (Report u : response.getData()) {
                        if (u.getUserName().equals(userName)){
                            String uLocation  = u.getLocation();
                            String[] s = uLocation.split(" ");
                            String la = s[1];
                            String[] ls = la.split("L");
                            String laa = ls[0];
                            String lo = s[3];
                            float turtleLaf = Float.parseFloat(la);
                            float turtleLao = Float.parseFloat(lo);
                            LatLng turtle = new LatLng(turtleLaf,turtleLao);
                            String pestN = u.getPest();
                            String date = u.getDate().toString();
                            HashMap<Object,Object> user = new HashMap();
                            user.put("lo",turtle);
                            user.put("pname",pestN);
                            user.put("date",date);
                            user.put("uname",userName);
                            userre.add(user);
                        }
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );




        geocoder = new Geocoder(AnalysisActivity.this, Locale.getDefault());

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Red-eared Slider Turtle")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
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
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("House Sparrow")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        countSpa += num;
                        spaNumList.add(num);
                        try {
                            addresses = geocoder.getFromLocation(turtleLaf, turtleLao, 1);
                            String postalCode = addresses.get(0).getPostalCode();
                            spaAddList.add(postalCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Black Rat")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        countRat += num;
                        ratNumList.add(num);
                        try {
                            addresses = geocoder.getFromLocation(turtleLaf, turtleLao, 1);
                            String postalCode = addresses.get(0).getPostalCode();
                            ratAddList.add(postalCode);
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
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
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
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
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
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
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
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_map:
                        select = new MapFragment();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("name",userName);
                        select.setArguments(bundle1);
                        break;
                    case R.id.nav_data:
                        select = new DataFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("turtle",countTurtle);
                        bundle.putInt("goat",countGoat);
                        bundle.putInt("goose",countGoose);
                        bundle.putInt("piegeon",countPiegeon);
                        bundle.putInt("rat",countRat);
                        bundle.putInt("spar",countSpa);
                        bundle.putString("turtlradd", turtleAddList.get(0));
                        bundle.putString("canadaAdd",gooseAddList.get(0));
                        bundle.putIntegerArrayList("goatnum",goatNumList);
                        bundle.putIntegerArrayList("pieNum",piegeonNumList);
                        bundle.putIntegerArrayList("ratNum",ratNumList);
                        bundle.putIntegerArrayList("spaNum",spaNumList);
                        bundle.putStringArrayList("goatAdd",goatAddList);
                        bundle.putStringArrayList("pieAdd",piegeonAddList);
                        bundle.putStringArrayList("ratAdd",ratAddList);
                        bundle.putStringArrayList("spaAdd",spaAddList);
                        select.setArguments(bundle);
                        break;

                }
                if (select != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.ana,select).commit();
                }
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.ana,new MapFragment()).commit();
    }
}