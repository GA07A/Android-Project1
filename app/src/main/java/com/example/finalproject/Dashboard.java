package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
TextView HomeP,ProfileP;
CardView cardView1,cardView2,cardView3,cardView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        HomeP = findViewById(R.id.homePage);
        ProfileP = findViewById(R.id.profileP);
        cardView1=findViewById(R.id.card1);
        cardView4=findViewById(R.id.card4);



        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,Home.class);
                startActivity(i);
            }
        });



        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,Profile.class);
                startActivity(i);
            }
        });

    }


}