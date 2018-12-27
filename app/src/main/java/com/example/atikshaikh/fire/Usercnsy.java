package com.example.atikshaikh.fire;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Usercnsy extends AppCompatActivity {
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
    FirebaseFirestore db;
    DocumentReference checkboxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercnsy);

        db = FirebaseFirestore.getInstance();
        checkboxes  = db.collection("CN").document("Syllabus");

        c1 = findViewById(R.id.ucncb1);
        c2 = findViewById(R.id.ucncb2);
        c3 = findViewById(R.id.ucncb3);
        c4 = findViewById(R.id.ucncb4);
        c5 = findViewById(R.id.ucncb5);
        c6 = findViewById(R.id.ucncb6);
        c7 = findViewById(R.id.ucncb7);
        c8 = findViewById(R.id.ucncb8);
        c9 = findViewById(R.id.ucncb9);
        c10 = findViewById(R.id.ucncb10);
        c11 = findViewById(R.id.ucncb11);
        c12 = findViewById(R.id.ucncb12);

        checkboxes.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    Boolean status;
                    try {
                        status = (Boolean)doc.get("checkBox1");
                        if(status) c1.setChecked(true);
                        else c1.setChecked(false);
                        status = (Boolean)doc.get("checkBox2");
                        if(status) c2.setChecked(true);
                        else c2.setChecked(false);
                        status = (Boolean)doc.get("checkBox3");
                        if(status) c3.setChecked(true);
                        else c3.setChecked(false);
                        status = (Boolean)doc.get("checkBox4");
                        if(status) c4.setChecked(true);
                        else c4.setChecked(false);
                        status = (Boolean)doc.get("checkBox5");
                        if(status) c5.setChecked(true);
                        else c5.setChecked(false);
                        status = (Boolean)doc.get("checkBox6");
                        if(status) c6.setChecked(true);
                        else c6.setChecked(false);
                        status = (Boolean)doc.get("checkBox7");
                        if(status) c7.setChecked(true);
                        else c7.setChecked(false);
                        status = (Boolean)doc.get("checkBox8");
                        if(status) c8.setChecked(true);
                        else c8.setChecked(false);
                        status = (Boolean)doc.get("checkBox9");
                        if(status) c9.setChecked(true);
                        else c9.setChecked(false);
                        status = (Boolean)doc.get("checkBox10");
                        if(status) c10.setChecked(true);
                        else c10.setChecked(false);
                        status = (Boolean)doc.get("checkBox11");
                        if(status) c11.setChecked(true);
                        else c11.setChecked(false);
                        status = (Boolean)doc.get("checkBox12");
                        if(status) c12.setChecked(true);
                        else c12.setChecked(false);
                    }
                    catch (Exception e){

                    }
                }
            }

        });


    }
}
