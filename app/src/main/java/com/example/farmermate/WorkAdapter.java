package com.example.farmermate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.farmermate.CalendarAc.Day1;
import static com.example.farmermate.CalendarAc.Detail;
import static com.example.farmermate.CalendarAc.Reco;
import static com.example.farmermate.CalendarAc.Step1;
import static com.example.farmermate.CalendarAc.Warni;
import static com.example.farmermate.CreateTable.Day;
import static com.example.farmermate.CreateTable.Dtail;
import static com.example.farmermate.CreateTable.Rec;
import static com.example.farmermate.CreateTable.Step;
import static com.example.farmermate.CreateTable.Warn;
import static com.example.farmermate.RecomPage.Advantage;
import static com.example.farmermate.RecomPage.Description;
import static com.example.farmermate.RecomPage.Name;
import static com.example.farmermate.RecomPage.Products;

public class WorkAdapter extends BaseAdapter {
    private Context aContext;
    private ArrayList<Work> Work;

    public WorkAdapter(Context aContext, ArrayList<Work> Work) {
        this.aContext = aContext;
        this.Work = Work;
    }

    public int getDay1(){
        return Day1;
    }
    public static String getStep1(){
        return Step1;
    }
    public String getDetail(){
        return Detail;
    }
    public String getReco(){
        return Reco;
    }
    public String getWarni(){
        return Warni;
    }



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
