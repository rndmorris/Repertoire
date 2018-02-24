/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

//import java.awt.Label;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class GameController implements Initializable, ControlledScreen {

    ScreensController myController;
    private Card temp;
    private Group main = new Group();
    private String reading1 = "Onyomi";
    private String reading2 = "Kunyomi";
    private String definition = "Definition";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        meaningLabel.setText(definition);
        readingOneLabel.setText(reading1);
        readingTwoLabel.setText(reading2);
        
          
        
    }    
    
    @FXML
    private Label readingTwo;

    @FXML
    private Label character;

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
    private Rectangle mainDisplay;

    @FXML
    private Rectangle mainCard;
    
    
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
    void unmastDeckClicked(ActionEvent event) {
        
      // temp = Program.
       //Program.user.getRandomKey();
       temp = Program.user.getRandomKey();
       character.setText(temp.getCharacter());
       readingOne.setText(temp.getReadingOne());
       readingTwo.setText(temp.getReadingTwo());
       meaning.setText(temp.getMeaning());

    }
    
}
