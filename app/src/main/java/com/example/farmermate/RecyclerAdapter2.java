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

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder2> {
    String data3[], data4[], data5[];
    int image[];
    Context context;
    public RecyclerAdapter2(Context ct, String s3[], String s4[], String s5[], int img[]){
        context = ct;
        data3 = s3;
        data4 = s4;
        data5 = s5;
        image = img;
    }
    
    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rice_recomlist2, parent,false);
        return new MyViewHolder2(view);
        
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, final int position) {
        holder.RName.setText(data3[position]);
        holder.RDesc.setText(data4[position]);
        //holder.RDescrip.setText(data3[position]);
        holder.Rimg.setImageResource(image[position]);

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, RiceDetail2.class);
                intent.putExtra("data3",data3[position]);
                // intent.putExtra("data2",data2[position]);
                intent.putExtra("data5",data5[position]);
                intent.putExtra("myImage",image[position]);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return image.length;
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView RName, RDesc,RDescrip;
        ImageView Rimg;
        ConstraintLayout mainlayout;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            RName = itemView.findViewById(R.id.ricename);
            RDesc = itemView.findViewById(R.id.ricedesc);
            //RDescrip = itemView.findViewById(R.id.detail);
            Rimg = itemView.findViewById(R.id.riceimg);
            mainlayout = itemView.findViewById(R.id.layout1);
        }
    }
}
