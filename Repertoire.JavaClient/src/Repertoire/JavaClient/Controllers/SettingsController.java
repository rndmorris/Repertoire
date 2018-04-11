/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;

import Repertoire.Program;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class SettingsController implements Initializable, ControlledScreen {

    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
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
    
}
