package com.example.daredevil07.ItsShowTime;


import android.app.Activity;
//AIzaSyBDqY7RVBWt6He1ZSejiyTMMhhXMadjXes
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

import android.os.AsyncTask;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentOption extends AppCompatActivity {

    String TAG="Document";
    private TextView text;
    String account;
    private Activity activity;
    TextView  amountLeft;
    FirebaseFirestore  db = FirebaseFirestore.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    String Balance="1234";
    private TextView finalResult;

    CollectionReference users = db.collection("users");
    Button pay,interact;
    int time = 5;
    private String price;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_option);
        //  myRef.setValue("Hello, World!");
        pay = findViewById(R.id.sb);
        amountLeft = findViewById(R.id.amountLeft);
        finalResult = findViewById(R.id.displayMobile);


       // final com.example.daredevil07.ItsShowTime.Film film = (com.example.daredevil07.ItsShowTime.Film) getIntent().getSerializableExtra("MyFilmNameConfirmation");
        Intent intent = getIntent();
        price = intent.getStringExtra("TotalPrice");
        Toast.makeText(getApplicationContext(),price,Toast.LENGTH_SHORT).show();


        //interact = findViewById(R.id.interact);
        SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
        String p=sf.getString("mobile","NA");
        Log.d("sp",p);
        finalResult.setText(p);
        account = p;


        DocumentReference docRef = db.collection("users").document(account);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Balance =  document.getData().toString();
                        Balance = Balance.replaceAll("[^a-zA-Z0-9]|[:]", "");
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        amountLeft.setText("Balance: " + Balance);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentOption.AsyncTaskRunner runner = new PaymentOption.AsyncTaskRunner();

                String sleepTime =  Integer.toString(time);

                runner.execute(sleepTime);
                int bal = Integer.parseInt(Balance);
                Double d = Double.parseDouble(price);
                int c=  (int) Math.round(d);
                bal = bal -c;
                Map<String, Object> data1 = new HashMap<>();
                data1.put(" ", bal);
                users.document(account).set(data1);
                Balance = Integer.toString(bal);
                amountLeft.setText("Balance: " + Balance);



            }
        });

     /*   docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            }
        }).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Toast.makeText(getApplicationContext(),documentSnapshot.get("mobile").toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

     /*
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                amountLeft.setText(document.getData().toString());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
*/
        // interactAction();

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

            Toast.makeText(PaymentOption.this, "Payment Completed", Toast.LENGTH_SHORT).show();


            startActivity(new Intent(PaymentOption.this,ThankYouActivity.class));
            Log.d("AsyncTask", "onPostExecute called");

            // execution of result of Long time consuming operation

            progressDialog.dismiss();

            finalResult.setText(result);

        }


        @Override

        protected void onPreExecute() {

            Toast.makeText(PaymentOption.this, "Payment in Process", Toast.LENGTH_SHORT).show();

            Log.d("AsyncTask", "onPreExecute called");

            progressDialog = ProgressDialog.show(PaymentOption.this,

                    "Payment in Process",

                    "Don't close for " + Integer.toString(time) + " seconds");

        }


        @Override

        protected void onProgressUpdate(String... text) {

            Log.d("AsyncTask", "onProgressUpdate");

            Toast.makeText(PaymentOption.this, "Payment in Process", Toast.LENGTH_SHORT).show();

            finalResult.setText(text[0]);


        }

    }
}