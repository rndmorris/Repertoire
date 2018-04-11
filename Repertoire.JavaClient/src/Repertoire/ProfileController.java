/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

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
public class ProfileController implements Initializable, ControlledScreen {

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
        myController.setScreen(Program.screen1ID);
    }

    @FXML
    void gameMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen2ID);
    }

    @FXML
    void deckMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen3ID);
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
    
}
