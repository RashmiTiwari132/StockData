package com.example.rashmit.stockdata;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class displays information in grid view
 * Created by RashmiT on 7/12/2017.
 */
public class StockDataAdapter extends RecyclerView.Adapter<StockDataAdapter.ViewHolder>{
    private Context mContext;
    private Response mResponse;
    private List<String> timedate;
    private Random mRandom = new Random();

    public StockDataAdapter(Context context, Response response){
        mContext = context;
        mResponse = response;
        timedate = new ArrayList<String>(mResponse.timeSeries.keySet());
        Log.d("StockDataAdapter","Inside constructor");
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView openView;
        public TextView highView;
        public TextView lowView;
        public TextView closeView;
        public TextView volumeView;

        public ViewHolder(View v) {
            super(v);
            openView = (TextView) v.findViewById(R.id.openView);
            highView = (TextView) v.findViewById(R.id.highView);
            lowView = (TextView) v.findViewById(R.id.lowView);
            closeView = (TextView) v.findViewById(R.id.closeView);
            volumeView = (TextView) v.findViewById(R.id.volumeView);
        }
    }

    public StockDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.stockdatamanifest, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        TimeSeries timeSeries = mResponse.timeSeries.get(timedate.get(position));
        holder.openView.setText(timeSeries.open);
        holder.highView.setText(timeSeries.high);
        holder.lowView.setText(timeSeries.low);
        holder.closeView.setText(timeSeries.close);
        holder.volumeView.setText(timeSeries.volume);

        Log.d("StockDataAdapter", "Inside onBindViewHolder");
    }

    public int getItemCount(){
        return mResponse.timeSeries.size();
    }
}
