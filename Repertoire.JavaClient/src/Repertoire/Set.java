/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Tucker
 */
public class Set implements Serializable{
    
    private HashMap<String, Deck> decks = new HashMap<>();
    
    private String activeDeck = "0";
    private Deck deck;
    
    public Set() {
        
    }
    
    public void initSet(HashMap<String, Card> set) {
        int deckNum = 0;
        for (int i = 0; i < set.size() ; i++) {
        if (i % (Program.user.getDefaultDeckSize()) == 0) {
            deck = new Deck();
            decks.put(Integer.toString(deckNum), deck);
            System.out.println(deckNum);
            deckNum++;
            System.out.println("Modulo equals zero");
    }
        //create corresponding methods in deck to add new cards
        
        deck.loadCard(set.get(Integer.toString(i)));
        }
    }
    
    public HashMap<String, Deck> getDecks() {
        return this.decks;
    }
    
    public Deck getActiveDeck() {
        Deck temp = this.decks.get(activeDeck);
       
        return temp;
        
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
                

}
