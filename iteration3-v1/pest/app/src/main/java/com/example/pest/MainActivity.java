package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Feedback;
import com.amplifyframework.datastore.generated.model.PestInfor;
import com.amplifyframework.datastore.generated.model.Report;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CardView guide;
    CardView identify;
    CardView pest_infor;
    CardView data;
    CardView feedback;
    CardView report;
    CardView about;
    TextView uname;
    SharedPreferences preferences;
    ArrayList<String> userNameList;
    ArrayList<Integer> userPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identify = findViewById(R.id.identify);
        about = findViewById(R.id.About);
        userNameList = new ArrayList<>();
        userPoints = new ArrayList<>();
        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Identify.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
            }
        });

        feedback = findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LeaderActivity.class);
                intent.putStringArrayListExtra("nameList",userNameList);
                intent.putIntegerArrayListExtra("points",userPoints);
                startActivity(intent);
            }
        });

        guide = findViewById(R.id.guide);
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GuideActivity.class));
            }
        });

        pest_infor = findViewById(R.id.pest_infor);
        pest_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PestInforActivity.class));
            }
        });

        data = findViewById(R.id.data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AnalysisActivity.class));
            }
        });

        uname = findViewById(R.id.mainname);
        preferences  = getSharedPreferences("userRecord",MODE_PRIVATE);
        String userName = preferences.getString("uname","");
        uname.setText("Welcome to InvasiPests! " + userName);



        Amplify.API.query(
                ModelQuery.list(Report.class, Report.USER_NAME.ne("")),
                response -> {
                    for (Report u : response.getData()) {
                        String name = u.getUserName();
                        int points = u.getUserScore();
                        userNameList.add(name);
                        userPoints.add(points);
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );




      /*  Report report = Report.builder()
                .useemail("test")
                .userName("test")
                .userScore(10)
                .build();
        Amplify.DataStore.save(
                report,
                success -> Log.i("Tutorial", "Saved item:"),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error));*/
        /*getPestInfor("5cec54f9-951f-4330-b283-d9ea9e3c420c");*/
        /*Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Red-eared Slider Turtle")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        Log.i("MyAmplifyApp", pe.getLatitud());
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );*/

/*
        PestInfor p7 = PestInfor.builder()
                .name("Pigeon")
                .latitud("-37.851")
                .longtitud("143.531")
                .year("2002")
                .num(20020)
                .build();
        PestInfor p8 = PestInfor.builder()
                .name("Pigeon")
                .latitud("-34.555")
                .longtitud("142.151")
                .year("2008")
                .num(6580)
                .build();
        PestInfor p9 = PestInfor.builder()
                .name("Pigeon")
                .latitud("-36.55")
                .longtitud("141.55")
                .year("2008")
                .num(2007)
                .build();


        Amplify.DataStore.save(
                p7,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getName()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error));
        Amplify.DataStore.save(
                p8,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getName()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error));
        Amplify.DataStore.save(
                p9,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getName()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error));*/
       /*

        Amplify.DataStore.save(
                item,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getName()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
        );*/

     /*
        */

    }

  /*  private void getPestInfor(String id){

        Amplify.API.query(
                ModelQuery.get(PestInfor.class, id),
                response -> Log.i("MyAmplifyApp", ((PestInfor) response.getData()).getLatitud()),
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );
    }*/
}