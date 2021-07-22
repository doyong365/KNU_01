package com.example.test_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class loading extends AppCompatActivity {

    String pho,pas,name;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent intent = getIntent();
        pho = intent.getExtras().getString("pn");
        pas = intent.getExtras().getString("pw");

        mDatabase.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            int flag = 0;

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue(User.class) != null) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        User user = ds.getValue(User.class);
                        if (user.passWd.equals(pas) && user.phonenum.equals(pho)) {
                            name = user.userName;
                            Log.d("loading", "checking" + name);
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        finish();
                        Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 일치하지 않습니다!!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Log.d("loading", "sending" + name);
                        Intent intent = new Intent(loading.this,Homepage.class); // intent가 화면전환 느낌
                        intent.putExtra("name",name); //"pn"이라는 키로 pn변수를 전달
                        startActivity(intent); //intent 실행
                        Toast.makeText(getApplicationContext(), "로그인 완료!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}