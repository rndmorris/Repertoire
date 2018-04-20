/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.json.simple.JSONObject;

/**
 *
 * @author Tucker
 */
public class User implements Serializable {
    /*
    private List<String> filesDone, filesToDo;
    private String currentlyProcessingFile;
    private ProcessState currentProcessState;
    */
   //File to serialize object to
    private String userFileName;/* = "applicationState.ser"; */
    /*
    enum ProcessState {
        READ_DONE,
        PROCESSING_STARTED,
        PROCESSING_ENDED,
        ANOTHER_STATE;
        
    }
    */
    
    //private ArrayList<Dictionary> libraries = new ArrayList<>();
    private HashMap<String, HashMap> sets = new HashMap<>();
    private HashMap<String, HashMap<String, Card>> Unmastered = new HashMap<>();
    private HashMap<String, HashMap<String, Card>> Mastered = new HashMap<>();
    private HashMap<String, ArrayList<Card>> readOnlyDecks = new HashMap<>();
    
    private int defaultDeckSize = 10;
    private HashMap<String, Card> temp;
    
    private String activeDeck;
    private Card firstMastered;
    
    public String id;
    private String username;
    private ArrayList unmastCount = new ArrayList();
    private ArrayList<String> mastCount = new ArrayList();
    
    String randomKey;
    Random random = new Random();
    List<String> keys = new ArrayList<>();
    
    public User() {
        
    }
    
    public User(String username, String id) {
        this.id = id;
        this.username = username;
        this.activeDeck = "0";
        setFileName();
        System.out.println(userFileName);
        
        
    }
    
    public User(String name, String username, HashMap<String, HashMap<String, Card>> set) {
        this.id = name;
        this.username = username;
        this.Unmastered = set;
    }
    
    
    
    public int getDefaultDeckSize() {
        return this.defaultDeckSize;
    }
    
    public String getActiveDeckString() {
        return activeDeck;
    }
    
    public int getActiveDeckInt() {
        return Integer.parseInt(activeDeck);
    }
    
    public void setActiveDeck(int i) {
        this.activeDeck = Integer.toString(i);
    }
    

    public void setFileName() {
        userFileName = username + ".ser";
    }
    
    public String getFileName() {
        return userFileName;
    }
    
    public String getUsername() {
        return username;
    }
   
    
    
    public void initNewLib(HashMap<String, Card> set) {
        int num = 0;
        int deckNum = 0;
        for (int i = 0; i < set.size() ; i++) {
        if (num == defaultDeckSize - 1) {
            temp = new HashMap<>();
            Program.user.putUnmastered(Integer.toString(deckNum), temp);
            deckNum++;
        }
        Program.user.getUnmastered().get(Integer.toString(deckNum - 1)).put(set.get(Integer.toString(i)).getCharacter(),set.get(Integer.toString((i))));
        
        }
    }
    
    public String getRandomKey() {
        
                System.out.println(Unmastered.get(Integer.toString(0)).toString());
              randomKey = (String) Unmastered.get(Integer.toString(0)).keySet().toArray()
               [new Random().nextInt(Unmastered.get(Integer.toString(0)).keySet().toArray().length)]; //Doesn't Work right
       return randomKey; 
        
    }
          
    /**
     * @param id
     * @return the Unmastered
     */
    public HashMap<String, HashMap<String, Card>> getUnmastered() {
        return Unmastered;
    }
    
    public HashMap<String, ArrayList<Card>> getReadOnlyDecks() {
        return readOnlyDecks;
    }

    /**
     * @param id
     * @param deck the deck to set
     */
    public void putUnmastered(String id,HashMap<String, Card> deck) {
        this.Unmastered.put(id, deck);
    }
    
    public void putReadOnlyDecks(String id, ArrayList<Card> deck) {
        this.readOnlyDecks.put(id, deck);
    }

    /**
     * @return the Mastered
     */
    public HashMap<String, HashMap<String, Card>> getMastered() {
        return Mastered;
    }

    /**
     * @param id
     * @param deck
     */
    public void putMastered(String id, HashMap<String, Card> deck) {
        this.Mastered.put(id, deck);
    }
    
    

    /**
     * @return the unmastCount
     */
    public int getUnmastCount(int index) {
        return (int) unmastCount.get(index);
    }
    
    public void setUnmastCount(int index, int count) {
        this.unmastCount.set(index, count);
    }

    /**
     * @param unmastCount the unmastCount to set
     */
    public void addUnmastCount(int unmastCount) {
        this.unmastCount.add(unmastCount);
    }

    /**
     * @param index
     * @return the mastCount
     */
    public int getMastCount(int index) {
        return  Integer.parseInt(mastCount.get(index));
    }
    
    public void setMastCount(int index, int count) {
        String s = Integer.toString(count);
        this.mastCount.set(index, s);
        System.out.println(mastCount.get(index));
    }

    /**
     * @param mastCount the mastCount to set
     */
    public void addMastCount(int mastCount) {
        this.mastCount.add(Integer.toString(mastCount));
    }
    
    public void setFirstMastered(Card card) {
        this.firstMastered = card;
    }
    
    public Card getFirstMastered() {
        return firstMastered;
    }
}
