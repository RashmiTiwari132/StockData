package com.example.rashmit.stockdata;

import java.util.HashMap;

/**
 * Created by RashmiT on 7/12/2017.
 * This class stores stock data for each company.
 * Each HashMap uses time and date combination as key.
 * Each HashMap stores TimeSeries class's object as value.
 */

public class Response {
    //MetaData metaData;
    private static Response mResponse=null;

    public static Response getResponse(){
        if(mResponse==null){
            mResponse = new Response();
        }
        return mResponse;
    }

    HashMap<String, TimeSeries> timeSeries = new HashMap<String, TimeSeries>();
}
