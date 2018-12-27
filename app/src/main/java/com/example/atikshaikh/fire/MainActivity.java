package com.example.atikshaikh.fire;

 import android.content.Intent;
 import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

 import com.google.firebase.firestore.FirebaseFirestore;
 import com.google.firebase.firestore.FirebaseFirestoreSettings;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firestore.setFirestoreSettings(settings);

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, LogIn.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
