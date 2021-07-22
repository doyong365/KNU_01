package com.example.test_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Mypage extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase fDB = FirebaseDatabase.getInstance();
    DatabaseReference getImage;
    DatabaseReference dbRf = fDB.getReference();
    TextView showname;
    String name,code;
    String name1,price;
    RelativeLayout bs;
    Button payy;
    LinearLayout menu1;
    ImageView foodpic;
    TextView menuname, menucnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        code = intent.getExtras().getString("code");
        bs = findViewById(R.id.button_backspace);
        payy = findViewById(R.id.button_payy);
        showname = findViewById(R.id.textView2);
        showname.setText(name);
        bs.setClickable(true);
        bs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        payy.setClickable(true);

        payy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay = new Intent(Mypage.this,barcode.class);
                startActivity(pay);
            }
        });

        if(code.equals("0")){
        }
        else{
            activation(code);
        }
    }

    public void activation(String code){

        menu1 = findViewById(R.id.test_menu1);
        foodpic = findViewById(R.id.foodpic1);
        menuname = findViewById(R.id.resname1111);
        menucnt = findViewById(R.id.count);
        menu1.setVisibility(View.VISIBLE);


        getImage = dbRf.child("res").child(code).child("resmenu").child("resmenu1").child("resmenu1pic");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(foodpic);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Mypage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(code).child("resmenu").child("resmenu1").child("resmenu1name");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                menucnt.setText(link);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Mypage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(code).child("resmenu").child("resmenu1").child("resmenu1cnt");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                int link = dataSnapshot.getValue(Integer.class);
                menucnt.append(" - ");
                menucnt.append(Integer.toString(link));
                menucnt.append("/");
                menucnt.append(Integer.toString(link));
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Mypage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(code).child("resname");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                menuname.setText(link);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Mypage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });



    }
}