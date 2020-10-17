package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class PigeonShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigeon_show);
        ImageSlider imageSlider = findViewById(R.id.pi_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.p1,"Pigeons", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.p2,"Pigeons", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.p3,"Pigeons", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.p4,"Pigeons", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}