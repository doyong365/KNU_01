package com.example.test_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

public class Homepage extends AppCompatActivity {
    ImageView res1icon, res2icon, res3icon;
    Button mp;
    RelativeLayout rest_list1, rest_list2, rest_list3;
    RatingBar rb1, rb2, rb3;
    String pho,pas, name;
    DatabaseReference mDatabase  = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase fDB = FirebaseDatabase.getInstance();
    User user;
    TextView resname1, resname2, resname3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        Log.d("Homepage", "it is the beginning" + name);
        res1icon = findViewById(R.id.resicon1);
        res2icon = findViewById(R.id.resicon2);
        res3icon = findViewById(R.id.resicon3);
        rb1 = findViewById(R.id.ratingBar1);
        rb2 = findViewById(R.id.ratingBar2);
        rb3 = findViewById(R.id.ratingBar3);
        DatabaseReference dbRf = fDB.getReference();
        DatabaseReference getImage = dbRf.child("res").child("1").child("resicon");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(res1icon);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child("2").child("resicon");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(res2icon);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child("3").child("resicon");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(res3icon);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child("1").child("resrate");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                float link = dataSnapshot.getValue(Float.class);
                rb1.setRating(link);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child("2").child("resrate");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                float link = dataSnapshot.getValue(Float.class);
                rb2.setRating(link);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child("3").child("resrate");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                float link = dataSnapshot.getValue(Float.class);
                rb3.setRating(link);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(Homepage.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });

        mp = findViewById(R.id.button_mp);
        rest_list1 = findViewById(R.id.restaurant_list1);
        rest_list2 = findViewById(R.id.restaurant_list2);
        rest_list3 = findViewById(R.id.restaurant_list3);
        resname1 = findViewById(R.id.resname1);     resname1.setText("분식집");
        resname2 = findViewById(R.id.resname2);     resname2.setText("카페");
        resname3 = findViewById(R.id.resname3);     resname3.setText("한식당");
        Log.d("Homepage", "do we know name?" + name);
        mp.setClickable(true);
        mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Homepage", name);
                Intent i = new Intent(Homepage.this,Mypage.class);
                i.putExtra("name",name);
                i.putExtra("code","0");
                startActivity(i);
            }
        });

        rest_list1.setOnClickListener(new MyClickListener());
        rest_list2.setOnClickListener(new MyClickListener());
        rest_list3.setOnClickListener(new MyClickListener());

    }

    class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String what = Integer.toString(view.getId());
            Log.d("Homepage", what);
            Intent i2 = new Intent(Homepage.this,view_restaurant.class);
            i2.putExtra("id",what);
            i2.putExtra("name",name);
            startActivity(i2);
        }
    }
}