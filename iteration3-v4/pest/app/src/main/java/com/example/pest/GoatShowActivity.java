package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class GoatShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goat_show);
        ImageSlider imageSlider = findViewById(R.id.goat_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.g1,"Goat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.g2,"Goat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.g3,"Goat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.g4,"Goat", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}