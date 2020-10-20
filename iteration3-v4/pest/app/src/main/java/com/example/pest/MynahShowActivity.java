package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MynahShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynah_show);
        ImageSlider imageSlider = findViewById(R.id.mynash_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.m1,"Indian Myna", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.m2,"Indian Myna", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.m3,"Indian Myna", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.m4,"Indian Myna", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}