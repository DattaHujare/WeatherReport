package com.weather.metoffice.widget.paginate;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weather.metoffice.R;
import com.weather.metoffice.widget.paginate.recycler.LoadingListItemCreator;


public class CustomLoadingListItemCreator implements LoadingListItemCreator {
    RecyclerView rvNotification;
    public CustomLoadingListItemCreator(RecyclerView rvNotification) {
        this.rvNotification=rvNotification;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_loading_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;

        if (rvNotification.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) vh.itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tvLoading;

        public VH(View itemView) {
            super(itemView);
//        tvLoading = (TextView) itemView.findViewById(R.id.tv_loading_text);
        }
    }
}
