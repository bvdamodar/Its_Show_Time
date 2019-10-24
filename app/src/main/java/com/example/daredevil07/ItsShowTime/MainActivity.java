package com.example.daredevil07.ItsShowTime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FilmIDCallback, FilmAvailable {
    private static final String TAG = "MainActivity";
    private ArrayList<Integer> allID;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Film> films;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com.example.daredevil07.ItsShowTime.NowPlayingFilmID nowPlayingFilmID = new com.example.daredevil07.ItsShowTime.NowPlayingFilmID(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        films = new ArrayList<>();

        mAdapter = new FilmAdapter(films, this);
        mRecyclerView.setAdapter(mAdapter);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                        Intent a = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(a);
                    return true;}
                else if (item.getItemId() == R.id.profile) {
                    Intent a = new Intent(MainActivity.this,Profile_Activity.class);
                    startActivity(a);
                    return true;}

                else if (item.getItemId() == R.id.tickets) {
                    Intent a = new Intent(MainActivity.this,TicketActivity.class);
                    startActivity(a);
                    return true;}

                else if (item.getItemId() == R.id.contact) {
                    Intent a = new Intent(MainActivity.this,ContactActivity.class);
                    startActivity(a);
                    return true;}
                else if (item.getItemId() == R.id.signout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent a = new Intent(MainActivity.this,Cust_Sign_In.class);
                    startActivity(a);
                    Toast.makeText(getApplicationContext(),"Signed Out Successfully",Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

    }

    private void fetchFilm(String url) {

        FilmTask task = new FilmTask(this);
        String[] urls = new String[] {url};
        task.execute(urls);

    }

    @Override
    public void filmIDCallback(ArrayList<Integer> moviesID) {
        Log.d(TAG, "filmIDCallback: all movies ids" + moviesID);
        allID = moviesID;

        for(Integer id: allID){
            String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=9356cb1f42f053e63a72c6bf6ca12171&append_" +
                    "to_response=credits,images,keywords,lists,releases,reviews,videos";

            fetchFilm(url);
        }
    }

    @Override
    public void filmAvailable(Film film) {
        films.add(film);
        mAdapter.notifyDataSetChanged();
    }


    public void tickets(MenuItem item) {
        Intent p = new Intent(MainActivity.this,Profile_Activity.class);
        startActivity(p);

    }

    public void sign_out(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        Intent p = new Intent(MainActivity.this,Cust_Sign_In.class);
        startActivity(p);
    }


}

