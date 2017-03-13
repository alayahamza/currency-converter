package com.example.halaya.currencyconverter.utils;

/**
 * Created by halaya on 10/03/2017.
 */

public enum Currencies {


    EUR("EURO"),

    USD("US DOLLAR"),

    YEN("Japanees YEN"),

    DT("DINAR, TUNISIEN");

    private String currencyName;

    public String getCurrencyName() {
        return currencyName;
    }

    private Currencies(final String currencyName) {
        this.currencyName = currencyName;
    }

    public static Currencies fromCurrencyLabel(final String currencyLabel) {
        Currencies curre = null;
        for (final Currencies currency : values()) {
            if (currencyLabel.equals(currencyLabel.toUpperCase())) {
                curre = currency;
            }
        }
        return curre;
    }

    public static String[] getAllCurrencies() {
        String[] currencies = new String[]{EUR.getCurrencyName(), USD.getCurrencyName(), YEN.getCurrencyName(), DT.getCurrencyName()};
        return currencies;
    }
}
