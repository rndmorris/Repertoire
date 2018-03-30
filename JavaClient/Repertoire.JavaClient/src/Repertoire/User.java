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
    
    private List<String> filesDone, filesToDo;
    private String currentlyProcessingFile;
    private ProcessState currentProcessState;
    
   //File to serialize object to
    static String fileName = "applicationState.ser";
    
    enum ProcessState {
        READ_DONE,
        PROCESSING_STARTED,
        PROCESSING_ENDED,
        ANOTHER_STATE;
        
    }
    
    public String getFileName() {
        return fileName;
    }
    
    private ArrayList<Dictionary> libraries = new ArrayList<>();
    
    private HashMap<String, Card> Unmastered = new HashMap<>();
    private HashMap<String, Card> Mastered = new HashMap<>();
    
    public String accountName;
    public String username;
    private int unmastCount;
    private int mastCount = 0;
    
    String randomKey;
    Random random = new Random();
    List<String> keys = new ArrayList<>();
    
    public User() {
        
    }
    
    public User(String name, String username) {
        this.accountName = name;
        this.username = username;
    }
    
    public User(String name, String username, HashMap<String, Card> set) {
        this.accountName = name;
        this.username = username;
        this.Unmastered = set;
    }
    

    
    
    public void initNewLib(HashMap<String, Card> set) {
        this.Unmastered = set;
    }
    
    public String getRandomKey() {
       
              randomKey = (String) Unmastered.keySet().toArray()
               [new Random().nextInt(Unmastered.keySet().toArray().length)]; //Doesn't Work right
       return randomKey; 
        
    }
          
    /**
     * @return the Unmastered
     */
    public HashMap<String, Card> getUnmastered() {
        return Unmastered;
    }

    /**
     * @param Unmastered the Unmastered to set
     */
    public void setUnmastered(HashMap<String, Card> Unmastered) {
        this.Unmastered = Unmastered;
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
    public int getUnmastCount() {
        return unmastCount;
    }

    /**
     * @param unmastCount the unmastCount to set
     */
    public void setUnmastCount(int unmastCount) {
        this.unmastCount = unmastCount;
    }

    /**
     * @return the mastCount
     */
    public int getMastCount() {
        return mastCount;
    }

    /**
     * @param mastCount the mastCount to set
     */
    public void setMastCount(int mastCount) {
        this.mastCount = mastCount;
    }
}
