package com.example.valutaconverter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class GetRatesCaller implements Response.Listener, Response.ErrorListener{

    RateCallable callable;
    String ApiKey = "cff48e869752f87a50ec";
    ArrayList<String> countries = new ArrayList<>();
    private static final String TAG = "GetRatesCaller";

    //Get data from api
    public void GetData(Context Activity, String from){
        RequestQueue queue = Volley.newRequestQueue(Activity);
        //Gets the conversion from 1 currency to a new one
            String url ="https://free.currconv.com/api/v7/convert?apiKey=" + ApiKey + "&q=" + from +"_"+ "USD" +"&compact=ultra";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
            queue.add(jsonObjectRequest);
    }

    //Convert the response data to new rate obj
    @Override
    public void onResponse(Object response) {
        String temp = response.toString();
        String[] values = temp.replace("{", "").replace("}", "").replace("\"", "").split(":");
        Rate rate = new Rate(values[0].split("_")[0],values[0].split("_")[1],Double.parseDouble(values[1]));
        callable.RateCallback(rate);
    }

    //Write the error in debug.
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(TAG, error.toString());
    }

    public GetRatesCaller(RateCallable callable){
        this.callable = callable;
    }
}
