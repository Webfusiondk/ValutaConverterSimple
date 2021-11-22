package com.example.valutaconverter;

public class Rate {

    private String from;
    private String to;
    private double spotRate;

    // Getter
    public String getTo() {
        return to;
    }

    // Setter
    public void setTo(String newTo) {
        this.to = newTo;
    }

    // Getter
    public String getFrom() {
        return from;
    }

    // Setter
    public void setFrom(String newFrom) {
        this.from = newFrom;
    }

    // Getter
    public double getRate() {
        return spotRate;
    }

    // Setter
    public void setRate(double newRate) {
        this.spotRate = newRate;
    }

    public Rate(String from,String to, double rate){
        this.from = from;
        this.to = to;
        this.spotRate = rate;
    }

    public Rate(String from, double rate){
        this.from = from;
        this.spotRate = rate;
    }
}
