package com.example.halaya.currencyconverter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.halaya.currencyconverter.services.CurrencyService;
import com.example.halaya.currencyconverter.utils.Currencies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] currencyArray = getResources().getStringArray(R.array.currencies);
        String defualtCurrency = currencyArray[0];
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        fillCurrencyList(R.id.spinnerRight, currencyAdapter);
        fillCurrencyList(R.id.spinnerLeft, currencyAdapter);

        ListView exchangeRateComparedToDefaultCurrency=(ListView)findViewById(R.id.currentExchangeRates);




        ArrayList<String> exchangeRates = callCurrencyService();
        ArrayAdapter<String> exchangeRateArrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, exchangeRates);
        exchangeRateComparedToDefaultCurrency.setAdapter(exchangeRateArrayAdapter);
    }

    protected Spinner fillCurrencyList(int spinnerItemId, ArrayAdapter arrayAdapter) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(spinnerItemId);
        spinner.setAdapter(arrayAdapter);
        return spinner;
    }

    protected ArrayList<String> callCurrencyService(){
        ArrayList<String> exchangeRates = new ArrayList<String>();
        CurrencyService currencyService = new CurrencyService();

        try {
            JSONObject json = new JSONObject(currencyService.requestContent("http://api.fixer.io/latest"));
            JSONObject dataObject = json.getJSONObject("data");
            JSONArray items = dataObject.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject rateObject = items.getJSONObject(i);
                exchangeRates.add(rateObject.getString("rates"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return exchangeRates;
    }


}
