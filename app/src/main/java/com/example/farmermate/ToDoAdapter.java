package com.example.farmermate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static com.example.farmermate.CreateTable.Name1;

public class ToDoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Todo> ToDoList;
    private ListView lvProduct;

    public static  Todo get(int position) {
        return null;
    }

    public ToDoAdapter(Context mContext, List<Todo> ToDoList) {
        this.mContext = mContext;
        this.ToDoList = ToDoList;
    }

    public ToDoAdapter(CreateTable createTable) {
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

    public String getName1() {
        return Name1;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_todo, null);
        TextView tvName = (TextView)v.findViewById(R.id.todo);
        //TextView tvPrice = (TextView)v.findViewById(R.id.tv_product_price);
        ImageView imgV =(ImageView)v.findViewById(R.id.imgv);
        //TextView tvDescription = (TextView)v.findViewById(R.id.tv_product_description);
        tvName.setText(ToDoList.get(position).getName1());
        //tvPrice.setText(String.valueOf(mProductList.get(position).getPrice()) + " $");
        //tvDescription.setText(mProductList.get(position).getDescription());
        imgV.setImageResource(R.drawable.riceim1);
        return v;
    }

}

