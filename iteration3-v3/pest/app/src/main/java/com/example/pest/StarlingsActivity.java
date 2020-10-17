package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class StarlingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starlings);
        ImageSlider imageSlider = findViewById(R.id.star_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.cs1,"Common Starlings", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.cs2,"Common Starlings", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.cs3,"Common Starlings", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.cs4,"Common Starlings", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}