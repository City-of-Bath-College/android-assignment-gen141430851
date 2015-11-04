package com.example.gen14143085.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {


    private Button btnPlay;
    private Button btnHiScores;
    private Button btnAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnHiScores = (Button)findViewById(R.id.btnHiScores);
        btnAbout = (Button) findViewById(R.id.btnAbout);



        btnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        btnHiScores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(IntroActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });


        btnAbout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(IntroActivity.this, ProfileActivity.class);
            startActivity(i);
        }

    });}}
















