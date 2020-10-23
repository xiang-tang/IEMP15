package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class SparrowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparrow);
        ImageSlider imageSlider = findViewById(R.id.spa_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.hs1,"House Sparrow", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hs2,"House Sparrow", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hs3,"House Sparrow", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hs4,"House Sparrow", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}