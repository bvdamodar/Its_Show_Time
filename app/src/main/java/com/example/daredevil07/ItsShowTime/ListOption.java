package com.example.daredevil07.ItsShowTime;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListOption extends AppCompatActivity  {

    private ListView list;
    private String price;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_option);
        list = (ListView) findViewById(R.id.listview);
        Intent intent = getIntent();
        price = intent.getStringExtra("TotalPrice");
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.paymentOptions, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    Intent intent = new Intent(ListOption.this, PaymentOption.class);
                    intent.putExtra("TotalPrice",price);
                    startActivity(intent);
                }
                else if(position ==1 ){
                    Intent intent = new Intent(ListOption.this, CreditCard.class);

                    startActivity(intent);
                }
                else if (position == 2){
                    Intent intent = new Intent(ListOption.this, DebitCard.class);

                    startActivity(intent);
                }
            }
        });
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        view = inflater.inflate(R.layout.activity_first_fragment,container,false);

        ListView listview =view.findViewById(R.id.listview);
        listview.setOnItemClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
               android.R.layout.simple_list_item_1, paymentOptions);
        ListView lv = view.findViewById(R.id.listview);
        lv.setAdapter(adapter);
        return view;
    }
*/


    }
}