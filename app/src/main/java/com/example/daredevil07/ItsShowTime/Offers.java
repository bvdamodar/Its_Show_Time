package com.example.daredevil07.ItsShowTime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Offers extends AppCompatActivity {


    private ListView lv ;
    private EditText editText ;
    private ArrayAdapter<String> adapter ;
    private String offers [] = {"Flat Rs. 75 Off on Movie Tickets (New Users)",
            "RuPay Credit Card Offer: Buy 1 Get 2 Free using Paytm",
            "Buy 1 & Get 1 Free on Total Dhamaal Movie Tickets (SBI and Axis Bank)",
            "Luka Chuppi Movie Offer: Buy Movie Voucher worth Rs. 199 and get Rs. 99 Off",
            "Badla Movie Offer: Upto Rs. 125 Cashback on Payments with Amazon Pay for All Users",
            "RuPay Credit Card Offer: Buy 1 Get 1 Free on your Movie Tickets",
            "ICICI Bank Credit Card Offer: Flat Rs. 100 Off on Movie Tickets"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        lv = (ListView) findViewById(R.id. listView );
        editText = (EditText) findViewById(R.id. editText );
        adapter = new ArrayAdapter<String>( this ,R.layout.offers_list, R.id. offer_name , offers );
        lv .setAdapter( adapter );
        editText .addTextChangedListener( new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                adapter .getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                Toast. makeText (getApplicationContext(), "before text change" , Toast. LENGTH_LONG ).show();
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                Toast. makeText (getApplicationContext(), "after text change" , Toast. LENGTH_LONG ).show();
            }
        });
        lv .setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value= adapter .getItem(position);
                Toast. makeText (getApplicationContext(),value,Toast. LENGTH_SHORT ).show();
                editText .setText(value);
            }
        });
    }
}
