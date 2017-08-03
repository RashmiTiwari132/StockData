package com.example.rashmit.stockdata;

/**
 * Created by RashmiT on 7/12/2017.
 * This class stores stock's information for each time series
 */
public class TimeSeries {
    String open;
    String high;
    String low;
    String close;
    String volume;

    public TimeSeries(){}

    public TimeSeries(String open, String high, String low, String close, String volume){
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }
}
