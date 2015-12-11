package com.example.lex.hangmanextreme;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Papi lexus on 8-12-2015.
 */
public class Loaddictionary {

    ArrayList<String> wordsList;
    String [] wordsArray;

    public ArrayList<String> loadWords(Context context){

        Resources res = context.getResources();
        wordsArray = res.getStringArray(R.array.words_small);

        wordsList = new ArrayList<>(Arrays.asList(wordsArray));

        return wordsList;

    }

}

