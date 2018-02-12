/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

/**
 *
 * @author Tucker
 */
public class Card {
    
    public String character;
    public String difficulty;
    public String readingOne;
    public String readingTwo;
    public String meaning;
    
    public Card() {
        
    }
    
    public Card (String character, String diff, String one, String two, String mean) {
        
        this.character = character;
        this.difficulty = diff;
        this.meaning = mean;
        this.readingOne = one;
        this.readingTwo = two;
       
    }
}
