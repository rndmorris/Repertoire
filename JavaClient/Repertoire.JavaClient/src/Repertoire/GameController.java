/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;

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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

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
       
       String cardClick = "240776__f4ngy__card-flip.wav";
       Media cardFlip = new Media(new File(cardClick).toURI().toString());
       mediaPlayer = new MediaPlayer(cardFlip);
       
      
        

    }
    
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
    void mainMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen1ID);
    }

    @FXML
    void deckMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen3ID);
    }

    @FXML
    void profileMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen4ID);
    }

    @FXML
    void studyMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen5ID);
    }

    @FXML
    void libraryMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen6ID);
    }

    @FXML
    void settingsMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen7ID);
    }

    @FXML
    void mastDeckClicked(ActionEvent event) {

        //Refresh Screen
        myController.refreshScreen(Program.screen3ID, Program.screen3File);
        /*
        myController.unloadScreen(Program.screen3ID);
        myController.loadScreen(Program.screen3ID, Program.screen3File);
        */
        //Set Screen
        myController.setScreen(Program.screen3ID);
        
    }

    @FXML
    void unmastDeckClicked(ActionEvent event) {
        
        
        
        Random rand = new Random();
        variableIndex = rand.nextInt(4);
        System.out.println("variableIndex at top: ------------- " + variableIndex);
        correct = rand.nextInt(6);
        

        mediaPlayer.play(); 
                
        try {
            
            // Set and Display Card
            randomKey = Program.user.getRandomKey();
            temp = Program.user.getUnmastered().get(randomKey);          

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
            
            for(int i = 0; i < deck.size(); i++) {
                System.out.println(i + "    variable: " + variableToTest(deck.get(i)));
            }
            System.out.println();
            
            
            for (int i = 0; i < 6; i++) {
                if (i == correct) continue;
                Card tempCard = deck.remove(rand.nextInt(deck.size()));
                cardsToGuess.push(tempCard);
                btns.get(i).setText(variableToTest(tempCard));
                btns.get(i).setVisible(true);
            }
            
            deck.addAll(cardsToGuess);
            cardsToGuess.clear();
            deck.add(temp);
            

        } catch (IllegalArgumentException e) {

            errorLabel.setText("Out of Cards!");
            System.out.println(e.getMessage());

        } catch (Exception e) {
            errorLabel.setText("Error... See terminal for more info");
            System.out.println(e.getMessage());
        }
    }
    
    public String variableToTest(Card card) {
        String testVariable = null;
        
        switch (variableIndex) {
            
            case 0 : 
                testVariable = card.getCharacter();
                break;
            case 1 :
                testVariable = card.getReadingOne();
                break;
            case 2 :
                testVariable = card.getReadingTwo();
                break;
            case 3 :
                testVariable = card.getMeaning();
                break;
                
    
    }
        return testVariable;
    }
    
    public void answerCorrect() {
        
        try {
            temp.setMasteryLevel((masterCount = temp.getMasteryLevel() + 1));
            if (masterCount == 5) {
                int cardKey;
                Program.user.getUnmastered().remove(randomKey);
                cardKey = Program.user.getMastCount();
                Program.user.getMastered().put(Integer.toString(cardKey), temp);
                Program.user.setMastCount(cardKey + 1);

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
/*
    @FXML
    void yesOnAction(ActionEvent event) {
        try {
            temp.setMasteryLevel((masterCount = temp.getMasteryLevel() + 1));
            if (masterCount == 5) {
                int cardKey;
                Program.user.getUnmastered().remove(randomKey);
                cardKey = Program.user.getMastCount();
                Program.user.getMastered().put(Integer.toString(cardKey), temp);
                Program.user.setMastCount(cardKey + 1);

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

    @FXML
    void noOnAction(ActionEvent event) {

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
    */
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
                xp1.setFill(Color.DARKGREEN);
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
                xp1.setFill(Color.DARKGREEN);
                xp1.setOpacity(1);

                xp2.setWidth(270);
                xp2.setLayoutX(265);
                xp2.setFill(Color.DARKGREEN);
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
                xp1.setFill(Color.DARKGREEN);
                xp1.setOpacity(1);

                xp2.setWidth(270);
                xp2.setLayoutX(265);
                xp2.setFill(Color.DARKGREEN);
                xp2.setOpacity(1);

                xp3.setWidth(270);
                xp3.setLayoutX(265);
                xp3.setFill(Color.DARKGREEN);
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
                xp1.setFill(Color.DARKGREEN);
                xp1.setOpacity(1);

                xp2.setWidth(270);
                xp2.setLayoutX(265);
                xp2.setFill(Color.DARKGREEN);
                xp2.setOpacity(1);

                xp3.setWidth(270);
                xp3.setLayoutX(265);
                xp3.setFill(Color.DARKGREEN);
                xp3.setOpacity(1);

                xp4.setWidth(270);
                xp4.setLayoutX(265);
                xp4.setFill(Color.DARKGREEN);
                xp4.setOpacity(1);

                xp5.setWidth(260);
                xp5.setLayoutX(270);
                xp5.setFill(Color.BLACK);
                xp5.setOpacity(.4);
                break;

            case 5:
                xp1.setWidth(270);
                xp1.setLayoutX(265);
                xp1.setFill(Color.DARKGREEN);
                xp1.setOpacity(1);

                xp2.setWidth(270);
                xp2.setLayoutX(265);
                xp2.setFill(Color.DARKGREEN);
                xp2.setOpacity(1);

                xp3.setWidth(270);
                xp3.setLayoutX(265);
                xp3.setFill(Color.DARKGREEN);
                xp3.setOpacity(1);

                xp4.setWidth(270);
                xp4.setLayoutX(265);
                xp4.setFill(Color.DARKGREEN);
                xp4.setOpacity(1);

                xp5.setWidth(270);
                xp5.setLayoutX(265);
                xp5.setFill(Color.DARKGREEN);
                xp5.setOpacity(1);
                break;

        }
    }

}
