package com.example.daredevil07.ItsShowTime;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DebitCard extends AppCompatActivity  {

    Button pay;

    int time = 5;

    private EditText number,cvv;

    private TextView finalResult;

    protected LocationManager locationManager;

    TextView txtLat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_debit_card);

        txtLat = (TextView) findViewById(R.id.textview1);

        finalResult = findViewById(R.id.Result);

        number = findViewById(R.id.number);

        pay = findViewById(R.id.pay);

        cvv=findViewById(R.id.cvv);


        pay.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String Number = number.getText().toString().trim();

                if(Number.isEmpty() || Number.length() < 12){

                    number.setError("Enter a valid credit card number");

                    number.requestFocus();

                    return;
                }

                String s = cvv.getText().toString().trim();

                if(s.isEmpty() || s.length() < 3){

                    cvv.setError("Enter a valid CVV number");

                    cvv.requestFocus();

                    return;
                }
                DebitCard.AsyncTaskRunner runner = new DebitCard.AsyncTaskRunner();

                String sleepTime =  Integer.toString(time);

                runner.execute(sleepTime);


            }
        });
    }





    private class AsyncTaskRunner extends AsyncTask<String, String, String> {


        private String resp;

        ProgressDialog progressDialog;


        @Override

        protected String doInBackground(String... params) {

            // NO UI related stuff here

            try {

                int timeinsec = Integer.parseInt(params[0]);
                int timeinms = timeinsec * 1000;


                while (timeinsec > 0) {

                    publishProgress("Payment in Progress for " + timeinsec + "  seconds"); // Calls onProgressUpdate()

                    Thread.sleep(1000);

                    timeinsec--;


                }

                resp = "Payment  Completed";

            } catch (InterruptedException e) {

                e.printStackTrace();

                resp = e.getMessage();

            } catch (Exception e) {

                e.printStackTrace();

                resp = e.getMessage();

            }

            return resp;

        }


        @Override

        protected void onPostExecute(String result) {

            Toast.makeText(DebitCard.this, "Payment Completed", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(DebitCard.this,ThankYouActivity.class));
            Log.d("AsyncTask", "onPostExecute called");

            // execution of result of Long time consuming operation

            progressDialog.dismiss();

            finalResult.setText(result);

        }


        @Override

        protected void onPreExecute() {

            Toast.makeText(DebitCard.this, "Payment in Process", Toast.LENGTH_SHORT).show();

            Log.d("AsyncTask", "onPreExecute called");

            progressDialog = ProgressDialog.show(DebitCard.this,

                    "Payment in Process",

                    "Don't close for " + Integer.toString(time) + " seconds");

        }


        @Override

        protected void onProgressUpdate(String... text) {

            Log.d("AsyncTask", "onProgressUpdate");

            Toast.makeText(DebitCard.this, "Payment in Process", Toast.LENGTH_SHORT).show();

            finalResult.setText(text[0]);


        }

    }
}
