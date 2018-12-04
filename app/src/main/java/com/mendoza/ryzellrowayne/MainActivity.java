package com.mendoza.ryzellrowayne;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    EditText eFname, eAge, eGender;
    DatabaseReference points;
    ArrayList<String> keyList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        points = db.getReference("points");
        eFname = findViewById(R.id.etfname);
        eAge = findViewById(R.id.etage);
        eGender = findViewById(R.id.etgender);
        keyList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss : dataSnapshot.getChildren()){
                    keyList.add(ss.getKey());
                }
                Toast.makeText(MainActivity.this, keyList.get(0), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addRecord (View v){
        String fname = eFname.getText().toString().trim();
        String gender = eGender.getText().toString().trim();
        int age = Integer.parseInt(eAge.getText().toString().trim());
        String key = points.push().getKey();
        Person per = new Person(fname, gender, age);
        points.child(key).setValue(per);
        Toast.makeText(this, "Record Inserted...", Toast.LENGTH_LONG).show();

    }

    public void Search(View v){
        index = 0;
        points.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Person per = dataSnapshot.child(keyList.get(index)).getValue(Person.class);
                eFname.setText(per.getFname());
                eGender.setText(per.getGender());
                eAge.setText(per.getAge());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
