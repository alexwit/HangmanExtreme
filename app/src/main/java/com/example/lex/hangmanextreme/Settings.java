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

<<<<<<< HEAD
    SeekBar seekBarLengthWord;
    SeekBar seekBarAmountAttempts;
    TextView TVword;
    TextView TVguess;
    Button btnReturn;
    Switch GoodOrEvilSwitch;
=======
    SeekBar seekBar1;
    SeekBar seekBar2;
    TextView TVword;
    TextView TVguess;
    Button btnReturn;
    Switch mySwitch;
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private static final String prefSettings = "settings";
    private static final String prefLength = "wordLength";
    private static final String prefAttempts = "attempts";
<<<<<<< HEAD
    private static final String prefMode = "mode";
=======
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.settings);

        preferences = getSharedPreferences(prefSettings, Context.MODE_PRIVATE);


        seekBarLengthWord = (SeekBar) findViewById(R.id.seekbarword);
        seekBarAmountAttempts = (SeekBar) findViewById(R.id.seekBarGues);

        GoodOrEvilSwitch = (Switch)findViewById(R.id.switchEvil);
=======
        setContentView(R.layout.activity_activity_settings);

        preferences = getSharedPreferences(prefSettings, Context.MODE_PRIVATE);

        seekBar1 = (SeekBar) findViewById(R.id.seekbarword);
        seekBar2 = (SeekBar) findViewById(R.id.seekBarGues);

        mySwitch = (Switch)findViewById(R.id.switchEvil);
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124

        btnReturn = (Button) findViewById(R.id.btnReturnSettings);

        TVword = (TextView) findViewById(R.id.TVgiveWordLenght);
        TVguess = (TextView) findViewById(R.id.TVshowguesses);

<<<<<<< HEAD
        GoodOrEvilSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
=======
        //set the switch to ON
        mySwitch.setChecked(true);


        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            boolean evilmode = true;
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

<<<<<<< HEAD
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
=======
                if(isChecked){
                   mySwitch.setChecked(true);
                }else{
                    mySwitch.setChecked(false);
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124
                }

            }
        });

<<<<<<< HEAD
        seekBarLengthWord.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
=======
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124

            int wordLength = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<1){
                    progress=1;
                }
                wordLength = progress;
                TVword.setText(progress + "/" + seekBar.getMax());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

<<<<<<< HEAD
=======
                Log.i("Settings", "wat is de progress: " + wordLength);

>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("Settings", "stop tracking" + wordLength);
                TVword.setText(wordLength + "/" + seekBar.getMax());
                editor = preferences.edit();
                editor.putInt(prefLength, wordLength);
                editor.commit();
            }
        });

<<<<<<< HEAD
        seekBarAmountAttempts.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
=======
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124

            int amountAttempts = 6;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<1){
                    progress=1;
                }
                amountAttempts = progress;
                TVguess.setText(progress + "/" + seekBar.getMax());

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
<<<<<<< HEAD
=======
                Log.i("Settings", "wat is de progress: " + amountAttempts);
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124
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