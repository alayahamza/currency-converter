package com.example.halaya.currencyconverter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        final Spinner currencySpinner = (Spinner)findViewById(R.id.spinnerLeft);
        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                defaultCurrency =  currencySpinner.getSelectedItem().toString();
                getRetrofitObject();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    void getRetrofitObject() {
        ArrayList<String> rates = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);

        Call<LatestExchangeRates> call = service.getLatestExchangeRates(defaultCurrency);

        call.enqueue(new Callback<LatestExchangeRates>() {
            @Override
            public void onResponse(Response<LatestExchangeRates> response, Retrofit retrofit) {

                try {
                    List<String> rates = new ArrayList<>();

                    rates.add("EUR : " + response.body().getRates().getEUR());
                    rates.add("AUD : " + response.body().getRates().getAUD());
                    rates.add("BGN : " + response.body().getRates().getBGN());
                    rates.add("BRL : " + response.body().getRates().getBRL());
                    rates.add("CAD : " + response.body().getRates().getCAD());
                    rates.add("CHF : " + response.body().getRates().getCHF());
                    rates.add("CNY : " + response.body().getRates().getCNY());
                    rates.add("CZK : " + response.body().getRates().getCZK());
                    rates.add("DKK : " + response.body().getRates().getDKK());
                    rates.add("HRK : " + response.body().getRates().getHRK());
                    rates.add("GBP : " + response.body().getRates().getGBP());
                    rates.add("MXN : " + response.body().getRates().getMXN());
                    rates.add("HKD : " + response.body().getRates().getHKD());
                    rates.add("HUF : " + response.body().getRates().getHUF());
                    rates.add("IDR : " + response.body().getRates().getIDR());
                    rates.add("ILS : " + response.body().getRates().getILS());
                    rates.add("INR : " + response.body().getRates().getINR());
                    rates.add("PHP : " + response.body().getRates().getPHP());
                    rates.add("JPY : " + response.body().getRates().getJPY());
                    rates.add("MYR : " + response.body().getRates().getMYR());
                    rates.add("KRW : " + response.body().getRates().getKRW());
                    rates.add("NOK : " + response.body().getRates().getNOK());
                    rates.add("NZD : " + response.body().getRates().getNZD());
                    rates.add("PLN : " + response.body().getRates().getPLN());
                    rates.add("RON : " + response.body().getRates().getRON());
                    rates.add("SEK : " + response.body().getRates().getSEK());
                    rates.add("SGD : " + response.body().getRates().getSGD());
                    rates.add("USD : " + response.body().getRates().getUSD());
                    rates.add("ZAR : " + response.body().getRates().getZAR());
                    rates.add("RUB : " + response.body().getRates().getRUB());
                    rates.add("TRL : " + response.body().getRates().getTRY());
                    rates.add("THB : " + response.body().getRates().getTHB());
                    rates.add("TRY : " + response.body().getRates().getTRY());
                    rates.remove(defaultCurrency+" : " + response.body().getRates().getClass().getMethod("get"+defaultCurrency).invoke(response.body().getRates()));
                    exchangeRateArrayAdapter.clear();
                    exchangeRateArrayAdapter.addAll(rates);
                    exchangeRateArrayAdapter.notifyDataSetChanged();
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

}
