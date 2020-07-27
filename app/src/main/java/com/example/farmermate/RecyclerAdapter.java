package com.example.farmermate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    String data1[], data2[], data3[];
    int image[];
    Context context;
    public RecyclerAdapter(Context ct, String s1[], String s2[], String s3[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        image = img;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rice_recom_list, parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.RName.setText(data1[position]);
        holder.RDesc.setText(data2[position]);
        //holder.RDescrip.setText(data3[position]);
        holder.Rimg.setImageResource(image[position]);

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, RiceDetail.class);
                intent.putExtra("data1",data1[position]);
               // intent.putExtra("data2",data2[position]);
                intent.putExtra("data3",data3[position]);
                intent.putExtra("myImage",image[position]);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return image.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView RName, RDesc,RDescrip;
        ImageView Rimg;
        ConstraintLayout mainlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            RName = itemView.findViewById(R.id.ricename);
            RDesc = itemView.findViewById(R.id.ricedesc);
            //RDescrip = itemView.findViewById(R.id.detail);
            Rimg = itemView.findViewById(R.id.riceimg);
            mainlayout = itemView.findViewById(R.id.mainlayout);
        }
    }
}
