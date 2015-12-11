package com.example.lex.hangmanextreme;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Papi lexus on 6-12-2015.
 */
public class EvilGameplay extends Gameplay {

    ArrayList<String> evilList;
    Loaddictionary loadDict;
    Gameplay gameplay;

    HashMap<String, ArrayList<String>> evilDictionary;
    ArrayList selectionWordList;

    public EvilGameplay(Context context, int wordLength){

        loadDict = new Loaddictionary();

        loadedList = loadDict.loadWords(context);

        evilList = makeEvilLengthArray(loadedList, wordLength);

        Log.i("evil", "evillist: " + evilList);

        String henk = setWord(evilList, wordLength);
        setWord(evilList, wordLength);

        setUnderscores();

        Log.i("evil", "setword: " + henk );

    }

    public ArrayList makeEvilLengthArray(ArrayList dictionary, int wordLength){

            selectionWordList = new ArrayList();
            int wordCount = 0;
            for(int i = 0; i < dictionary.size(); i++){

                if(dictionary.get(i).toString().length()==wordLength){
                    selectionWordList.add(wordCount, dictionary.get(i));
                }
            }
            return selectionWordList;
        }


        public ArrayList evilCheckList(ArrayList list, Character c){
            ArrayList<String> tempArrayList = new ArrayList();
            evilDictionary = new HashMap<>();
            for(int i = 0; i < list.size();i++){
                String key = "";
                String w = list.get(i).toString();
                for(int j = 0; j < w.length(); j++){
                    if(w.charAt(j)== c){
                        key = key + c;
//                        Log.i("evil", "print key in the make: " + key);
                    }
                    else{
                        key = key + "_";
                    }
                }
                Log.i("evil", "print key: " + key);
                if(evilDictionary.get(key)==null){
                    evilDictionary.put(key,new ArrayList<String>());
                    evilDictionary.get(key).add(w);
                    Log.i("evil", "dict key : " + evilDictionary.get(key));

                }
                else{
                    evilDictionary.get(key).add(w);
                    Log.i("Evil", "print values: " + evilDictionary.get(key));
                }
            }
            int sizeCount = 0;
            for(Map.Entry<String, ArrayList<String>> entry : evilDictionary.entrySet()){
                Log.i("Evil", "print values:" + entry.getValue());
                Log.i("Evil", "print keysize: " + entry.getValue().size());
                    if(entry.getValue().size()> sizeCount) {
                        sizeCount = entry.getValue().size();
                        tempArrayList = entry.getValue();
                    }
            }
            Log.i("evilcheck", "arraylist" + tempArrayList);
            return tempArrayList;

        }

}
