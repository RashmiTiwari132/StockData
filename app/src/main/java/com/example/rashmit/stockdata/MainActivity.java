package com.example.rashmit.stockdata;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    public RecyclerView mRecyclerView;
    public LinearLayoutManager mLinearLayoutManager;
    public Response mResponse;
    public StockDataReceiver mStockDataReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStockDataReceiver = new StockDataReceiver(this);
        mResponse = Response.getResponse();
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mLinearLayoutManager =  new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mStockDataReceiver.execute();

    }
}
