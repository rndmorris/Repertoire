/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import Repertoire.JavaClient.Controllers.GameController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Tucker
 */
public class Dictionary {

    public String file = "testDictionary.JSON";
    private final String case1 = "hiragana";
    private final String case2 = "katakana";
    private final String case3 = "jlptn5";
    private final String case4 = "jlptn4";
    private final String case5 = "jlptn3";
    private final String case6 = "jlptn2";
    private final String case7 = "jlptn1";
    private final String case8 = "null8";
    private final String case9 = "null9";
    public static ObservableList<Card> data = FXCollections.observableArrayList();

    public Dictionary() {

    }

    public boolean dataInit(URL name) {

        JSONParser parser = new JSONParser();
        HashMap<String, Card> set = new HashMap<>(); //unmastered 
        ArrayList cards = new ArrayList();
        int count = 0;
        try {

            JSONObject object = (JSONObject) parser.parse(new InputStreamReader(name.openStream()));

            JSONArray array = (JSONArray) object.get("kanji");

            //(JSONArray) parser.parse(new FileReader(name));
            Iterator it = array.iterator();

            while (it.hasNext()) {
                JSONObject card = (JSONObject) it.next();

                String category = (String) card.get("category");
                String character = (String) card.get("character");
                String readingOne = (String) card.get("onyomi");
                String readingTwo = (String) card.get("kunyomi");
                String meaning = (String) card.get("meaning");

                int difficulty = convertDifficulty(category);

                Card c = new Card(character, difficulty, readingOne, readingTwo, meaning);
                
                //add to an unmastered deck
                set.put(Integer.toString(count), c);
                
                data.add(c);
                cards.add(c); // needs to be deck instead of full dictionary

                System.out.println(difficulty + " " + character + " " + readingOne + " "
                        + readingTwo + " " + meaning);

                count++;
            }

            Program.user.setUnmastCount(count);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }
        
        if (Program.newUser) {
        //set user unmastered deck to newly initialized dictionary
        
        Program.user.setUnmastered(set);
        
        //set working game deck to newly initialized dictionary (should be a deck)
        GameController.deck = cards;
        }

        return true;

    }

    public int convertDifficulty(String category) {

        int value = 0;

        switch (category) {

            case case1:
                value = 1;
                break;
            case case2:
                value = 2;
                break;
            case case3:
                value = 3;
                break;
            case case4:
                value = 4;
                break;
            case case5:
                value = 5;
                break;
            case case6:
                value = 6;
                break;
            case case7:
                value = 7;
                break;
            case case8:
                value = 8;
                break;
            case case9:
                value = 9;
                break;

        }

        return value;

    }
    

}
