package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class TurtleShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turtle_show);
        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.a1,"Red-eared slider Turtle", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.a2,"Red-eared slider Turtle", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.a3,"Red-eared slider Turtle", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.a4,"Red-eared slider Turtle", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
    }
}