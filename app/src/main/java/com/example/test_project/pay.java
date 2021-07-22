package com.example.test_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class pay extends AppCompatActivity {
    FirebaseDatabase fDB = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    ImageView foodpic;
    TextView menuname, menucnt, menuprice;
    Integer price;
    String cnt, name;
    Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        foodpic = findViewById(R.id.imageView4);
        menuname = findViewById(R.id.textView7);
        menucnt = findViewById(R.id.textView9);
        menuprice = findViewById(R.id.textView11);
        pay = findViewById(R.id.button3);
        Intent intent = getIntent();
        cnt = intent.getExtras().getString("id");
        name = intent.getExtras().getString("name");
        DatabaseReference dbRf = fDB.getReference();
        DatabaseReference getImage;

        getImage = dbRf.child("res").child(cnt).child("resmenu").child("resmenu1").child("resmenu1pic");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(foodpic);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(pay.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(cnt).child("resmenu").child("resmenu1").child("resmenu1name");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                menuname.setText(link);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(pay.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(cnt).child("resmenu").child("resmenu1").child("resmenu1cnt");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                int link = dataSnapshot.getValue(Integer.class);
                menucnt.setText(Integer.toString(link));
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(pay.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(cnt).child("resmenu").child("resmenu1").child("resmenu1price");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                int link = dataSnapshot.getValue(Integer.class);
                menuprice.setText(Integer.toString(link));
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(pay.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });

        pay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent22 = new Intent(pay.this,Mypage.class); // intent가 화면전환 느낌
                intent22.putExtra("name",name);
                intent22.putExtra("code",cnt);
                Toast.makeText(getApplicationContext(), "결제가 완료되었습니다!", Toast.LENGTH_LONG).show();
                startActivity(intent22); //intent 실행
            }
        });
    }

}
