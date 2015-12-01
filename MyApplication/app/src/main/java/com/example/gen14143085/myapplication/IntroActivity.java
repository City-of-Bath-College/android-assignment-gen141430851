package com.example.gen14143085.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class IntroActivity extends AppCompatActivity {

//declaring variables
    private Button btnPlay;
    private Button btnHiScores;
    private Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
//initialising variables
        btnPlay = (Button)findViewById(R.id.btnPlay); //connecting the buttons
        btnHiScores = (Button)findViewById(R.id.btnHiScores);
        btnAbout = (Button) findViewById(R.id.btnAbout);


       //On click listeners
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
                Intent i = new Intent(IntroActivity.this, HighScoreActivity.class);
                startActivity(i);
            }
        });


        btnAbout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(IntroActivity.this, ProfileActivity.class);
            startActivity(i);
        }

    });}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }}











