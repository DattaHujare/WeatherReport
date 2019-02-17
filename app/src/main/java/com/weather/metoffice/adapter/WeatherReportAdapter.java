package com.weather.metoffice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weather.metoffice.R;
import com.weather.metoffice.dbmodel.WeatherData;
import com.weather.metoffice.widget.CustomTextViewMedium;
import com.weather.metoffice.widget.CustomTextViewRegular;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherReportAdapter extends RecyclerView.Adapter<WeatherReportAdapter.MyViewHolder> {

    private static final String TAG = "WeatherReportAdapter";
    private ArrayList<WeatherData> dataSet;
    private Context mContext;
    private String monthArray[];

    public WeatherReportAdapter(ArrayList<WeatherData> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
        monthArray = mContext.getResources().getStringArray(R.array.month_array);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_year_report, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: " + dataSet);
        holder.tvLoactionName.setText(dataSet.get(holder.getAdapterPosition()).getLocation());
        holder.tvMonthYear.setText(monthArray[dataSet.get(holder.getAdapterPosition()).getMonth() - 1] + " " + dataSet.get(holder.getAdapterPosition()).getYear());
        holder.tvRainFall.setText(dataSet.get(holder.getAdapterPosition()).getRainFall() + " mm");
        holder.tvMaxTemperature.setText(dataSet.get(holder.getAdapterPosition()).gettMax() + "\u00B0");
        holder.tvMinTemperature.setText(dataSet.get(holder.getAdapterPosition()).gettMin() + "\u00B0");
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvLoactionName)
        CustomTextViewMedium tvLoactionName;
        @BindView(R.id.tvMonthYear)
        CustomTextViewMedium tvMonthYear;
        @BindView(R.id.tvRainFall)
        CustomTextViewRegular tvRainFall;
        @BindView(R.id.tvMaxTemperature)
        CustomTextViewRegular tvMaxTemperature;
        @BindView(R.id.tvMinTemperature)
        CustomTextViewRegular tvMinTemperature;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
