package com.example.daredevil07.ItsShowTime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.io.Serializable;


public class FilmDetailActivity extends AppCompatActivity {
    private static final String TAG = "FilmDetailActivity";
    private Film filmIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "On create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        Log.d(TAG,"onCreate: Started.");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    Intent a = new Intent(FilmDetailActivity.this,MainActivity.class);
                    startActivity(a);
                    return true;}
                else if (item.getItemId() == R.id.profile) {
                    Intent a = new Intent(FilmDetailActivity.this,Profile_Activity.class);
                    startActivity(a);
                return true;}
                else if (item.getItemId() == R.id.tickets) {
                    Intent a = new Intent(FilmDetailActivity.this,TicketActivity.class);
                    startActivity(a);
                    return true;}

                else if (item.getItemId() == R.id.contact) {
                    Intent a = new Intent(FilmDetailActivity.this,ContactActivity.class);
                    startActivity(a);
                    return true;
                }
                else if (item.getItemId() == R.id.signout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent a = new Intent(FilmDetailActivity.this,Cust_Sign_In.class);
                    startActivity(a);
                    Toast.makeText(getApplicationContext(),"Signed Out Successfully",Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });



        getIntent().getSerializableExtra("MyClass");

        intentGetter();

        Button button = findViewById(R.id.seatselectionButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), com.example.daredevil07.ItsShowTime.ReservationActivity.class);

                itemDetailIntent.putExtra("MyFilmTitle", filmIntent);


//                itemDetailIntent.putExtra("imageURL", listItem.getImageURL());
//                itemDetailIntent.putExtra("cameraName", listItem.getCameraName());

                view.getContext().startActivity(itemDetailIntent);
            }
        });


    }



    private void intentGetter(){
        Log.d(TAG, "Get Intent");

        Film film = (Film) getIntent().getSerializableExtra("MyClass");
        setFilmInfo(film);
        filmIntent = film;
    }

    private void setFilmInfo(Film film){
        Log.d(TAG, "Set image url and camera name");

        ImageView image = findViewById(R.id.imgViewDetailActivity);
        Picasso.with(this).load(film.getPosterPath()).into(image);

        TextView overview = findViewById(R.id.textOverview);
        overview.setText(film.getOverview());

        TextView title = findViewById(R.id.titleDetailText);
        title.setText(film.getTitle());

        TextView genre = findViewById(R.id.genreDetailText);
        genre.setText(film.getGenres());

        TextView actors = findViewById(R.id.actorsDetailText);
        actors.setText(film.getActor());

        TextView director = findViewById(R.id.directorDetailText);
        director.setText(film.getDirector());

    }
}
