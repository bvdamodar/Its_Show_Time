package com.example.daredevil07.ItsShowTime;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class TicketDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    Intent a = new Intent(TicketDetailActivity.this,MainActivity.class);
                    startActivity(a);
                    return true;}
                else if (item.getItemId() == R.id.profile) {
                    Intent a = new Intent(TicketDetailActivity.this,Profile_Activity.class);
                    startActivity(a);
                    return true;}

                else if (item.getItemId() == R.id.tickets) {
                    Intent a = new Intent(TicketDetailActivity.this,TicketActivity.class);
                    startActivity(a);
                    return true;}

                else if (item.getItemId() == R.id.contact) {
                    Intent a = new Intent(TicketDetailActivity.this,ContactActivity.class);
                    startActivity(a);
                    return true;}
                else if (item.getItemId() == R.id.signout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent a = new Intent(TicketDetailActivity.this,Cust_Sign_In.class);
                    startActivity(a);
                    Toast.makeText(getApplicationContext(),"Signed Out Successfully",Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

        Ticket ticket = (Ticket) getIntent().getSerializableExtra("MyFilmAdapter");


        String text= "row 1, num 3"; // Whatever you need to encode in the QR code

        ImageView imageView = findViewById(R.id.code);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        TextView titleTicket = findViewById(R.id.titleFilmDetailed);
        titleTicket.setText(ticket.getFilmTitle());

        TextView seatDetail = findViewById(R.id.giveSeatsDetailed);
        seatDetail.setText(ticket.getSeatInfo());

        TextView dateTime = findViewById(R.id.dateTicketDetail);
        dateTime.setText("Date: " + ticket.getDate() + " Time: " + ticket.getTime());

        TextView idNum = findViewById(R.id.idNum);
        idNum.setText(String.valueOf(ticket.getTicketID()));

    }


}
