package com.example.lex.hangmanextreme;

import android.content.Context;

/**
 * Created by Papi lexus on 3-12-2015.
 */
public class GoodGameplay extends Gameplay {

        Gameplay gameplay;
        Loaddictionary loadDict;

        public GoodGameplay(Context context,int length){

            gameplay = new Gameplay();

            loadDict = new Loaddictionary();

            loadedList = loadDict.loadWords(context);

            setWord(loadedList,wordLength);


        }


}
