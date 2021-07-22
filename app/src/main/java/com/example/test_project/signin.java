package com.example.test_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signin extends AppCompatActivity {

    private DatabaseReference mDatabase;
    TextInputEditText name, id, pw22;
    Button confirm, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        name = findViewById(R.id.TextInputEditText_name);
        id = findViewById(R.id.TextInputEditText_phonenumber);
        pw22 = findViewById(R.id.textInputEditText_password);

        confirm = findViewById(R.id.button4);
        cancel = findViewById(R.id.button5);

        confirm.setClickable(true);
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String pn = id.getText().toString(); // pn 받기
                String pw = pw22.getText().toString(); // pw 받기
                String nm = name.getText().toString();

                adduser(nm, pw, pn);
                finish();
                Toast.makeText(getApplicationContext(), "회원가입 완료!", Toast.LENGTH_LONG).show();
            }
        });

        cancel.setClickable(true);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void adduser(String name, String passwd, String phone){
        User user = new User(name, passwd, phone);
        mDatabase.child("user").child(phone).setValue(user);
    }
}