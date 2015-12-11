package com.example.lex.hangmanextreme;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Papi lexus on 10-12-2015.
 */
public class Gameplay {

    private SharedPreferences preferences;
    private static final String prefSettings = "settings";
    private static final String prefLength = "wordLength";
    private static final String prefAttempts = "attempts";

    //preferences = getSharedPreferences(prefSettings, Context.MODE_PRIVATE);
    // initializes attempts
//
    private int attemptsleft = 6;
    //    int attempts = preferences.getInt(prefAttempts, 6);
    EvilGameplay evilGameplay;
    // number of guesses
    private int numberGuesses = 0;

    // creates a beta word list
    StringBuilder guessedWord;

    ArrayList<String> loadedList;

    int wordLength = 4;

    String currWord;

    //ArrayList<String> evilList;
    // array with wrong char's
    StringBuilder wrongChars;



    public String setWord(ArrayList List, int wordLength){
        do {
            int index = new Random().nextInt(List.size());
            currWord = List.get(index).toString();
        } while (currWord.length() != wordLength);
        Log.i("Gameplay", "currWordlengte = " + currWord.length());
        return currWord;
    }

    public void setUnderscores(){
        Log.i("Gameplay", "currWordlengte = " + currWord.length() + " CURRWORD= " + currWord);
        guessedWord = new StringBuilder();
        wrongChars = new StringBuilder();
        for (int i = 0; i < currWord.length(); i++){
            guessedWord.append("-");
        }
    }


    public void setWordLength(int userLength){wordLength = userLength;}

    public boolean checkChar(Character C) {
        if(currWord.contains(C.toString())){
            return true;
        };
        return false;
    }

    public boolean updateChar(int i, Character C){
        if (currWord.charAt(i) == C){
            return true;
        }
        return false;
    }

    public void restart() {
        Log.e("gameplay","restart");
        setWord(loadedList, wordLength);
        attemptsleft = 6;
        //todo set attempts to preferences

        numberGuesses = 0;
    }

    public boolean checkWord() {
        return (guessedWord.toString().equals(currWord));
    }

    public int getAttempts() { return attemptsleft;}

    public int getNumberGuesses(){
        return numberGuesses;
    }

    public void setAttempts(int attempt){
        attemptsleft = attempt;
    }

    public StringBuilder getWrongChars() {
        return wrongChars;
    }

    public boolean updateWrongChar(Character C){
        for (int i = 0; i < wrongChars.length(); i++){
            Character wrongLetter = wrongChars.charAt(i);
            if(C == wrongLetter){
                return false;
            }
        }
        wrongChars.append(C + " ");
        updateGuesses();
        updateAttempts();
        return true;
    }

    public void updateGuesses() {
        numberGuesses++;
    }

    public void updateAttempts() {
        //todo attempts are not local anymore
        attemptsleft--;
    }

}
