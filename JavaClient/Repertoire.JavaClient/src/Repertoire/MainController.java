/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import com.sun.glass.ui.Application;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class MainController implements Initializable, ControlledScreen {

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
    
    private Button fullButton;
    
    @FXML
    void exitClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void libraryClicked(ActionEvent event) {
        myController.setScreen(Program.screen6ID);
    }

    @FXML
    void settingsClicked(ActionEvent event) {
        myController.setScreen(Program.screen7ID);
    }

    @FXML
    void masterClicked(ActionEvent event) {
        myController.setScreen(Program.screen2ID);
    }

    @FXML
    void deckClicked(ActionEvent event) {
        myController.setScreen(Program.screen3ID);
    }

    @FXML
    void profileClicked(ActionEvent event) {
        myController.setScreen(Program.screen4ID);
    }

    @FXML
    void studyClicked(ActionEvent event) {
        myController.setScreen(Program.screen5ID);
    }
    
    //note to remove redundant menu buttons below later
    
        @FXML
    void playMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen2ID);
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
    
    
   
    // set to full screen
    @FXML
    void fullscreenClicked(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        
        if (!stage.isFullScreen())
        {
            stage.setFullScreen(true);
            fullButton.setVisible(false);
        }
        }
    
    // ****Add listener to re-add fullScreen Button upon hitting escape****
        
    }

     
    

