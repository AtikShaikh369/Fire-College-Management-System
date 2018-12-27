package com.example.atikshaikh.fire;

import android.app.ProgressDialog;
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


public class signin extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signinBtn;
    private Button signupBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signinBtn = (Button) findViewById(R.id.btn_signin);
        password = (EditText)findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        signupBtn = (Button)findViewById(R.id.signup);

        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent intent = new Intent(signin.this,AdminTabs.class);
                    startActivity(intent);
                }
            }
        };

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(signin.this,AdminTabs.class);
                //startActivity(intent);

                startSignin();
            }
        });
     /* signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(signin.this, signup.class));
                Intent intent = new Intent(signin.this,signup.class);
                startActivity(intent);
            }
        });  */
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    private void startSignin() {
          //  pd.setMessage("Signing in");
         // pd.show();
        String em = email.getText().toString();
        String pass = password.getText().toString();
        if (TextUtils.isEmpty(em)||TextUtils.isEmpty(pass)) {
            Toast.makeText(signin.this, "Fields are Empty", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(em, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(signin.this, "SignIn Problem", Toast.LENGTH_LONG).show();
                    }
            //         else pd.dismiss();
                }
            });
        }
    }
}