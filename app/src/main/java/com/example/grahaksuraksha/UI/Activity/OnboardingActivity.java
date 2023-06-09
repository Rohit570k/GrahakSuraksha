package com.example.grahaksuraksha.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.grahaksuraksha.Adapter.SliderAdapter;
import com.example.grahaksuraksha.R;
import com.example.grahaksuraksha.UI.Activity.Main.MainActivity;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted, nextbtn ,skipbtn;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);
        nextbtn = findViewById(R.id.next_btn);
        skipbtn = findViewById(R.id.skip_btn);

        //call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        //Click listener for the button
        letsGetStarted.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
        skipbtn.setOnClickListener(view ->{
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        nextbtn.setOnClickListener((view) -> {
            viewPager.setCurrentItem(currentPosition + 1);
            });
    }


    private void addDots(int position) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(R.color.dotsColor));

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.buttonColor));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            currentPosition = position;

            if (position == 0) {
                nextbtn.setVisibility(View.VISIBLE);
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                nextbtn.setVisibility(View.VISIBLE);
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else if (position == 2) {
                nextbtn.setVisibility(View.VISIBLE);
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.bottom_anim);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
                nextbtn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}