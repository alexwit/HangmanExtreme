package com.example.lex.hangmanextreme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    SeekBar seekBarLengthWord;
    SeekBar seekBarAmountAttempts;
    TextView TVword;
    TextView TVguess;
    Button btnReturn;
    Switch GoodOrEvilSwitch;

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private static final String prefSettings = "settings";
    private static final String prefLength = "wordLength";
    private static final String prefAttempts = "attempts";
    private static final String prefMode = "mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        preferences = getSharedPreferences(prefSettings, Context.MODE_PRIVATE);


        seekBarLengthWord = (SeekBar) findViewById(R.id.seekbarword);
        seekBarAmountAttempts = (SeekBar) findViewById(R.id.seekBarGues);

        GoodOrEvilSwitch = (Switch)findViewById(R.id.switchEvil);

        btnReturn = (Button) findViewById(R.id.btnReturnSettings);

        TVword = (TextView) findViewById(R.id.TVgiveWordLenght);
        TVguess = (TextView) findViewById(R.id.TVshowguesses);

        GoodOrEvilSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    GoodOrEvilSwitch.setChecked(true);
                    editor = preferences.edit();
                    editor.putBoolean(prefMode, true);
                    editor.commit();
                } else {
                    GoodOrEvilSwitch.setChecked(false);
                    editor = preferences.edit();
                    editor.putBoolean(prefMode, false);
                    editor.commit();
                }

            }
        });

        seekBarLengthWord.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int wordLength = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<1){
                    progress=1;
                }
                wordLength = progress;
                TVword.setText(progress + "/" + seekBar.getMax());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("Settings", "stop tracking" + wordLength);
                TVword.setText(wordLength + "/" + seekBar.getMax());
                editor = preferences.edit();
                editor.putInt(prefLength, wordLength);
                editor.commit();
            }
        });

        seekBarAmountAttempts.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int amountAttempts = 6;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<1){
                    progress=1;
                }
                amountAttempts = progress;
                TVguess.setText(progress + "/" + seekBar.getMax());

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("Settings", "stop tracking" + amountAttempts);
                TVguess.setText(amountAttempts + "/" + seekBar.getMax());
                editor = preferences.edit();
                editor.putInt(prefAttempts, amountAttempts);
                editor.commit();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.this, MainActivity.class);
                startActivity(i);
            }

        });

    }
}