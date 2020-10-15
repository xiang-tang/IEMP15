package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class RatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat);
        ImageSlider imageSlider = findViewById(R.id.rat_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.br1,"Black Rat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.br2,"Black Rat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.br3,"Black Rat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.br4,"Black Rat", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}