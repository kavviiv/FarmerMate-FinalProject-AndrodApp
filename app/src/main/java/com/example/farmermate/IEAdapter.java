package com.example.farmermate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IEAdapter extends RecyclerView.Adapter<IEAdapter.ViewHolder> {
    private List<IncomeExpense> mIncomeExpense;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView amountTxtV;
        public TextView descriptionTxtV;
        public TextView dateTxtV;
        public ImageView imageView;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            amountTxtV = (TextView) v.findViewById(R.id.amount);
            descriptionTxtV = (TextView) v.findViewById(R.id.description);
            dateTxtV = (TextView) v.findViewById(R.id.dateadd);
            imageView = (ImageView) v.findViewById(R.id.imageView2);
        }
    }

    public void add(int position, IncomeExpense incomeExpense) {
        mIncomeExpense.add(position, incomeExpense);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mIncomeExpense.remove(position);
        notifyItemRemoved(position);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public IEAdapter(List<IncomeExpense> myDataset, Context context, RecyclerView recyclerView) {
        mIncomeExpense = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public IEAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.single_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        final IncomeExpense incomeExpense = mIncomeExpense.get(position);
        holder.amountTxtV.setText("จำนวนเงิน: " + incomeExpense.getAmount());
        holder.descriptionTxtV.setText(incomeExpense.getDescription());
        holder.dateTxtV.setText(incomeExpense.getDate());
        if (incomeExpense.getDescription().startsWith("รายรับ")) {
            holder.imageView.setImageResource(R.drawable.increased_revenue);
        } else {
            holder.imageView.setImageResource(R.drawable.decresed_revenue);
        }


        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("เลือกตัวเลือก");
                builder.setMessage("อัปเดตหรือลบ?");
                builder.setPositiveButton("อัปเดต", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        goToUpdateActivity(incomeExpense.getId());

                    }
                });
                builder.setNeutralButton("ลบ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IEDBHelper dbHelper = new IEDBHelper(mContext);
                        dbHelper.deleteIERecord(incomeExpense.getId(), mContext);

                        mIncomeExpense.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mIncomeExpense.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

    private void goToUpdateActivity(long personId) {
        Intent goToUpdate = new Intent(mContext, UpdateRecordActivity_ex.class);
        goToUpdate.putExtra("USER_ID", personId);
        mContext.startActivity(goToUpdate);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mIncomeExpense.size();
    }


}
