package com.example.atikshaikh.fire;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Admin_dbms_sy extends AppCompatActivity {
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
    FirebaseFirestore db;
    DocumentReference checkboxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbms_sy);
        db = FirebaseFirestore.getInstance();
        checkboxes  = db.collection("DBMS").document("Syllabus");

        c1 = findViewById(R.id.adbmscb1);
        c2 = findViewById(R.id.adbmscb2);
        c3 = findViewById(R.id.adbmscb3);
        c4 = findViewById(R.id.adbmscb4);
        c5 = findViewById(R.id.adbmscb5);
        c6 = findViewById(R.id.adbmscb6);
        c7 = findViewById(R.id.adbmscb7);
        c8 = findViewById(R.id.adbmscb8);
        c9 = findViewById(R.id.adbmscb9);
        c10 = findViewById(R.id.adbmscb10);
        c11 = findViewById(R.id.adbmscb11);
        c12 = findViewById(R.id.adbmscb12);

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

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.cn_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        Map<String,Boolean> newCheck = new HashMap<>();

        if (c1.isChecked()) newCheck.put("checkBox1",true);
        else newCheck.put("checkBox1",false);
        if (c2.isChecked()) newCheck.put("checkBox2",true);
        else newCheck.put("checkBox2",false);
        if (c3.isChecked()) newCheck.put("checkBox3",true);
        else newCheck.put("checkBox3",false);
        if (c4.isChecked()) newCheck.put("checkBox4",true);
        else newCheck.put("checkBox4",false);
        if (c5.isChecked()) newCheck.put("checkBox5",true);
        else newCheck.put("checkBox5",false);
        if (c6.isChecked()) newCheck.put("checkBox6",true);
        else newCheck.put("checkBox6",false);
        if (c7.isChecked()) newCheck.put("checkBox7",true);
        else newCheck.put("checkBox7",false);
        if (c8.isChecked()) newCheck.put("checkBox8",true);
        else newCheck.put("checkBox8",false);
        if (c9.isChecked()) newCheck.put("checkBox9",true);
        else newCheck.put("checkBox9",false);
        if (c10.isChecked()) newCheck.put("checkBox10",true);
        else newCheck.put("checkBox10",false);
        if (c11.isChecked()) newCheck.put("checkBox11",true);
        else newCheck.put("checkBox11",false);
        if (c12.isChecked()) newCheck.put("checkBox12",true);
        else newCheck.put("checkBox12",false);

        checkboxes.set(newCheck)
                .addOnSuccessListener(new OnSuccessListener< Void >() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Saved Successfully !!",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        return super.onOptionsItemSelected(item);
    }
}
