package com.example.daredevil07.ItsShowTime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;



public class TicketActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Ticket> tickets;
    private FilmDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        db = new FilmDatabase(this);
        tickets = db.getAllTickets();

        mRecyclerView = findViewById(R.id.recycler_view_ticket);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new com.example.daredevil07.ItsShowTime.TicketAdapter(tickets, this);
        mRecyclerView.setAdapter(mAdapter);


    }


    public void contacts(View view) {
        Intent p = new Intent(TicketActivity.this,ContactActivity.class);
        startActivity(p);
    }
}
