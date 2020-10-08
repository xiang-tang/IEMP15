package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PestInforActivity extends AppCompatActivity {
    CardView turtle;
    CardView goat;
    CardView goose;
    CardView pigeon;
    CardView mynah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_infor);
        this.setTitle("InvasiPests-Pest Gallery");

        turtle = findViewById(R.id.turtle_infor);
        turtle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PestInforActivity.this,TurtleShowActivity.class));
            }
        });

        goat = findViewById(R.id.goat_infor);
        goat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PestInforActivity.this,GoatShowActivity.class));
            }
        });

        goose = findViewById(R.id.goose_infor);
        goose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PestInforActivity.this,GooseShowActivity.class));
            }
        });

        pigeon = findViewById(R.id.pigeninfor);
        pigeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PestInforActivity.this,PigeonShowActivity.class));
            }
        });

        mynah = findViewById(R.id.mynah_infor);
        mynah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PestInforActivity.this,MynahShowActivity.class));
            }
        });

    }
}