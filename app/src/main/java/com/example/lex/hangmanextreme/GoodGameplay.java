package com.example.lex.hangmanextreme;

import android.content.Context;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 297ed9890a5e23e896a69f0d2c978dd07ab7f415
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
<<<<<<< HEAD
=======
=======
>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124
>>>>>>> 297ed9890a5e23e896a69f0d2c978dd07ab7f415

/**
 * Created by Papi lexus on 3-12-2015.
 */
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 297ed9890a5e23e896a69f0d2c978dd07ab7f415

public class GoodGameplay implements GameplayInterface {

    private int attemptsleft = 6;

    private int numberGuesses = 0;

    private StringBuilder guessedWord;

    private int wordLength = 4;

    private String currWord;

    private ArrayList loadedList;

    private StringBuilder wrongChars;

    private Loaddictionary loadDict;

        public GoodGameplay(Context context,int length){

<<<<<<< HEAD
=======
=======
public class GoodGameplay extends Gameplay {

        Gameplay gameplay;
        Loaddictionary loadDict;

        public GoodGameplay(Context context,int length){

            gameplay = new Gameplay();

>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124
>>>>>>> 297ed9890a5e23e896a69f0d2c978dd07ab7f415
            loadDict = new Loaddictionary();

            loadedList = loadDict.loadWords(context);

            setWord(loadedList,wordLength);

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 297ed9890a5e23e896a69f0d2c978dd07ab7f415
            setUnderscores();

        }


    @Override
    public String setWord(ArrayList list, int wordLength) {
        do {
            int index = new Random().nextInt(list.size());
            currWord = list.get(index).toString();
        } while (currWord.length() != wordLength);
        //Log.i("Gameplay", " set word currWordlengte = " + currWord.length());
        return currWord;
    }

    @Override
    public void setUnderscores() {
        Log.i("Gameplay", "currWordlengte = " + currWord.length() + " CURRWORD= " + currWord);
        guessedWord = new StringBuilder();
        wrongChars = new StringBuilder();
        for (int i = 0; i < currWord.length(); i++){
            guessedWord.append("-");
        }
    }

    @Override
    public void setWordLength(int userLength) {
        wordLength = userLength;
    }

    @Override
    public boolean updateChar(int i, Character C) {
        if (currWord.charAt(i) == C){
            return true;
        }
        return false;
    }

    @Override
    public int getAttempts() {
        return attemptsleft;
    }

    @Override
    public int getNumberGuesses() {
        return numberGuesses;
    }

    @Override
    public boolean updateWrongChar(Character C) {
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

    @Override
    public void updateGuesses() {
        numberGuesses++;
    }

    @Override
    public void updateAttempts() {
        attemptsleft--;
    }

    @Override
    public void setAttempts(int attempt) {
        attemptsleft = attempt;
    }

    @Override
    public StringBuilder getGuessedWord() {
        return guessedWord;
    }

    @Override
    public void restart() {
        Log.e("gameplay","restart");
        setWord(loadedList, wordLength);
        setUnderscores();
        attemptsleft = getAttempts();
        numberGuesses = getNumberGuesses();
    }

    @Override
    public boolean checkWord() {
        return (guessedWord.toString().equals(currWord));
    }

    @Override
    public boolean checkChar(Character C) {
        if(currWord.contains(C.toString())){
            return true;
        };
        return false;
    }

    @Override
    public String getCurrWord() {
        return currWord;
    }

    @Override
    public StringBuilder getWrongChars() {
        return wrongChars;
    }
<<<<<<< HEAD
=======
=======

        }


>>>>>>> bebb396609ba22e90af6d101bad8bca97e339124
>>>>>>> 297ed9890a5e23e896a69f0d2c978dd07ab7f415
}
