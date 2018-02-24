/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class StudyController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableView.setItems(Dictionary.data);
     
        
        
        charColumn.setCellValueFactory(
                new PropertyValueFactory<>("character"));
        
        readOneColumn.setCellValueFactory(
                new PropertyValueFactory<>("readingOne"));
        
        readTwoColumn.setCellValueFactory(
                new PropertyValueFactory<>("readingTwo"));
        
        meanColumn.setCellValueFactory(
                new PropertyValueFactory<>("meaning"));
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
    void profileMenuItem(ActionEvent event) {
        myController.setScreen(Program.screen4ID);
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
    private TableColumn<Card, String> readTwoColumn;

    @FXML
    private TableColumn<Card, String> charColumn;

    @FXML
    private TableColumn<Card, String> meanColumn;

    @FXML
    private TableView<Card> tableView;

    @FXML
    private TableColumn<Card, String> readOneColumn;
    
}
