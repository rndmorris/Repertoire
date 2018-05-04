/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.FileNotFoundException;
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
public final class Dictionary {

    public static ObservableList<Card> data = FXCollections.observableArrayList();
    private static int unmastCount;
    private static int mastCount;
    private static HashMap<String, Card> tempMast;
    private static HashMap<String, Card> tempUnMast;
    private static ArrayList<Card> deck;
    
    public static boolean dataLoaded = false;

    private Dictionary() {}
    
    public static boolean dataInit(URL name) {

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
            //for (int i = 0; i < set.size() ; i++) {
                
            //}
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Heres the error");

        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }  


        //set user unmastered deck to newly initialized dictionary
        Set userSet = new Set();
     
        //    tempUnMast = new HashMap<>();//done with all declarations 
        //    tempMast = new HashMap<>();//
         //   deck = new ArrayList<>();//
        //    unmastCount = 0;//
        //    mastCount = 0;// done with all declarations
            
          
            userSet.initSet(set);
            
           
       // }
        System.out.println("Done within loading data");
        
            Program.user.setSet(userSet);
            
             
            userSet.setActiveDeck(0);
            Program.newUser = false;
            
//        }
        //GameController.deck = cards;
        

        return true;

    }

    public static int convertDifficulty(String category) {

        int value = 0;

        switch (category) {

            case "hiragana":
                value = 1;
                break;
            case "katakana":
                value = 2;
                break;
            case "jlptn5":
                value = 3;
                break;
            case "jlptn4":
                value = 4;
                break;
            case "jlptn3":
                value = 5;
                break;
            case "jlptn2":
                value = 6;
                break;
            case "jlptn1":
                value = 7;
                break;
            case "null8":
                value = 8;
                break;
            case "null9":
                value = 9;
                break;

        }

        return value;

    }
    

}
