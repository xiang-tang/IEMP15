package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Feedback;
import com.amplifyframework.datastore.generated.model.PestInfor;
import com.amplifyframework.datastore.generated.model.Report;

public class MainActivity extends AppCompatActivity {
    CardView guide;
    CardView identify;
    CardView pest_infor;
    CardView data;
    CardView feedback;
    CardView report;
    CardView about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identify = findViewById(R.id.identify);
        about = findViewById(R.id.About);
        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Identify.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
            }
        });

        feedback = findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FeedbackActivity.class));
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