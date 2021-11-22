package com.example.valutaconverter;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Queue;


public class FixerCurrency implements CurrencyDAO, RateCallable {
    private static final String TAG = "FixerCurrency";
    GetRatesCaller grc = new GetRatesCaller(this);
    ArrayList<Rateable> counterList = new ArrayList<>();
    ArrayList<Rate> listOfRates = new ArrayList<>();

    @Override
    public ArrayList<Rate> GetRates(String base) {

        return listOfRates;
    }

    void AddListener(Rateable rateable){
        counterList.add(rateable);
    }

    //Since we are listing threw out the life cycle of the application
    //There is no need to remove in this project
    void RemoveListner(Rateable rateable){
        counterList.remove(rateable);
    }

    public void CallRate(Context activity, String from){
     grc.GetData(activity,from);
    }

    @Override
    public void RateCallback(Rate rate) {
        for (int i = 0; i < counterList.size();i++){
            counterList.get(i).UpdateUI(rate);
        }
    }
}
