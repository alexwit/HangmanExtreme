package com.example.lex.hangmanextreme;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Papi lexus on 6-12-2015.
 */
public class EvilGameplay implements GameplayInterface {

    private int attemptsleft = 6;
    //    int attempts = preferences.getInt(prefAttempts, 6);


    private int numberGuesses = 0;

    // creates a beta word list
    private StringBuilder guessedWord;

    private int wordLength = 4;

    private String currWord;

    private StringBuilder wrongChars;

    private ArrayList<String> evilList;
    private Loaddictionary loadDict;

    private HashMap<String, ArrayList<String>> evilMap;
    private ArrayList selectionWordList;
    private ArrayList loadedList;


    public EvilGameplay(Context context, int wordLength){

        loadDict = new Loaddictionary();

        loadedList = loadDict.loadWords(context);

        evilList = makeEvilLengthArray(loadedList, wordLength);

        setWord(evilList ,wordLength);

        setUnderscores();

    }

    private ArrayList makeEvilLengthArray(ArrayList dictionary, int wordLength){

            selectionWordList = new ArrayList();
            int wordCount = 0;
            for(int i = 0; i < dictionary.size(); i++){

                if(dictionary.get(i).toString().length()==wordLength){
                    selectionWordList.add(wordCount, dictionary.get(i));
                }
            }
            return selectionWordList;
        }


    private ArrayList evilCheckList(ArrayList list, Character c){
        ArrayList<String> tempArrayList = new ArrayList();
        evilMap = new HashMap<>();
        // for every word in the list
        for(int i = 0; i < list.size();i++){
            String key = "";
            String word = list.get(i).toString();
            // Creates a key for the map
            for(int j = 0; j < word.length(); j++){
                if(word.charAt(j)== c){
                        key = key + c;
                    }
                    else{
                        key = key + "_";
                    }
                }
                // checks if key already exists
                if(evilMap.get(key)==null){
                    evilMap.put(key, new ArrayList<String>());
                    evilMap.get(key).add(word);
                }
                // adds word to existing key
                else{
                    evilMap.get(key).add(word);
                }
            }
        // checks which key contains most words
        int sizeCount = 0;
        for(Map.Entry<String, ArrayList<String>> entry : evilMap.entrySet()){
            if(entry.getValue().size()> sizeCount) {
                        sizeCount = entry.getValue().size();
                        tempArrayList = entry.getValue();
            }
        }
        Log.i("evilcheck", "arraylist" + tempArrayList);
        return tempArrayList;

        }

    @Override
    public String setWord(ArrayList list, int wordLength) {
        // chooses a random word from list
        do {
            int index = new Random().nextInt(list.size());
            currWord = list.get(index).toString();
        } while (currWord.length() != wordLength);
        return currWord;
    }

    @Override
    public void setUnderscores() {
        guessedWord = new StringBuilder();
        wrongChars = new StringBuilder();
        for (int i = 0; i < currWord.length(); i++){
            guessedWord.append("-");
        }
        Log.i("evil", "guessedWord " + guessedWord);
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
    public void restart() {
        Log.e("gameplay","restart");
        setWord(makeEvilLengthArray(loadedList, wordLength), wordLength);
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
        evilCheckList(evilList, C);
        Log.i("evil", "currword: " + currWord);
        updateGuesses();
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
}
