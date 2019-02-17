package com.weather.metoffice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weather.metoffice.R;
import com.weather.metoffice.WeatherReportActivity;
import com.weather.metoffice.widget.CustomTextViewRegular;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YearRecyclerAdapter extends RecyclerView.Adapter<YearRecyclerAdapter.MyViewHolder> {
    private static final String TAG = "YearRecyclerAdapter";
    private ArrayList<String> dataSet;
    private Context mContext;
    private String location;


    public YearRecyclerAdapter(ArrayList<String> dataSet, Context mContext, String location) {
        this.dataSet = dataSet;
        this.mContext = mContext;
        this.location = location;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_year, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.tvYear.setText(dataSet.get(holder.getAdapterPosition()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: location " + location + " year " + dataSet.get(holder.getAdapterPosition()));
                mContext.startActivity(new Intent(mContext, WeatherReportActivity.class)
                        .putExtra("location", location)
                        .putExtra("year", dataSet.get(holder.getAdapterPosition())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvYear)
        CustomTextViewRegular tvYear;
        @BindView(R.id.cvYear)
        CardView cvYear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
