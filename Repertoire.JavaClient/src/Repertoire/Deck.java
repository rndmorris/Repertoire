/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Tucker
 */
public class Deck implements Serializable{
    
    private HashMap<String, Card> unmastered = new HashMap<>();
    private HashMap<String, Card> mastered = new HashMap<>();
    private ArrayList<Card> readOnly = new ArrayList<>();
    
    private ArrayList unmastCount = new ArrayList();
    private ArrayList<String> mastCount = new ArrayList();
    
    
    
    public Deck() {
        
    }
    
    public void loadCard(Card card) {
        
        //Program.user.getUnmastered().get(Integer.toString(deckNum - 1)).put(set.get(Integer.toString(i)).getCharacter(),set.get(Integer.toString((i))));
        putUnmastered(card);
        addReadOnly(card);
    }
    
                         /**
     * @return the Mastered
     */
    public HashMap<String, Card> getMastered() {
        return mastered;
    }
    
        /**
     * @param id
     * @param deck
     */
    public void putMastered(Card c) {
       // this.mastered.put(id, deck);
    }
    
    public HashMap<String, Card> getUnmastered() {
        return unmastered;
    }
    
        /**
     * @param id
     * @param deck the deck to set
     */
    public void putUnmastered(Card c) {
        this.unmastered.put(c.getCharacter(),c);
    }
    
    public ArrayList<Card> getReadOnlyDecks() {
        System.out.println("testy");
        return this.readOnly;
    }
    
        public void addReadOnly(Card c) {
        this.readOnly.add(c);
    }
        
            public int getMastCount(/*int index*/) {
     //   return  Integer.parseInt(mastCount.get(index))
     System.out.println("MastCount in getMastCount: " + mastered.size());
     return mastered.size();
      
    }
    
    public void setMastCount(int index, int count) {
        String s = Integer.toString(count);
        this.mastCount.set(index, s);
        System.out.println(mastCount.get(index));
    }
    
        /**
     * @param index
     * @return the unmastCount
     */
    public int getUnmastCount(int index) {
        return (int) unmastCount.get(index);
    }
    
    public void setUnmastCount(int index, int count) {
        this.unmastCount.set(index, count);
    }
    


}