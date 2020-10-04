package com.example.farmermate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    ImageView profile;
    View recompage, tablepage, weatherpage,locate,mon;
    Button co,co2,co3;

    //ส่วนที่กดข้าไปหน้าต่าง ๆ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        profile = (ImageView) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openuser();
            }
        });
        recompage = (View) findViewById(R.id.recompage);
        recompage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecomPage();
            }
        });
        tablepage = (View) findViewById(R.id.tablepage);
        tablepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTablePage();
            }
        });
        weatherpage = (View) findViewById(R.id.weatherpage);
        weatherpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeatherPage();
            }
        });
        locate = (View) findViewById(R.id.nearlocate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(HomePage.this, RetrieveMapActivity.class);
                startActivity(I);
            }
        });
        mon = (View) findViewById(R.id.view2);
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(HomePage.this, CalendarAc.class);
                startActivity(I);
            }
        });

    }

    public void  openuser() {
        Intent intent = new Intent(this, user.class);
        startActivity(intent);
    }
    public void  openWeatherPage() {
        Intent intent = new Intent(this, WeatherPage.class);
        startActivity(intent);
    }
    public void  openTablePage() {
        Intent intent = new Intent(this, CreateTable.class);
        startActivity(intent);
    }
    public void openRecomPage(){
        Intent intent = new Intent (this, RecomPage.class);
        startActivity(intent);
    }

}