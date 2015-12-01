package com.example.gen14143085.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;

import io.paperdb.Paper;

//defining variables
public class MainActivity extends AppCompatActivity {

    private Button btnTrue;
    private Button btnFalse;
    private TextView lblQuestion;
    private ImageView imgPicture;
    private TextView lblScore;
    private TextView lblHighscores;
    private List<QuestionObject> questions;


    private QuestionObject currentQuestion;
    private int index;
    private int score;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTrue = (Button)findViewById(R.id.btnTrue);
        btnFalse = (Button)findViewById(R.id.btnFalse);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        imgPicture = (ImageView)findViewById(R.id.imgPicture);
        lblScore = (TextView)findViewById(R.id.lblScore);
        lblQuestion.setText("Is London the capital of England?");
        imgPicture.setImageResource(R.drawable.englandflag);

        index = 0;
        score = 0;

        //onclick listeners
        btnFalse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

        generateQuestions();
        setUpQuestion();
        Paper.init(this);
    }


   private void generateQuestions(){ //loads question list

       questions = new ArrayList<>();

       questions.add(new QuestionObject("Is the capital of England London?", true, R.drawable.englandflag));
       questions.add(new QuestionObject("Is the capital of France Paris?", true, R.drawable.franceflag));
       questions.add(new QuestionObject("Is the capital of Egypt Giza?", false, R.drawable.egyptflag));
       questions.add(new QuestionObject("Is the capital of Czech Republic Prague?", true, R.drawable.czechflag));
       questions.add(new QuestionObject("Is the capital of Argentina Córdoba?", false, R.drawable.argentinaflag));
       questions.add(new QuestionObject("Is the capital of Liechtenstein Vaduz?", true, R.drawable.liechtensteinflag));
       questions.add(new QuestionObject("Is the capital of Iceland Reykjavík?", true, R.drawable.icelandflag));
       questions.add(new QuestionObject("Is the capital of Indonesia Jakarta?", true, R.drawable.indonesiaflag));
       questions.add(new QuestionObject("Is the capital of Cameroon Douala?", false, R.drawable.cameroonflag));
       questions.add(new QuestionObject("Is the capital of Uzbekistan Tashkent?", true, R.drawable.uzbekistanflag));

       //more questions
       questions.add(new QuestionObject("Is the capital of Bosnia and Herzegovina Sarajevo?", true, R.drawable.bosniaflag));
       questions.add(new QuestionObject("Is the capital of Sweden Stockholm?", true, R.drawable.swedenflag));
       questions.add(new QuestionObject("Is the capital of Mali Sikasso?", false, R.drawable.maliflag));
       questions.add(new QuestionObject("Is the capital of Holland Amsterdam?", true, R.drawable.netherlandsflag));
       questions.add(new QuestionObject("Is the capital of Senegal Pikine?", false, R.drawable.senegalflag));
       questions.add(new QuestionObject("Is the capital of Cuba Santiago de Cuba?", false, R.drawable.cubaflag));
       questions.add(new QuestionObject("Is the capital of Canada Toronto?", false, R.drawable.canadaflag));
       questions.add(new QuestionObject("Is the capital of South_Korea Incheon?", false, R.drawable.southkoreaflag));
       questions.add(new QuestionObject("Is the capital of Andorra Canillo?", false, R.drawable.andorraflag));
       questions.add(new QuestionObject("Is the capital of Palau Ngerulmud?", true, R.drawable.palauflag));


    }

    private void setUpQuestion(){ //loads text and image of each question

        if (index == questions.size()) {
            Log.d("JOSH_APP", "ended all the questions");
            endGame();
        }

        else {
            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());

            index++;
        }
    }


    private void determineButtonPress(boolean answer){
        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer){ //if answer is correct add 1 to score
            Toast.makeText(MainActivity.this, "Correct!!", Toast.LENGTH_SHORT).show();
            score++;
            lblScore.setText("Score: " +Integer.toString(score));
        }

        else {
            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();
        }

        setUpQuestion(); //sets up next question
    }

    private void endGame(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("You scored " + score + " points! Please enter your name:");

        final EditText input = new EditText(this);


        //Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        //Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            playerName = input.getText().toString();

            //Create new highscore
            HighScoreObject highScore = new HighScoreObject();
            highScore.score = score;
            highScore.name = playerName;
            highScore.timestamp = new Date().getTime();

            //Load highscores using paper
            List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

            //Add item
            highScores.add(highScore);

            //Sort highscores by score then timestamp

            Collections.sort(highScores, new Comparator<HighScoreObject>() {
                @Override
                public int compare(HighScoreObject lhs, HighScoreObject rhs) {
                    //First compare scores
                    if(lhs.score > rhs.score){
                        return 1;
                    }
                    else if(lhs.score < rhs.score){
                        return -1;
                    }
                    //if scores are equal, whichever score was set first will be on top
                    else if(lhs.timestamp > rhs.timestamp){
                        return 1;
                    }
                    else if(lhs.timestamp < rhs.timestamp){
                        return -1;
                    }
                    else{
                        //Scores and timestamp equal
                        return 0;
                    }
                }
            });

            // Reverse highscores
            Collections.reverse(highScores);

            // Write using paper
            Paper.book().write("highscores", highScores);
            Log.d("JOSH_APP", "Saving high scores!");

            finish();
        }
    });

    //cancel button
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            finish();
        }
    });

    builder.show();

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
