/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;

import Repertoire.Dictionary;
import Repertoire.Program;
import com.sun.glass.ui.Application;
import java.io.FileOutputStream;

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
import org.apache.commons.lang.SerializationUtils;

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
        
        //Save currentUser ,, will have to save at switching between users too
        try {
            
            
            
            String fileName = Program.user.getFileName();
            
            //New file output stream for the file
            FileOutputStream fos = new FileOutputStream(fileName);
            
            //Serialize String
           SerializationUtils.serialize(Program.user, fos);
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //Save usernames
        
        try {
            
            
            
            String fileName = Program.usernamesFile;
            
            //New file output stream for the file
            FileOutputStream fos = new FileOutputStream(fileName);
            
            //Serialize String
           SerializationUtils.serialize(Program.accounts, fos);
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        System.exit(0);
    }

    @FXML
    void libraryClicked(ActionEvent event) {
        myController.setScreen("Library");
    }

    @FXML
    void settingsClicked(ActionEvent event) {
        myController.setScreen("Settings");
    }

    @FXML
    void masterClicked(ActionEvent event) {
        //Refresh Screen
        myController.refreshScreen("Game", myController.getScreenMap().get("Game"));
        
        //Set Screen
        myController.setScreen("Game");
    }

    @FXML
    void deckClicked(ActionEvent event) {
        myController.refreshScreen("Inventory", myController.getScreenMap().get("Inventory"));
        myController.setScreen("Inventory");
    }

    @FXML
    void profileClicked(ActionEvent event) {
        myController.setScreen("Profile");
    }

    @FXML
    void studyClicked(ActionEvent event) {
        myController.setScreen("Study");
    }
    
    //note to remove redundant menu buttons below later
    
        @FXML
    void playMenuItem(ActionEvent event) {
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

    @FXML
    void settingsMenuItem(ActionEvent event) {
        myController.setScreen("Settings");
    }
    
        @FXML
    void onLoadData(ActionEvent event) {
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

     
    

