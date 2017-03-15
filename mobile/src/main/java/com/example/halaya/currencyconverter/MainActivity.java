package com.example.halaya.currencyconverter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> exchangeRateArrayAdapter;
    String defaultCurrency;
    EditText valueToConvert;
    Response<LatestExchangeRates> LatestRatesResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView exchangeRateComparedToDefaultCurrency = (ListView) findViewById(R.id.currentExchangeRates);
        String[] currencyArray = getResources().getStringArray(R.array.currencies);
        defaultCurrency = currencyArray[0];
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        fillCurrencyList(R.id.spinnerLeft, currencyAdapter);
        exchangeRateArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        exchangeRateComparedToDefaultCurrency.setAdapter(exchangeRateArrayAdapter);
        final Spinner currencySpinner = (Spinner) findViewById(R.id.spinnerLeft);
        valueToConvert = (EditText) findViewById(R.id.valueToConvert);
        valueToConvert.setText("1");
        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                defaultCurrency = currencySpinner.getSelectedItem().toString();
                getRetrofitObject();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        valueToConvert.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                updateDisplay(LatestRatesResponse);
            }
        });
    }

    void getRetrofitObject() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);

        Call<LatestExchangeRates> call = service.getLatestExchangeRates(defaultCurrency);

        call.enqueue(new Callback<LatestExchangeRates>() {
            @Override
            public void onResponse(Response<LatestExchangeRates> response, Retrofit retrofit) {
                LatestRatesResponse = response;
                try {
                    updateDisplay(LatestRatesResponse);
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }

    protected Spinner fillCurrencyList(int spinnerItemId, ArrayAdapter arrayAdapter) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(spinnerItemId);
        spinner.setAdapter(arrayAdapter);
        return spinner;
    }

    protected void updateDisplay(Response<LatestExchangeRates> response){
        String stringValueToConvert =  valueToConvert.getText().toString();
        if(stringValueToConvert.isEmpty()) {
            stringValueToConvert = "1";
            valueToConvert.setText("1");
        BigDecimal decimalValueToConvert = new BigDecimal(stringValueToConvert);

        List<String> rates = new ArrayList<>();

        if (response.body().getRates().getEUR() != null)
            rates.add("EUR : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getEUR())));
        if (response.body().getRates().getAUD() != null)
            rates.add("AUD : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getAUD())));
        if (response.body().getRates().getBGN() != null)
            rates.add("BGN : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getBGN())));
        if (response.body().getRates().getBRL() != null)
            rates.add("BRL : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getBRL())));
        if (response.body().getRates().getCAD() != null)
            rates.add("CAD : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getCAD())));
        if (response.body().getRates().getCHF() != null)
            rates.add("CHF : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getCHF())));
        if (response.body().getRates().getCNY() != null)
            rates.add("CNY : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getCNY())));
        if (response.body().getRates().getCZK() != null)
            rates.add("CZK : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getCZK())));
        if (response.body().getRates().getDKK() != null)
            rates.add("DKK : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getDKK())));
        if (response.body().getRates().getHRK() != null)
            rates.add("HRK : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getHRK())));
        if (response.body().getRates().getGBP() != null)
            rates.add("GBP : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getGBP())));
        if (response.body().getRates().getMXN() != null)
            rates.add("MXN : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getMXN())));
        if (response.body().getRates().getHKD() != null)
            rates.add("HKD : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getHKD())));
        if (response.body().getRates().getHUF() != null)
            rates.add("HUF : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getHUF())));
        if (response.body().getRates().getIDR() != null)
            rates.add("IDR : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getIDR())));
        if (response.body().getRates().getILS() != null)
            rates.add("ILS : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getILS())));
        if (response.body().getRates().getINR() != null)
            rates.add("INR : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getINR())));
        if (response.body().getRates().getPHP() != null)
            rates.add("PHP : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getPHP())));
        if (response.body().getRates().getJPY() != null)
            rates.add("JPY : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getJPY())));
        if (response.body().getRates().getMYR() != null)
            rates.add("MYR : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getMYR())));
        if (response.body().getRates().getKRW() != null)
            rates.add("KRW : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getKRW())));
        if (response.body().getRates().getNOK() != null)
            rates.add("NOK : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getNOK())));
        if (response.body().getRates().getNZD() != null)
            rates.add("NZD : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getNZD())));
        if (response.body().getRates().getPLN() != null)
            rates.add("PLN : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getPLN())));
        if (response.body().getRates().getRON() != null)
            rates.add("RON : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getRON())));
        if (response.body().getRates().getSEK() != null)
            rates.add("SEK : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getSEK())));
        if (response.body().getRates().getSGD() != null)
            rates.add("SGD : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getSGD())));
        if (response.body().getRates().getUSD() != null)
            rates.add("USD : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getUSD())));
        if (response.body().getRates().getZAR() != null)
            rates.add("ZAR : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getZAR())));
        if (response.body().getRates().getRUB() != null)
            rates.add("RUB : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getRUB())));
        if (response.body().getRates().getTRY() != null)
            rates.add("TRY : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getTRY())));
        if (response.body().getRates().getTHB() != null)
            rates.add("THB : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getTHB())));
        if (response.body().getRates().getTRY() != null)
            rates.add("TRY : " + decimalValueToConvert.multiply(new BigDecimal(response.body().getRates().getTRY())));
        exchangeRateArrayAdapter.clear();
        exchangeRateArrayAdapter.addAll(rates);
        exchangeRateArrayAdapter.notifyDataSetChanged();
    }

}
