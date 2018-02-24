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

    
    
    private String character;
    private String difficulty;
    private String readingOne;
    private String readingTwo;
    private String meaning;
    
    public Card() {
        
    }
    
    public Card (String character, String diff, String one, String two, String mean) {
        
        this.character = character;
        this.difficulty = diff;
        this.meaning = mean;
        this.readingOne = one;
        this.readingTwo = two;
       
    }
    
    /**
     * @return the character
     */
    public String getCharacter() {
        return character;
    }

    /**
     * @param character the character to set
     */
    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the readingOne
     */
    public String getReadingOne() {
        return readingOne;
    }

    /**
     * @param readingOne the readingOne to set
     */
    public void setReadingOne(String readingOne) {
        this.readingOne = readingOne;
    }

    /**
     * @return the readingTwo
     */
    public String getReadingTwo() {
        return readingTwo;
    }

    /**
     * @param readingTwo the readingTwo to set
     */
    public void setReadingTwo(String readingTwo) {
        this.readingTwo = readingTwo;
    }

    /**
     * @return the meaning
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * @param meaning the meaning to set
     */
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    
    
}
