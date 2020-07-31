package com.example.farmermate;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Rice2 extends AppCompatActivity {
    private ViewPager rSlideViewPager;
    private LinearLayout rDotLayout;
    private TextView[] mDots;
    private SliderAdapter sliderAdapter;

    private Button mNextbutt;
    private Button  mBackbutt;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice2);

        rSlideViewPager = (ViewPager) findViewById(R.id.slideviewPager);
        rDotLayout = (LinearLayout) findViewById(R.id.dotlayout);

        mBackbutt = (Button) findViewById(R.id.backbutt);
        mNextbutt = (Button) findViewById(R.id.nextbutt) ;

        sliderAdapter = new SliderAdapter(this);
        rSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator( 0);

        rSlideViewPager.addOnPageChangeListener(viewListener);

        mNextbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });

        mBackbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });


    }


    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        for (int i =0; i< mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#648226;"));
            mDots[i].setTextSize(24);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            rDotLayout.removeAllViews();
            rDotLayout.addView(mDots[i]);

        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.purple));

        }



    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            mCurrentPage = i;
            if (i == 0){
                mNextbutt.setEnabled(true);
                mBackbutt.setEnabled(false);

                mBackbutt.setVisibility(View.INVISIBLE);

                mBackbutt.setText("");
                mNextbutt.setText("Next");
            }
            else if(i == mDots.length -1 ){
                mNextbutt.setEnabled(true);
                mBackbutt.setEnabled(true);

                mBackbutt.setVisibility(View.VISIBLE);

                mBackbutt.setText("Back");
                mNextbutt.setText("Finish");
                mNextbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openMainPage();
                    }
                });
            }
            else {
                mNextbutt.setEnabled(true);
                mBackbutt.setEnabled(true);
                mBackbutt.setVisibility(View.VISIBLE);
                mBackbutt.setText("Back");
                mNextbutt.setText("Next");

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void openMainPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}