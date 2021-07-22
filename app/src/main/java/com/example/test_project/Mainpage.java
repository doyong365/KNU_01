package com.example.test_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class Mainpage extends AppCompatActivity {

    private DatabaseReference mDatabase;
    TextInputEditText TextInputEditText_pn, TextInputEditText_pw; //phonenum이랑 pw의 id를 받아올 변수
    RelativeLayout RelativeLayout_login; //login버튼 id를 받아올 변수
    TextView sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // 메인엑티비티가 사용할 레이아웃

//id 값 받아오기
        TextInputEditText_pn = findViewById(R.id.TextInputEditText_phonenumber1);
        TextInputEditText_pw = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login = findViewById(R.id.RelativeLayout_login);
        sign_in = findViewById(R.id.sign_in);

        RelativeLayout_login.setClickable(true);
        RelativeLayout_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String pn = TextInputEditText_pn.getText().toString(); // pn 받기
                String pw = TextInputEditText_pw.getText().toString(); // pw 받기
                Intent intent = new Intent(Mainpage.this,loading.class); // intent가 화면전환 느낌
                intent.putExtra("pn",pn); //"pn"이라는 키로 pn변수를 전달
                intent.putExtra("pw",pw); //"pw"이라는 키로 pw변수를 전달
                startActivity(intent); //intent 실행

            }
        });

        sign_in.setClickable(true);
        sign_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent sign = new Intent(Mainpage.this,signin.class); // intent가 화면전환 느낌
                startActivity(sign); //intent 실행
            }
        });
    }
}