package com.example.lex.hangmanextreme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

public class activity_settings extends AppCompatActivity {


    SeekBar seekBar1;
    SeekBar seekBar2;
    TextView TVword;
    TextView TVguess;
    Button btnReturn;
    NumberPicker lengthPick, attemptPick;

    private SharedPreferences preferences;
    private static final String prefSettings = "settings";
    private static final String prefLength = "wordLength";
    private static final String prefAttempts = "attempts";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_settings);

        preferences = getSharedPreferences(prefSettings, Context.MODE_PRIVATE);

        seekBar1 = (SeekBar) findViewById(R.id.seekbarword);
        seekBar2 = (SeekBar) findViewById(R.id.seekBarGues);

        btnReturn = (Button) findViewById(R.id.btnReturnSettings);

        TVword = (TextView) findViewById(R.id.TVgiveWordLenght);
        TVguess = (TextView) findViewById(R.id.TVshowguesses);




        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int wordLength = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wordLength = progress;
                TVword.setText(progress + "/" + seekBar.getMax());

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                Log.i("Settings", "wat is de progress: " + wordLength);

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("Settings", "stop tracking" + wordLength);
                TVword.setText(wordLength + "/" + seekBar.getMax());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(prefLength, wordLength);
                editor.commit();
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int amountAttempts = 6;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amountAttempts = progress;
                TVguess.setText(progress + "/" + seekBar.getMax());

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                Log.i("Settings", "wat is de progress: " + amountAttempts);

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("Settings", "stop tracking" + amountAttempts);
                TVguess.setText(amountAttempts + "/" + seekBar.getMax());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(prefAttempts, amountAttempts);
                editor.commit();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_settings.this, MainActivity.class);
                startActivity(i);
            }

        });

    }
}