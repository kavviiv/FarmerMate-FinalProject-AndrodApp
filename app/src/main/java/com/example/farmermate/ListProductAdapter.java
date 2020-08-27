package com.example.farmermate;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NgocTri on 11/7/2015.
 */
public class ListProductAdapter extends BaseAdapter {
    private Context mContext;
    private List<Product> mProductList;
    private ListView lvProduct;


    public ListProductAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getId();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_product_name);
        //TextView tvPrice = (TextView)v.findViewById(R.id.tv_product_price);
        ImageView imgV =(ImageView)v.findViewById(R.id.imgv);
        //TextView tvDescription = (TextView)v.findViewById(R.id.tv_product_description);
        tvName.setText(mProductList.get(position).getName());
        //tvPrice.setText(String.valueOf(mProductList.get(position).getPrice()) + " $");
        //tvDescription.setText(mProductList.get(position).getDescription());
        imgV.setImageResource(R.drawable.riceim1);
        return v;
    }

}
