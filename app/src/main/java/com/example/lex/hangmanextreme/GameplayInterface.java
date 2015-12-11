package com.example.lex.hangmanextreme;

import java.util.ArrayList;

/**
 * Created by Papi lexus on 11-12-2015.
 */
public interface GameplayInterface {


    // sets the word that need to be guessed
    String setWord(ArrayList list, int wordLength);

    // sets the underscores from the given word
    void setUnderscores();

    void setWordLength(int userLength);

    // updates the char
    boolean updateChar(int i, Character C);

    int getAttempts();

    int getNumberGuesses();

    boolean updateWrongChar(Character C);

    void updateGuesses();

    void updateAttempts();

    void setAttempts(int attempt);

    StringBuilder getGuessedWord();

    void restart();

    boolean checkWord();

    boolean checkChar(Character C);

    String getCurrWord();

    StringBuilder getWrongChars();
}
