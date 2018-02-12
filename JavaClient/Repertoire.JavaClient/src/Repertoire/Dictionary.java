/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
    
    
    
    public Dictionary() {
        
    }
    
    public void dataInit(String name) {
        
    /*    
        try {
            
        
        
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    */
    }
    public boolean isJsonValid(String name) {
        
        JSONParser parser = new JSONParser();
        HashMap<String, Card> set = new HashMap<>();
        int count = 0;
    try {
        
        JSONObject object = (JSONObject)parser.parse(new FileReader(name));
        
        JSONArray array = (JSONArray) object.get("kanji");
                
                //(JSONArray) parser.parse(new FileReader(name));
        Iterator it = array.iterator();
        
        while (it.hasNext()) 
        {
            JSONObject card = (JSONObject) it.next();
            
            String difficulty = (String) card.get("category");
            String character = (String) card.get("character");
            String readingOne = (String) card.get("onyomi");
            String readingTwo = (String) card.get("kunyomi");
            String meaning = (String) card.get("meaning");
            
            Card c = new Card(character, difficulty, readingOne, readingTwo, meaning);
            set.put(Integer.toString(count), c);
            
            
            System.out.println(difficulty + " " + character + " " + readingOne + " " +
                    readingTwo + " " + meaning);
            
            count++;
        }
        
        }  catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    catch (ParseException e)
        {
            System.out.println(e.getMessage());
            
            
        }
       
        Program.user.setUnmastered(set);
        
    return true;
    }
}
   



