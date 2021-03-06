package com.example.valutaconverter;

import android.content.Context;

import java.util.ArrayList;

public class CurrencyCalculator {

    //Calculate the base rate to the new rate
    Rate CalculateAmmount(Rate base, Rate rate){
        double calculatedRate;
        calculatedRate = base.getRate() * rate.getRate();
        return new Rate(base.getFrom(),rate.getTo(), calculatedRate);
    }
}
