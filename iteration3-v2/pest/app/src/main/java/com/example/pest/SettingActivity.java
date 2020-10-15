package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {
    CardView set_about;
    CardView fed_about;
    Button button;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.setTitle("InvasiPests-Setting");
        set_about = findViewById(R.id.set_about);
        set_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,AboutActivity.class));
            }
        });

        fed_about = findViewById(R.id.set_feedback);
        fed_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,FeedbackActivity.class));
            }
        });
        preferences  = getSharedPreferences("userRecord",MODE_PRIVATE);
        editor = preferences.edit();
        button = findViewById(R.id.siginout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("autologin",false);
                editor.commit();
                startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}