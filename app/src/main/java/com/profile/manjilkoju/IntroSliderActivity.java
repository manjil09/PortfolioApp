package com.profile.manjilkoju;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class IntroSliderActivity extends AppCompatActivity {
    TextView tvSkip,tvNext;
    ViewPager viewPager;
    int[] layouts;
    MyViewPagerAdapter viewPagerAdapter;
    IntroSliderPref introSliderPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);

        introSliderPref = new IntroSliderPref(this);
        //directly starts the MainActivity if the app isn't opened for the first time
        if (!introSliderPref.isFirstTime()){
            launchMainActivity();
            finish();
        }

        tvSkip = findViewById(R.id.tvSkip);
        tvNext = findViewById(R.id.tvNext);
        viewPager = findViewById(R.id.viewPager);

        //storing the layout resource files for each slider page in a variable which can be inflated using the view pager adapter
        layouts = new int[]{
                R.layout.intro_one,
                R.layout.intro_two,
                R.layout.intro_three
        };

        //on click listener to launch the main activity when skip is clicked
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMainActivity();
            }
        });

        //on click listener to inflate the next slider page when next is clicked
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem();
                if (current < layouts.length){
                    viewPager.setCurrentItem(current);
                }else{
                    launchMainActivity();
                }
            }
        });


        viewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);//setting PagerAdapter for the ViewPager
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //replacing next with start when last slider page is inflated
            if (position == layouts.length - 1){
                tvNext.setText(R.string.start);
            }else{
                tvNext.setText(R.string.next);
            }
        }

        @Override
        public void onPageSelected(int position) {}

        @Override
        public void onPageScrollStateChanged(int state) {}
    };


    public int getItem(){
        return viewPager.getCurrentItem() + 1;
    }

    //method to start the main activity when skip or start is clicked
    public void launchMainActivity(){
        introSliderPref.setIsFirstTime(false);//calling the method in IntroSliderPref to set the value in the shared preference 'false'
        Intent intent = new Intent(IntroSliderActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    //creating an inner class MyViewPagerAdapter which extends PagerAdapter
    public class MyViewPagerAdapter extends PagerAdapter {
        LayoutInflater layoutInflater;

        public MyViewPagerAdapter(){}

        @NonNull
        @Override
        //creates the page for the given position
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        //returns the number of slider pages available
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        //remove the page for the given position
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}