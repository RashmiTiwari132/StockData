package com.example.rashmit.stockdata;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by RashmiT on 7/12/2017.
 * This class sends asynchronous HTTPS request to https://www.alphavantage.co website.
 * After successful HTTPS connection JSON response is received.
 * Response is parsed inside parseJSONResponse method of this class.
 * stored in Response class's object*
 */

public class StockDataReceiver extends AsyncTask<String, Void, String> {

    MainActivity mainactivity;

    public StockDataReceiver(MainActivity mainactivity){
        this.mainactivity = mainactivity;
    }

    @Override
    protected String doInBackground(String... params){
        long val=0;

        try{
            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=7NSMNQJSQZN5COSN");
            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();

            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
                parseJSONResponse(stringBuilder);
                bufferedReader.close();
            }
            finally{
                urlConnection.disconnect();
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response){
        mainactivity.mRecyclerView.setAdapter(new StockDataAdapter(mainactivity, mainactivity.mResponse));
    }

    private void parseJSONResponse(StringBuilder JSONResponse){
        try{
            JSONObject reader = new JSONObject(JSONResponse.toString());
            JSONObject data = reader.getJSONObject("Time Series (1min)");
            JSONArray dataArray = (reader.getJSONObject("Time Series (1min)")).names();

            String dateTime;
            TimeSeries timeSeries;
            Response response = Response.getResponse();

            String open = new String("1. open");
            String high = new String("2. high");
            String low = new String("3. low");
            String close = new String("4. close");
            String volume = new String("5. volume");
            String openData = new String();
            String highData = new String();
            String lowData = new String();
            String closeData = new String();
            String volumeData = new String();

            for(int i=0; i<dataArray.length();i++){
                dateTime = (String)dataArray.get(i);
                JSONObject jsonObject = (data.getJSONObject(dateTime));
                openData = jsonObject.getString(open);
                highData = jsonObject.getString(high);
                lowData = jsonObject.getString(low);
                closeData = jsonObject.getString(close);
                volumeData = jsonObject.getString(volume);
                timeSeries = new TimeSeries(openData, highData, lowData, closeData, volumeData);

                response.timeSeries.put(dateTime, timeSeries);
            }
        }catch(JSONException je){
            Log.e("ERROR", je.getMessage(), je);
        }
        Log.d("StockDataReceiver",new String(JSONResponse));
    }
}
