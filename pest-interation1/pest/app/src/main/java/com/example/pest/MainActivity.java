package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

public class MainActivity extends AppCompatActivity {
    CardView guide;
    CardView identify;
    CardView map;
    CardView data;
    CardView setting;
    CardView report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identify = findViewById(R.id.identify);
        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Identify.class));
            }
        });
       /* Todo item = Todo.builder()
                .name("Build Android application")
                .description("Build an Android application using Amplify")
                .build();

        Amplify.DataStore.save(
                item,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getName()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
        );*/


    }
}