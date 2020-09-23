package com.example.farmermate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.farmermate.RecomPage.Advantage;
import static com.example.farmermate.RecomPage.Description;
import static com.example.farmermate.RecomPage.Name;
import static com.example.farmermate.RecomPage.Products;

public class RiceAdapter extends BaseAdapter  {
    private Context mContext;
    private List<Rice> mRiceList;

    public RiceAdapter(Context mContext, List<Rice> mRiceList) {
        this.mContext = mContext;
        this.mRiceList = mRiceList;
    }

    public RiceAdapter(RecomDetail recomDetail) {
    }

    public static  Rice get(int position) {
        return null;
    }

    public   String getName() {
        return Name;
    }

    public  String getDescription() {
        return Description;
    }

    public String getAdvantage(){
        return Advantage;
    }
    public String getProducts(){
        return Products;
    }

    @Override
    public int getCount() {
        return mRiceList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRiceList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_product_name);
        //TextView tvPrice = (TextView)v.findViewById(R.id.tv_product_price);
        ImageView imgV =(ImageView)v.findViewById(R.id.imgv);
        //TextView tvDescription = (TextView)v.findViewById(R.id.tv_product_description);
        tvName.setText(mRiceList.get(position).getName());
        Name = (mRiceList.get(position).getName());
        Description = (mRiceList.get(position).getDescription());

        //tvPrice.setText(String.valueOf(mProductList.get(position).getPrice()) + " $");
        //tvDescription.setText(mProductList.get(position).getDescription());
        imgV.setImageResource(R.drawable.riceim2);
        return v;
    }
}
