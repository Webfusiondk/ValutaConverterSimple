package com.example.valutaconverter;

import android.content.Context;

import java.util.ArrayList;

public interface CurrencyDAO {
    public ArrayList<Rate> GetRates(String base);
}
