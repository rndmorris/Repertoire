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
    private HashMap<String, HashMap> Unmastered = new HashMap<>();
    private HashMap<String, Card> Mastered = new HashMap<>();
    private HashMap<String, ArrayList> readOnlyDecks = new HashMap<>();
    
    private int defaultDeckSize = 3;
    private HashMap<String, Card> temp;
    
    private String activeDeck;
    
    public String id;
    public String username;
    private ArrayList unmastCount = new ArrayList();
    private ArrayList mastCount = new ArrayList();
    
    String randomKey;
    Random random = new Random();
    List<String> keys = new ArrayList<>();
    
    public User() {
        
    }
    
    public User(String username, String id) {
        this.id = id;
        this.username = username;
        setFileName();
        System.out.println(userFileName);
        
        
    }
    
    public User(String name, String username, HashMap<String, HashMap> set) {
        this.id = name;
        this.username = username;
        this.Unmastered = set;
    }
    
    
    
    public int getDefaultDeckSize() {
        return this.defaultDeckSize;
    }
    
    public String getActiveDeck() {
        return activeDeck;
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
        
                System.out.println(Unmastered.get(Integer.toString(0)).size());
              randomKey = (String) Unmastered.get(Integer.toString(0)).keySet().toArray()
               [new Random().nextInt(Unmastered.get(Integer.toString(0)).keySet().toArray().length)]; //Doesn't Work right
       return randomKey; 
        
    }
          
    /**
     * @param id
     * @return the Unmastered
     */
    public HashMap<String, HashMap> getUnmastered() {
        return Unmastered;
    }
    
    public HashMap<String, ArrayList> getReadOnlyDecks() {
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
    public HashMap<String, Card> getMastered() {
        return Mastered;
    }

    /**
     * @param Mastered the Mastered to set
     */
    public void setMastered(HashMap<String, Card> Mastered) {
        this.Mastered = Mastered;
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
        return (int) mastCount.get(index);
    }
    
    public void setMastCount(int index, int count) {
        this.mastCount.set(index, count);
    }

    /**
     * @param mastCount the mastCount to set
     */
    public void addMastCount(int mastCount) {
        this.mastCount.add(mastCount);
    }
}
