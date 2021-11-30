package com.example.valutaconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Rateable {
    private static final String TAG = "MyActivity";
    FixerCurrency fc = new FixerCurrency();
    CurrencyCalculator cc = new CurrencyCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fc.AddListener(this);
        LoadSpinner();
        LoadButton();
    }

    //Load spinner
    void LoadSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.currencySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency, android.R.layout.simple_spinner_item);
        //Set the layout for the adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Set the ArrayAdapter to the drop down
        spinner.setAdapter(adapter);
    }

    //Initialize onclick
    void LoadButton(){
        Button button = (Button) findViewById(R.id.Calculate);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG,"Clicked");
                fc.CallRate(MainActivity.this,GetUserInput().getFrom());
            }
        });
    }

    //get userinput from ui
    public Rate GetUserInput(){
        Spinner spinner = (Spinner) findViewById(R.id.currencySpinner);
        String currency = spinner.getSelectedItem().toString();
        EditText inputText = (EditText) findViewById(R.id.inputRate);
        Double ammount = Double.valueOf(inputText.getText().toString());
        Rate rate = new Rate(currency,ammount);
        return rate;
    }

    //Update ui with data from api
    @Override
    public void UpdateUI(Rate rate) {
        TextView currency,rateView;
        currency = (TextView) findViewById(R.id.CurrencyRegion1);
        rateView = (TextView) findViewById(R.id.rateRegion1);
        Rate calulatedRate = cc.CalculateAmmount(GetUserInput(),rate);
        currency.setText(calulatedRate.getTo());
        rateView.setText(String.valueOf(calulatedRate.getRate()));
    }
}