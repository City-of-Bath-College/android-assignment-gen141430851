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

import io.paperdb.Paper;


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
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTrue = (Button)findViewById(R.id.btnTrue);
        btnFalse = (Button)findViewById(R.id.btnFalse);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        imgPicture = (ImageView)findViewById(R.id.imgPicture);
        lblScore = (TextView)findViewById(R.id.lblScore);
        lblHighscores = (TextView)findViewById(R.id.lblHighscores);
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


   private void generateQuestions(){

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


    }

    private void setUpQuestion(){

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

        if (answer == expectedAnswer){
            Toast.makeText(MainActivity.this, "Correct!!", Toast.LENGTH_SHORT).show();
            score++;
            lblScore.setText("Score: " +Integer.toString(score));
        }

        else {
            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();
        }

        setUpQuestion();
    }

    private void endGame(){
        /*
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations")
                .setMessage("You Scored " + score + " points this round!")
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
*/
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Title");

                        final EditText input = new EditText(this);


                        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder.setView(input);

                        // Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                playerName = input.getText().toString();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();

                        HighScoreObject highScore = new HighScoreObject(score,"MyName", new Date().getTime());


                        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

                        highScores.add(highScore);

                        Paper.book().write("highscores", highScores);

                        finish();
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
