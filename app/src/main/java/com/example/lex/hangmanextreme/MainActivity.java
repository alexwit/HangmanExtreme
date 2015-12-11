package com.example.lex.hangmanextreme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GameplayInterface gameplay;
    TextView answerWord;
    EditText guessedChar;
    Button btncheckChar;
    Button btnRestart;
    TextView TVattempts;
    TextView TVwrongChars;
    TextView tvnumberGuessses;

    private SharedPreferences preferences;
    private static final String prefSettings = "settings";
    private static final String prefLength = "wordLength";
    private static final String prefAttempts = "attempts";
    private static final String prefMode = "mode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(prefSettings, Context.MODE_PRIVATE);

        // adjustable settings
        int userLength = preferences.getInt(prefLength, 4);
        int attempts = preferences.getInt(prefAttempts, 10);
        Boolean gameMode = preferences.getBoolean(prefMode, true);

        // identifies all widgets
        btncheckChar = (Button)findViewById(R.id.btnCharcheck);
        btnRestart = (Button)findViewById(R.id.btnRestart);
        TVattempts = (TextView)findViewById(R.id.TVNumberAttempts);
        answerWord = (TextView)findViewById(R.id.outputWord);
        TVwrongChars = (TextView) findViewById(R.id.TVwrongChars);
        tvnumberGuessses = (TextView) findViewById(R.id.tvNumberGuesses);
        guessedChar = (EditText) findViewById(R.id.etChar);

        // checks which gameplay need to be initialized
        if (gameMode) {
            gameplay = new EvilGameplay(this, userLength);
            Log.i("main", "evil");

        }
        else{
            gameplay = new GoodGameplay(this, userLength);
            Log.i("main", "good");
        }


        // sets preferred settings of the player
        gameplay.setWordLength(userLength);
        gameplay.setAttempts(attempts);

        setValuesWidgets();

//        answerWord.setText(String.valueOf(gameplay.getGuessedWord()));
//        TVattempts.setText(String.valueOf(gameplay.getAttempts()));


        btncheckChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // gets the Character from the user
                final Character C = guessedChar.getText().toString().toUpperCase().charAt(0);

                // checks if the character is in the word
                if (gameplay.checkChar(C)) {
                    // Updates the word to show the character on the correct spot
                    // is going to be integrated in ultimate version
                    for (int i = 0; i < gameplay.getCurrWord().length(); i++) {
                        if (gameplay.updateChar(i, C)) {
                            gameplay.getGuessedWord().setCharAt(i, C);
                            answerWord.setText(String.valueOf(gameplay.getGuessedWord()));
                        }
                    }
                    setValuesWidgets();
//                    tvnumberGuessses.setText(String.valueOf(gameplay.getNumberGuesses()));
                    // checks if the whole word is guessed
                    if (gameplay.checkWord()) {
                        Intent i = new Intent(MainActivity.this, won.class);
                        startActivity(i);
                    }
                    // Updates the wrong character list, the attempts left and guesses
                } else if (gameplay.updateWrongChar(C)) {
                    if (gameplay.getAttempts() == 0) {
                        Toast.makeText(MainActivity.this, "You've DIED!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, lose.class);
                        startActivity(i);
                    }
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    setValuesWidgets();
//                    TVwrongChars.setText(gameplay.getWrongChars().toString());
//                    tvnumberGuessses.setText(String.valueOf(gameplay.getNumberGuesses()));
//                    TVattempts.setText(String.valueOf(gameplay.getAttempts()));

                }

            }
        });

        // restarts all statistics of the game and chooses a new word
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameplay.restart();
                setValuesWidgets();
            }
        });
    }


    // sets all widgets with the gorrect values
    public void setValuesWidgets (){
        TVattempts.setText((String.valueOf(gameplay.getAttempts())));
        tvnumberGuessses.setText(String.valueOf(gameplay.getNumberGuesses()));
        TVwrongChars.setText(String.valueOf(gameplay.getWrongChars()));
        answerWord.setText(String.valueOf(gameplay.getGuessedWord()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings){

            Intent i = new Intent(MainActivity.this, Settings.class);
            startActivity(i);
        }
        if (id == R.id.action_highscore){

            Intent i = new Intent(MainActivity.this, highscore.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
