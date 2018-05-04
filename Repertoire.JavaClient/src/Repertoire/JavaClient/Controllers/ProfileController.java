/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;

import Repertoire.Card;
import Repertoire.Program;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class ProfileController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        username.setText(Program.user.getUsername());
        try {
        displayFirstCard();
        //*****************cardsOwned.setText(Integer.toString(Program.user.getMastCount(Program.user.getActiveDeckInt())));
        System.out.println("passed");
        cardsOwned.setText(Integer.toString(Program.user.getSet().getActiveDeck().getMastCount()));
        System.out.println("Cards Owned Number: " +(Program.user.getSet().getActiveDeck().getMastCount()));
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage() + " - NewUser");
            
}}
    
    @FXML
    private GridPane gridCard;
    


    @FXML
    private Label username;
    
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private Label cardsOwned;
    
    @FXML
        void onBack(ActionEvent event) {
           myController.setScreen("Main");
    }
        
    @FXML
    void mainMenuItem(ActionEvent event) {
        myController.setScreen("Main");
    }

    @FXML
    void gameMenuItem(ActionEvent event) {
        myController.setScreen("Game");
    }

    @FXML
    void deckMenuItem(ActionEvent event) {
        myController.setScreen("Inventory");
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
    
    public void displayFirstCard(){
        Card c;
        Group temp;
        c = Program.user.getFirstMastered();
        temp = c.getCard();
        gridCard.add(temp, 0, 0);
    }
    
}
