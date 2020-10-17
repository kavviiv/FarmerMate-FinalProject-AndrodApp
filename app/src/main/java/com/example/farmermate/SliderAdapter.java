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
            R.drawable.carl,
            R.drawable.weat,
            R.drawable.lok,
            R.drawable.pay
    };

    public String[] slide_heading={
            "แนะนำพันธุ์ข้าว",
            "สร้างตารางงาน",
            "ดูสภาพอากาศ",
            "ดูพื้นที่นาใกล้เคียง",
            "บันทึกค่าใช้จ่าย"
    };

    public String[] slide_des ={
            "แนะนำพันธุ์ข้าวที่เหมาะสมกับ \n พื้นที่ปลูก ลักษณะดินและแหล่งน้ำ",
            "สร้างตารางงานการทำนา \n ประกอบด้วยงานที่ต้องทำทั้งหมด \n ",
            "ดูสภาพอากาศแบบต่อวันและล่วงหน้า \nโดยรับข้อมูลพื้นที่ผ่าน GPS บนสมาร์ทโฟนของผู้ใช้",
            "ดูพื้นที่นาใกล้เคียงว่าปลูกข้าวพันธุ์อะไร \n ",
            "บันทึกค่าใช้จ่ายในแต่ละขั้นตอนการทำนา \n เพื่อการสรุปถึงค่าใช้จ่ายในการทำนาแต่ละครั้ง"
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
        TextView slideDesc = (TextView) view.findViewById(R.id.slide_des);

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
