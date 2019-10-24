package com.example.daredevil07.ItsShowTime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class PhoneVerification extends AppCompatActivity {

    private String price;
    private EditText editTextMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        editTextMobile = findViewById(R.id.editTextMobile);
        Intent i = getIntent();
        price = i.getStringExtra("TotalPrice");



        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }
                SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=sf.edit();
                edit.putString("mobile",editTextMobile.getText().toString().trim());
                edit.commit();

                Intent intent = new Intent(PhoneVerification.this, Verify.class);
                intent.putExtra("mobile", mobile);
                intent.putExtra("TotalPrice",price );
                startActivity(intent);
            }
        });
    }

}