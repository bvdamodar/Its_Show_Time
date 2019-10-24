package com.example.daredevil07.ItsShowTime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ContactActivity extends AppCompatActivity {

    private TextView contact;
    private TextView route;
    private TextView adres;
    private TextView tijden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    Intent a = new Intent(ContactActivity.this,MainActivity.class);
                    startActivity(a);
                    return true;}
                else if (item.getItemId() == R.id.profile) {
                    Intent a = new Intent(ContactActivity.this,Profile_Activity.class);
                    startActivity(a);
                    return true;}

                else if (item.getItemId() == R.id.tickets) {
                    Intent a = new Intent(ContactActivity.this,TicketActivity.class);
                    startActivity(a);
                    return true;}

                else if (item.getItemId() == R.id.contact) {
                    Intent a = new Intent(ContactActivity.this,ContactActivity.class);
                    startActivity(a);
                    return true;}
                else if (item.getItemId() == R.id.signout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent a = new Intent(ContactActivity.this,Cust_Sign_In.class);
                    startActivity(a);
                    Toast.makeText(getApplicationContext(),"Signed Out Successfully",Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

        Contact item = new Contact("Contact Us:\n" +
                "Name: Damodar \n" +
                "Mail: bvdamodar99@gmail.com\n", "Phone Number: +919885880998 \n" +
                "Name : Kannan\nMail : kannan_mani@gmail.com\nPhone Number : 8015150522"+
                "Name : Bheeshma\nMail : sahasvat@gmail.com\nPhone Number : 9087469332"+
                "\n" , "", "");

        contact = findViewById(R.id.ContactId);
        route = findViewById(R.id.RouteId);
        adres = findViewById(R.id.AdresId);
        tijden = findViewById(R.id.OpeningsId);

        contact.setText(item.getContact());
        route.setText(item.getRoute());
        adres.setText(item.getAdres());
        tijden.setText(item.getOpeningstijden());





    }
}
