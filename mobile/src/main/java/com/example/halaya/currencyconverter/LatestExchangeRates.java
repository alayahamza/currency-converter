package com.example.halaya.currencyconverter;

import java.util.List;

/**
 * Created by halaya on 14/03/2017.
 */

public class LatestExchangeRates {
    String base;
    String date;
    Rates rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }
}
