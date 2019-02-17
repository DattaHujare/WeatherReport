package com.weather.metoffice.widget.paginate;

import android.support.v7.widget.RecyclerView;

import com.weather.metoffice.widget.paginate.recycler.RecyclerPaginate;


//import com.paginate.abslistview.AbsListViewPaginate;

public abstract class Paginate {


    public interface Callbacks {
        /**
         * Called when next page of data needs to be loaded.
         */
        void onLoadMore();


        boolean isLoading();


        boolean hasLoadedAllItems();
    }

    /**
     * Use this method to indicate that there is or isn't more data to load. If there isn't any more data to load
     * loading row, if used, won't be displayed as the last item of the list. Adding/removing loading row is done
     * automatically each time when underlying adapter data is changed. Use this method to explicitly add/remove
     * loading row.
     *
     * @param hasMoreDataToLoad true if there is more data to load, false otherwise.
     */
    abstract public void setHasMoreDataToLoad(boolean hasMoreDataToLoad);

    /**
     * Call unbind to detach list (RecyclerView or AbsListView) from Paginate when pagination functionality is no
     * longer needed on the list.
     * <p/>
     * Paginate is using scroll listeners and adapter data observers in order to perform required checks. It wraps
     * original (source) adapter with new adapter that provides loading row if loading row is used. When unbind is
     * called original adapter will be set on the list and scroll listeners and data observers will be detached.
     */
    abstract public void unbind();


    public static RecyclerPaginate.Builder with(RecyclerView recyclerView, Callbacks callback) {
        return new RecyclerPaginate.Builder(recyclerView, callback);
    }

//    public static AbsListViewPaginate.Builder with(AbsListView absListView, Callbacks callback) {
//        return new AbsListViewPaginate.Builder(absListView, callback);
//    }

}