package com.example.pest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout linearLayout;
    TextView textView;
    TextView back;
    private IntroPref introPref;
    private int[] layouts;
    private  TextView[] dots;
    private MyviewPageAdapter myviewPageAdapter;
    SharedPreferences sharedPreferences;
    Boolean firsttime;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        sharedPreferences  = getSharedPreferences("Mypre",MODE_PRIVATE);
        editor = sharedPreferences.edit();




        introPref = new IntroPref(this);
        firsttime = sharedPreferences.getBoolean("firsttime",true);
        if (!firsttime){
            launchHomeScreen();
            finish();
        }

        viewPager = findViewById(R.id.viewpageslider);
        textView = findViewById(R.id.tvtext);
        linearLayout = findViewById(R.id.layoutDots);
        back = findViewById(R.id.back);


        layouts = new int[]{
                R.layout.intro_one,
                R.layout.intro_two,
                R.layout.intro_three
        };

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length){
                    viewPager.setCurrentItem(current);
                }else {
                    launchHomeScreen();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getpitem(-1);
                if (current < layouts.length){
                    viewPager.setCurrentItem(current);
                }else {
                    launchHomeScreen();
                }
            }
        });

        myviewPageAdapter = new MyviewPageAdapter();
        viewPager.setAdapter(myviewPageAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        addBottomDots(0);

    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + 1;
    }

    private int getpitem(int i){
        return viewPager.getCurrentItem() - 1;
    }

    public class MyviewPageAdapter extends PagerAdapter{
       LayoutInflater layoutInflater;
       public MyviewPageAdapter(){}


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

           layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           View view = layoutInflater.inflate(layouts[position],container,false);
           container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View)object;
            container.removeView(view);
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if (position == 0){
                back.setText("");
            }
            if (position != 0 ){
                back.setText("BACK");
            }
            if (position == layouts.length - 1){
                textView.setText("START");

            }else {
                textView.setText("NEXT");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private void addBottomDots(int currentPage){
        dots = new TextView[layouts.length];
        int [] activecolor = getResources().getIntArray(R.array.active);
        int [] intivecolor = getResources().getIntArray(R.array.inactive);
        linearLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(intivecolor[currentPage]);
            linearLayout.addView(dots[i]);
        }

        if (dots.length > 0){
            dots[currentPage].setTextColor(activecolor[currentPage]);
        }
    }

    private void launchHomeScreen() {
        //introPref.setIsFirstTimeLaunch(false);
        firsttime = false;
        editor.putBoolean("firsttime",firsttime);
        editor.apply();
        startActivity(new Intent(IntroActivity.this,LoginActivity.class));
        finish();
    }
}