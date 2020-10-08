package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class GooseShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goose_show);
        ImageSlider imageSlider = findViewById(R.id.goose_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.c1,"Canada Goose", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.c2,"Canada Goose", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.c3,"Canada Goose", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.c4,"Canada Goose", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}