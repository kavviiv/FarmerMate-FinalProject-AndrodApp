package com.example.farmermate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.farmermate.CreateTable.Dtail;
import static com.example.farmermate.CreateTable.Rec;
import static com.example.farmermate.CreateTable.Step;
import static com.example.farmermate.CreateTable.Warn;

public class ToDoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Todo> ToDoList;


    public ToDoAdapter(Context mContext, List<Todo> ToDoList) {
        this.mContext = mContext;
        this.ToDoList = ToDoList;
    }

    public ToDoAdapter(CreateTable createTable) {
    }
    public static  Todo get(int position) {
        return null;
    }

    public String getStep(){
        return Step;
    }
    public String getDtail(){
        return Dtail;
    }
    public String getRec(){
        return Rec;
    }
    public String getWarn(){
        return Warn;
    }


    @Override
    public int getCount() {
        return ToDoList.size();
    }

    @Override
    public Object getItem(int position) {
        return ToDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ToDoList.get(position).getId();
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_todo, null);
        TextView tvName = (TextView)v.findViewById(R.id.todo);
        ImageView imgV =(ImageView)v.findViewById(R.id.imgv);
        tvName.setText(ToDoList.get(position).getStep());
        imgV.setImageResource(R.drawable.riceim1);
        return v;
    }

}

