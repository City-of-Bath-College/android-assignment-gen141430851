package com.example.gen14143085.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


import io.paperdb.Paper;

public class HighScoreActivity extends AppCompatActivity {
    //defining variables
    private ListView listView;
    private List<HighScoreObject> highScores;
    private Button btnReset;
    private TextView lblNoScores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);


        //Set no scores textview
        lblNoScores = (TextView)findViewById(R.id.lblNoScores);
        lblNoScores.setVisibility(View.INVISIBLE);

        //Display Scores
        Paper.init(this);
        displayScores();

        //Reset button
        btnReset = (Button)findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reset High scores
                Paper.book().delete("highscores");

                //Reinflate view
                setContentView(R.layout.activity_high_score);

            }
    });
}

    private class HighscoreAdapter extends ArrayAdapter<HighScoreObject> {

        public HighscoreAdapter(List<HighScoreObject> items) {
            super(HighScoreActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                               R.layout.row_highscore, null);
            }

            //get the highscore object
            HighScoreObject highScore = highScores.get(position);
            Date date = new Date(highScore.getTimestamp());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //Set Text
            TextView lblTitle = (TextView)convertView.findViewById(R.id.lblTitle);
            String strOutput = (position + 1) + ". " + highScore.getName()  + " " + dateFormat.format(date) + " : " + + highScore.getScore() + " points";
            lblTitle.setText(strOutput);

            return convertView;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_high_score, menu);
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
            }


    protected void displayScores(){

        // Load High Scores
        highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        if(highScores.size() > 0){
            // Display score list
            HighscoreAdapter adapter = new HighscoreAdapter(highScores);
            listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(adapter);

            // Hide no Scores
            lblNoScores.setVisibility(View.INVISIBLE);
        }
        else{
            // Display no scores
            lblNoScores.setVisibility(View.VISIBLE);
        }}}

