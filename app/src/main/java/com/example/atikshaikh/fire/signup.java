package com.example.atikshaikh.fire;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button signupBtn;
    private FirebaseAuth.AuthStateListener mAuthListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        password = (EditText)findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        signupBtn = (Button)findViewById(R.id.signup);

        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent intent = new Intent(signup.this,AdminTabs.class);
                    startActivity(intent);
                }
            }
        };

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //if(currentUser == null)

            //Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        //else
            //Toast.makeText(this, "not null", Toast.LENGTH_SHORT).show();
    }

    private void signup() {
        
        String e = email.getText().toString();
        String p = password.getText().toString();
        if (TextUtils.isEmpty(e)||TextUtils.isEmpty(p)) {
            Toast.makeText(signup.this, "Fields are Empty", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(signup.this, "Authentication Complete.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(signup.this, "Authentication failed.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
