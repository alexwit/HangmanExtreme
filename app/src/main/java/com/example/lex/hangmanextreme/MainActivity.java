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

    Gameplay gameplay;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameplay = new Gameplay();
        preferences = getSharedPreferences(prefSettings, Context.MODE_PRIVATE);

        btncheckChar = (Button)findViewById(R.id.btnCharcheck);
        btnRestart = (Button)findViewById(R.id.btnRestart);

        //final Integer attempts = preferences.getInt(prefAttempts, 6);


        TVattempts = (TextView)findViewById(R.id.TVNumberAttempts);
        answerWord = (TextView)findViewById(R.id.outputWord);
        TVwrongChars = (TextView) findViewById(R.id.TVwrongChars);
        tvnumberGuessses = (TextView) findViewById(R.id.tvNumberGuesses);

        guessedChar = (EditText) findViewById(R.id.etChar);

        // todo: make clear where attempts is used for
        TVattempts.setText(String.valueOf(gameplay.attemptsleft));

        gameplay.setUnderscores();

        // todo explain what is happing and change outputword
        answerWord.setText(String.valueOf(gameplay.guessedWord));


        btncheckChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Character C = guessedChar.getText().toString().charAt(0);
                Log.i("Mainactivity", "wat is de char van de user: " + C);

                if (gameplay.checkChar(C)) {

                    for (int i = 0; i < gameplay.currWord.length(); i++) {
                        if (gameplay.updateChar(i, C)) {
                            gameplay.guessedWord.setCharAt(i, C);
                            answerWord.setText(String.valueOf(gameplay.guessedWord));
                        }
                    }
                    gameplay.updateGuesses();
                    Log.i("Mainactivity", "Guesses + 1 " + gameplay.numberGuesses);
                    tvnumberGuessses.setText(String.valueOf(gameplay.numberGuesses));

                    if (gameplay.checkWord()) {
//                    Toast.makeText(MainActivity.this, "You've WON!!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, won.class);
                        startActivity(i);
                    }

                } else if (gameplay.updateWrongChar(C)) {
                    if (gameplay.attemptsleft == 0) {
                        Toast.makeText(MainActivity.this, "You've DIED!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, lose.class);
                        startActivity(i);
                    }

                    //todo door de preferences kan je de attempts niet meer aanpassen
                    gameplay.updateAttempts();

                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();

                    TVwrongChars.setText(gameplay.wrongChars.toString());
                    Log.i("Mainactivity", "Guesses 2.0 + 1" + gameplay.numberGuesses);

                    tvnumberGuessses.setText(String.valueOf(gameplay.numberGuesses));

                    Log.i("Mainactivity", "attempts + -1" + gameplay.numberGuesses);

                    TVattempts.setText(String.valueOf(gameplay.attemptsleft));

                }
//                else if (gameplay.checkWord()) {
//                    Toast.makeText(MainActivity.this, "You've WON!!", Toast.LENGTH_LONG).show();
//                }

            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameplay.restart();
                gameplay.setUnderscores();
                TVattempts.setText((String.valueOf(gameplay.attemptsleft)));
                tvnumberGuessses.setText(String.valueOf(gameplay.numberGuesses));
                TVwrongChars.setText(String.valueOf(gameplay.wrongChars));
                answerWord.setText(String.valueOf(gameplay.guessedWord));
            }
        });


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

            Intent i = new Intent(MainActivity.this, activity_settings.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_highscore){

            Intent i = new Intent(MainActivity.this, highscore.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
