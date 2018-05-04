/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;

import Repertoire.Card;
import Repertoire.Program;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class GameController implements Initializable, ControlledScreen {

    ScreensController myController;
    private ArrayList<Sphere> diffValues = new ArrayList<>();
    private Card temp;
    private Group main = new Group();
    private String reading1 = "Onyomi";
    private String reading2 = "Kunyomi";
    private String definition = "Definition";
    private int masterCount;
    private String randomKey;
    private int variableIndex;
    public static ArrayList<Card> deck = new ArrayList<>();
    private int deckSize;
    private ArrayList<Button> btns = new ArrayList<>();
    private Stack<Card> cardsToGuess = new Stack<>();
    private int correct;
    private MediaPlayer mediaPlayer;
    private String variableToTest;
    private boolean cheat;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    //*************    deck = Program.user.getReadOnlyDecks().get(Program.user.getActiveDeckString());
    try {
       if(!Program.newUser) deck = Program.user.getSet().getActiveDeck().getReadOnlyDecks();
    }catch (NullPointerException ex) {
        System.out.println(ex.getMessage());
    }
        cheat = false;
        
        diffValues.add(diffSphere1);
        diffValues.add(diffSphere2);
        diffValues.add(diffSphere3);
        diffValues.add(diffSphere4);
        diffValues.add(diffSphere5);
        diffValues.add(diffSphere6);
        diffValues.add(diffSphere7);
        diffValues.add(diffSphere8);
        diffValues.add(diffSphere9);

        meaningLabel.setText(definition);
        readingOneLabel.setText(reading1);
        readingTwoLabel.setText(reading2);
        
       btns.add(btn10);
       btns.add(btn11);
       btns.add(btn20);
       btns.add(btn21);
       btns.add(btn30);
       btns.add(btn31);
       
       btn10.setVisible(false);
       btn11.setVisible(false);
       btn20.setVisible(false);
       btn21.setVisible(false);
       btn30.setVisible(false);
       btn31.setVisible(false);
       
       URL cardClick = getClass().getResource("/assets/audio/240776__f4ngy__card-flip.wav");
       Media cardFlip = null;
        try {
            cardFlip = new Media(cardClick.toURI().toString());
        } catch (URISyntaxException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
       mediaPlayer = new MediaPlayer(cardFlip);
       
      
        

    }
    
    @FXML
    private ToggleButton bCheat;
    
    @FXML
    private Button btn10;
    
    @FXML
    private Button btn11;
    
    @FXML
    private Button btn20;
    
    @FXML
    private Button btn21;
    
    @FXML
    private Button btn30;
    
    @FXML
    private Button btn31;

    @FXML
    private Sphere diffSphere1;

    @FXML
    private Sphere diffSphere2;

    @FXML
    private Sphere diffSphere3;

    @FXML
    private Sphere diffSphere4;

    @FXML
    private Sphere diffSphere5;

    @FXML
    private Sphere diffSphere6;

    @FXML
    private Sphere diffSphere7;

    @FXML
    private Sphere diffSphere8;

    @FXML
    private Sphere diffSphere9;

    @FXML
    private Rectangle xp0;

    @FXML
    private Rectangle xp1;

    @FXML
    private Rectangle xp2;

    @FXML
    private Rectangle xp3;

    @FXML
    private Rectangle xp4;

    @FXML
    private Rectangle xp5;

    @FXML
    private Label readingTwo;

    @FXML
    private Button character;

    @FXML
    private Button mainDisplay;

    @FXML
    private Label meaningLabel;

    @FXML
    private Label meaning;

    @FXML
    private Label readingTwoLabel;

    @FXML
    private Label readingOneLabel;

    @FXML
    private Label readingOne;

    @FXML
    private Label errorLabel;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    void onCheat(ActionEvent event) {
        if(cheat) {
            cheat = false;
            bCheat.setSelected(false);
        } else {
            cheat = true;
            bCheat.setSelected(true);
        }
        
    }

    @FXML
    void mainMenuItem(ActionEvent event) {
        myController.setScreen("Main");
    }

    @FXML
    void deckMenuItem(ActionEvent event) {
        myController.setScreen("Inventory");
    }

    @FXML
    void profileMenuItem(ActionEvent event) {
        myController.setScreen("Profile");
    }

    @FXML
    void studyMenuItem(ActionEvent event) {
        myController.setScreen("Study");
    }

    @FXML
    void libraryMenuItem(ActionEvent event) {
        myController.setScreen("Library");
    }

    @FXML
    void settingsMenuItem(ActionEvent event) {
        myController.setScreen("Settings");
    }

    @FXML
    void mastDeckClicked(ActionEvent event) {

        //Refresh Screen
        myController.refreshScreen("Inventory", myController.getScreenMap().get("Inventory"));
        /*
        myController.unloadScreen("Inventory");
        myController.loadScreen("Inventory", Program.screen3File);
        */
        //Set Screen
        myController.setScreen("Inventory");
        
    }

    @FXML
    void unmastDeckClicked(ActionEvent event) {
        
        
        
        Random rand = new Random();
        variableIndex = rand.nextInt(4);
        System.out.println("variableIndex at top: ------------- " + variableIndex);
        correct = rand.nextInt(6);
        

        mediaPlayer.play(); 
        mediaPlayer.seek(Duration.ZERO);
                
                    
            // Set and Display Card
            try {
            randomKey = Program.user.getRandomKey();
            System.out.println(randomKey);
            } catch (NullPointerException ex) {
                errorLabel.setText("Must download a Deck Collection in the Library before playing!");
                errorLabel.setVisible(true);
                return;
            }
          //  temp = (Card)Program.user.getUnmastered().get(Program.user.getActiveDeckString()).get(randomKey);          
            temp = (Card)Program.user.getSet().getActiveDeck().getUnmastered().get(randomKey);
            character.setText(temp.getCharacter());
            readingOne.setText(temp.getReadingOne());
            readingTwo.setText(temp.getReadingTwo());
            meaning.setText(temp.getMeaning());

            errorLabel.setVisible(false);

            mainDisplay.setVisible(true);
            character.setVisible(true);
            readingOneLabel.setVisible(true);
            readingOne.setVisible(true);
            readingTwoLabel.setVisible(true);
            readingTwo.setVisible(true);
            meaningLabel.setVisible(true);
            meaning.setVisible(true);

            for (int i = 0; i < temp.getDifficulty(); i++) {
                diffValues.get(i).setVisible(true);
            }

            displayMastery(temp.getMasteryLevel());
            
            //set and display possible answers
            deck.remove(temp);
            btns.get(correct).setText(variableToTest(temp));
            btns.get(correct).setVisible(true);
            
            if(!cheat)hideAnswer(variableToTest);
            
            for(int i = 0; i < deck.size(); i++) {
                System.out.println(i + "    variable: " + variableToTest(deck.get(i)));
            }
            System.out.println();
            
            
            for (int i = 0; i < 6; i++) {
                if (i == correct) continue;
                System.out.println(deck.size());
                Card tempCard;
                if (deck.size() > 6){
                tempCard = deck.remove(rand.nextInt(deck.size()));
                } else {
                tempCard = deck.get(rand.nextInt(deck.size()));  
                }
                cardsToGuess.push(tempCard);
                btns.get(i).setText(variableToTest(tempCard));
                btns.get(i).setVisible(true);
            }
            
            if (deck.size() > 6)deck.addAll(cardsToGuess);
            cardsToGuess.clear();
            deck.add(temp);
            


    }
    
    public String variableToTest(Card card) {
        String testVariable = null;
        
        switch (variableIndex) {
            
            case 0 : 
                testVariable = card.getCharacter();
                variableToTest = "character";
                break;
            case 1 :
                testVariable = card.getReadingOne();
                variableToTest = "readingOne";
                break;
            case 2 :
                testVariable = card.getReadingTwo();
                variableToTest = "readingTwo";
                break;
            case 3 :
                testVariable = card.getMeaning();
                variableToTest = "meaning";
                break;
                
    
    }
        return testVariable;
    }
    
    public void answerCorrect() {
        int cardKey;
        try {
            temp.setMasteryLevel((masterCount = temp.getMasteryLevel() + 1));
            if (masterCount == 5) {
               //***************** if (Program.user.getMastered().get(Program.user.getActiveDeckString()).isEmpty()) Program.user.setFirstMastered(temp);
                if (Program.user.getSet().getActiveDeck().getMastered().isEmpty()) {
                    Program.user.setFirstMastered(temp);
                    cardKey = 0;
                    System.out.println("00000000000000000000000");
                } else {
                    System.out.println("MastCount in Game: " +Program.user.getSet().getActiveDeck().getMastCount());
                    cardKey = Program.user.getSet().getActiveDeck().getMastCount();
                }
                
                
                //****************Card c = Program.user.getUnmastered().get(Program.user.getActiveDeckString()).remove(randomKey);
                Card c = Program.user.getSet().getActiveDeck().getUnmastered().remove(randomKey);
                System.out.println(c.toString());
                
              //****MAY BE A PROBLEM*********  cardKey = Program.user.getMastCount(Program.user.getActiveDeckInt());
                //cardKey = Program.user.getSet().getActiveDeck().getMastCount();
                //System.out.println(cardKey);
                
               //********* Program.user.getMastered().get(Program.user.getActiveDeckString()).put(Integer.toString(cardKey), temp);
                Program.user.getSet().getActiveDeck().getMastered().put(Integer.toString(cardKey), temp);
                System.out.println(Program.user.getSet().getActiveDeck().getMastCount());
             //***********   Program.user.setMastCount(Program.user.getActiveDeckInt(), cardKey + 1);
              //  Program.user.setMastCount(Program.user.getActiveDeckInt(), cardKey + 1);
              
              
              //System.out.println(" Cards Owned should be- " +Program.user.getMastCount(Program.user.getActiveDeckInt()));

                errorLabel.setText("Card Mastered!");
                errorLabel.setVisible(true);
                
                

            } else {

                errorLabel.setText("Correct!");
                errorLabel.setVisible(true);

            }

            hideCard();

            for (int i = 0; i < temp.getDifficulty(); i++) {
                diffValues.get(i).setVisible(false);
            }

            temp = null;

        } catch (NullPointerException e) {
            errorLabel.setText("Please draw a card");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            errorLabel.setText("Error... See terminal for more info");
            System.out.println(e.getMessage());
        }
        
    }
    
    public void answerIncorrect() {
        
        try {

            temp.setMasteryLevel(0);

            hideCard();

            for (int i = 0; i < temp.getDifficulty(); i++) {
                diffValues.get(i).setVisible(false);
            }

            errorLabel.setText("Incorrect!");
            errorLabel.setVisible(true);

            temp = null;

        } catch (NullPointerException e) {
            errorLabel.setText("Please draw a card");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            errorLabel.setText("Error... See terminal for more info");
            System.out.println(e.getMessage());
        }
        
    }
    
    public void checkAnswer(int guess) {
        //boolean result = false;
       // if (guess == correct)result = true;
        if (btns.get(guess).getText().equals(btns.get(correct).getText())) {
            //result = true;
            answerCorrect();
        }else {
            answerIncorrect();
        }
        
       
    }
    
    public void hideAnswer(String variable) {
        
        switch (variable) {
            
            case "character" :
                character.setText("");
                break;
            case "readingOne" :
                readingOne.setText("");
                break;
            case "readingTwo" :
                readingTwo.setText("");
                break;
            case "meaning" :
                meaning.setText("");
                break;
        }
    }

    
        @FXML
    void onBack(ActionEvent event) {
           myController.setScreen("Main");
    }
    
      @FXML
    void guess10OnAction(ActionEvent event) {
        checkAnswer(0);

    }
    
      @FXML
    void guess11OnAction(ActionEvent event) {
        checkAnswer(1);
    }
    
      @FXML
    void guess20OnAction(ActionEvent event) {
        checkAnswer(2);
    }
    
      @FXML
    void guess21OnAction(ActionEvent event) {
        checkAnswer(3);
    }
    
      @FXML
    void guess30OnAction(ActionEvent event) {
        checkAnswer(4);
    }
    
      @FXML
    void guess31OnAction(ActionEvent event) {
        checkAnswer(5);
    }

    public void hideCard() {
        mainDisplay.setVisible(false);
        character.setVisible(false);
        readingOneLabel.setVisible(false);
        readingOne.setVisible(false);
        readingTwoLabel.setVisible(false);
        readingTwo.setVisible(false);
        meaningLabel.setVisible(false);
        meaning.setVisible(false);

        xp0.setVisible(false);
        xp1.setVisible(false);
        xp2.setVisible(false);
        xp3.setVisible(false);
        xp4.setVisible(false);
        xp5.setVisible(false);
    }

    // Display level of master behind card for this card object
    // ..may change to if-else within for as it is getting lengthy
    public void displayMastery(int xp) {

        xp0.setVisible(true);
        xp1.setVisible(true);
        xp2.setVisible(true);
        xp3.setVisible(true);
        xp4.setVisible(true);
        xp5.setVisible(true);

        switch (xp) {

            case 0:
                xp1.setWidth(260);
                xp1.setLayoutX(270);
                xp1.setFill(Color.BLACK);
                xp1.setOpacity(.4);

                xp2.setWidth(260);
                xp2.setLayoutX(270);
                xp2.setFill(Color.BLACK);
                xp2.setOpacity(.4);

                xp3.setWidth(260);
                xp3.setLayoutX(270);
                xp3.setFill(Color.BLACK);
                xp3.setOpacity(.4);

                xp4.setWidth(260);
                xp4.setLayoutX(270);
                xp4.setFill(Color.BLACK);
                xp4.setOpacity(.4);

                xp5.setWidth(260);
                xp5.setLayoutX(270);
                xp5.setFill(Color.BLACK);
                xp5.setOpacity(.4);
                break;

            case 1:
                xp1.setWidth(270);
                xp1.setLayoutX(265);
                xp1.setFill(Color.AQUA);
                xp1.setOpacity(1);

                xp2.setWidth(260);
                xp2.setLayoutX(270);
                xp2.setFill(Color.BLACK);
                xp2.setOpacity(.4);

                xp3.setWidth(260);
                xp3.setLayoutX(270);
                xp3.setFill(Color.BLACK);
                xp3.setOpacity(.4);

                xp4.setWidth(260);
                xp4.setLayoutX(270);
                xp4.setFill(Color.BLACK);
                xp4.setOpacity(.4);

                xp5.setWidth(260);
                xp5.setLayoutX(270);
                xp5.setFill(Color.BLACK);
                xp5.setOpacity(.4);
                break;

            case 2:
                xp1.setWidth(270);
                xp1.setLayoutX(265);
                xp1.setFill(Color.AQUA);
                xp1.setOpacity(1);

                xp2.setWidth(270);
                xp2.setLayoutX(265);
                xp2.setFill(Color.AQUA);
                xp2.setOpacity(1);

                xp3.setWidth(260);
                xp3.setLayoutX(270);
                xp3.setFill(Color.BLACK);
                xp3.setOpacity(.4);

                xp4.setWidth(260);
                xp4.setLayoutX(270);
                xp4.setFill(Color.BLACK);
                xp4.setOpacity(.4);

                xp5.setWidth(260);
                xp5.setLayoutX(270);
                xp5.setFill(Color.BLACK);
                xp5.setOpacity(.4);
                break;

            case 3:
                xp1.setWidth(270);
                xp1.setLayoutX(265);
                xp1.setFill(Color.AQUA);
                xp1.setOpacity(1);

                xp2.setWidth(270);
                xp2.setLayoutX(265);
                xp2.setFill(Color.AQUA);
                xp2.setOpacity(1);

                xp3.setWidth(270);
                xp3.setLayoutX(265);
                xp3.setFill(Color.AQUA);
                xp3.setOpacity(1);

                xp4.setWidth(260);
                xp4.setLayoutX(270);
                xp4.setFill(Color.BLACK);
                xp4.setOpacity(.4);

                xp5.setWidth(260);
                xp5.setLayoutX(270);
                xp5.setFill(Color.BLACK);
                xp5.setOpacity(.4);
                break;

            case 4:
                xp1.setWidth(270);
                xp1.setLayoutX(265);
                xp1.setFill(Color.AQUA);
                xp1.setOpacity(1);

                xp2.setWidth(270);
                xp2.setLayoutX(265);
                xp2.setFill(Color.AQUA);
                xp2.setOpacity(1);

                xp3.setWidth(270);
                xp3.setLayoutX(265);
                xp3.setFill(Color.AQUA);
                xp3.setOpacity(1);

                xp4.setWidth(270);
                xp4.setLayoutX(265);
                xp4.setFill(Color.AQUA);
                xp4.setOpacity(1);

                xp5.setWidth(260);
                xp5.setLayoutX(270);
                xp5.setFill(Color.BLACK);
                xp5.setOpacity(.4);
                break;

            case 5:
                xp1.setWidth(270);
                xp1.setLayoutX(265);
                xp1.setFill(Color.AQUA);
                xp1.setOpacity(1);

                xp2.setWidth(270);
                xp2.setLayoutX(265);
                xp2.setFill(Color.AQUA);
                xp2.setOpacity(1);

                xp3.setWidth(270);
                xp3.setLayoutX(265);
                xp3.setFill(Color.AQUA);
                xp3.setOpacity(1);

                xp4.setWidth(270);
                xp4.setLayoutX(265);
                xp4.setFill(Color.AQUA);
                xp4.setOpacity(1);

                xp5.setWidth(270);
                xp5.setLayoutX(265);
                xp5.setFill(Color.AQUA);
                xp5.setOpacity(1);
                break;

        }
    }

}
