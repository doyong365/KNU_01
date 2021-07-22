package com.example.test_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
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

public class view_restaurant extends AppCompatActivity {
    FirebaseDatabase fDB = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    String id,name;
    RelativeLayout test_m;
    TextView resname, menuname, menuprice;
    RatingBar rb;
    ImageView backpic, iconpic, menupic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_restaurant);
        resname = findViewById(R.id.textView);
        menuname = findViewById(R.id.TextView_menuname);
        menuprice = findViewById(R.id.TextView_menuprice);
        backpic = findViewById(R.id.imageView6);
        iconpic = findViewById(R.id.imageView5);
        menupic = findViewById(R.id.ImageView_menupic);
        rb = findViewById(R.id.ratingBar);
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        name = intent.getExtras().getString("name");
        DatabaseReference dbRf = fDB.getReference();
        DatabaseReference getImage;
        String cnt;
        if(Integer.parseInt(id) == 2131231058){
            cnt = "1";
        }
        else if(Integer.parseInt(id) == 2131231059){
            cnt = "2";
        }
        else{
            cnt = "3";
        }
        getImage = dbRf.child("res").child(cnt).child("resname");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                resname.setText(link);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(view_restaurant.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(cnt).child("resrate");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                float link = dataSnapshot.getValue(Float.class);
                rb.setRating(link);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(view_restaurant.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(cnt).child("resbackground");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(backpic);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(view_restaurant.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(cnt).child("resicon");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(iconpic);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(view_restaurant.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });


        test_m = findViewById(R.id.test_menu);
        getImage = dbRf.child("res").child(cnt).child("resmenu").child("resmenu1").child("resmenu1pic");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(menupic);
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(view_restaurant.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(view_restaurant.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        getImage = dbRf.child("res").child(cnt).child("resmenu").child("resmenu1").child("resmenu1price");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                int link = dataSnapshot.getValue(Integer.class);
                menuprice.setText(Integer.toString(link) + "원");
            }
            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                Toast.makeText(view_restaurant.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        test_m.setClickable(true);
        test_m.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view_restaurant.this,pay.class); // intent가 화면전환 느낌
                intent.putExtra("id",cnt);
                intent.putExtra("name",name);
                startActivity(intent); //intent 실행
            }
        });

    }
}