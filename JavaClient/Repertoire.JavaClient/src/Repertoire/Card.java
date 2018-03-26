/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Tucker
 */
public class Card implements Serializable{

    
    
    private String character;
    private String readingOne;
    private String readingTwo;
    private String meaning;
    private int difficulty;
    private int masteryLevel = 0;
    
    public Card() {
        
    }
    
    public Card (String character, int diff, String one, String two, String mean) {
        
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
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(int difficulty) {
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
    
    public Group getCard(/*Pane pane*/) {
        
        Button b = new Button();
        
        b.setPrefSize(160,200);
        
        Button c = new Button (character);
        
        c.setId("c");
        c.setStyle("-fx-arc-height: 100;");
        c.setStyle("-fx-arc-width: 100;");
        c.setStyle("-fx-background-color: blue;");
        
        
        
        c.setLayoutX(b.getLayoutX() + 15);
        c.setLayoutY(b.getLayoutY() + 10);
        c.setPrefSize(130,80);
        
        
        c.setStyle("-fx-font: 40 arial;");
        
        Group g = new Group();
        g.getChildren().addAll(b,c);
        ArrayList<Circle> diffValues = new ArrayList();
        
        
        for (int i = 1; i<= this.difficulty; i++) {
        Circle circ = new Circle();
        circ.setRadius(5);
        circ.setCenterY(c.getLayoutY() + 90);
        circ.setCenterX((c.getLayoutX() + 15 * i)-10);
        diffValues.add(circ);
        g.getChildren().add(circ);
        }
        
        Label readLabOne = new Label();
        readLabOne.setText("Onyomi");
        readLabOne.setLayoutX(c.getLayoutX());
        readLabOne.setLayoutY(b.getLayoutY() + 110);
        
        
        Label readLabTwo = new Label();
        readLabTwo.setText("Kunyomi");
        readLabTwo.setLayoutX(readLabOne.getLayoutX() + 60);
        readLabTwo.setLayoutY(b.getLayoutY() + 110);
        
        Label one = new Label();
        one.setText(readingOne);
        one.setLayoutX(readLabOne.getLayoutX());
        one.setLayoutY(readLabOne.getLayoutY() + 20);
        one.setMaxWidth(50);
        
        Label two = new Label();
        two.setText(readingTwo);
        two.setLayoutX(readLabTwo.getLayoutX());
        two.setLayoutY(readLabTwo.getLayoutY() + 20);
        two.setMaxWidth(50);                                // Needs to be changed.
        
        Label meanLab = new Label();
        meanLab.setText("Meaning");
        meanLab.setLayoutX(c.getLayoutX());
        meanLab.setLayoutY(two.getLayoutY() + 20);
        
        Label means = new Label();
        means.setText(meaning);
        means.setLayoutX(meanLab.getLayoutX());
        means.setLayoutY(meanLab.getLayoutY() + 20);
        
        g.getChildren().addAll(readLabOne, readLabTwo, one, two, meanLab, means);
        
        
        // Test for transparent button
        
        
        /*
        Button tester = new Button();
        tester.setPrefSize(200, 200);
        tester.setLayoutX(100);
        tester.setLayoutY(100);
        tester.setStyle("-fx-background-color: blue;");
        
        */
        
        
        
        
        
        //g.getChildren().addAll(b, c);
        //pane.getChildren().add(g);
        //pane.getChildren().add(tester);
        return g;
    }

    /**
     * @return the masteryLevel
     */
    public int getMasteryLevel() {
        return masteryLevel;
    }

    /**
     * @param masteryLevel the masteryLevel to set
     */
    public void setMasteryLevel(int masteryLevel) {
        this.masteryLevel = masteryLevel;
    }
    
    
}
