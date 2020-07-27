package com.example.farmermate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //arrsy เก็บ icon
    public int[] slide_image ={
            R.drawable.leaf1,
            R.drawable.tree1,
            R.drawable.leaf1
    };

    public String[] slide_heading={
            "คำอธิบาย",
            "คำอธิบาย",
            "คำอธิบาย"
    };

    public String[] slide_des ={
            "คำอธิบาย การใช้งานแอปพลเิเชัน",
            "เคำอธิบาย การใช้งานแอปพลเิเชัน",
            "คำอธิบาย การใช้งานแอปพลเิเชัน"
    };


    @Override
    public int getCount(){
        return slide_heading.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_head);
        TextView slideDesc = (TextView) view.findViewById(R.id.slid_des);

        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setText(slide_heading[position]);
        slideDesc.setText(slide_des[position]);
        container.addView(view);

        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
