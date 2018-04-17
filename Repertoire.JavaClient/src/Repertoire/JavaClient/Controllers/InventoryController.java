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
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class InventoryController implements Initializable, ControlledScreen {

    ScreensController myController;
    static int colCount = 0;
    static int rowCount = 0;
    private Insets pad = new Insets(50, 0, 0, 0);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        update();
        // Must be run at switching of screens vvvvvvvvvv
        // Draw All Mastered Cards

    }

    public void update() {
        colCount = 0;
        rowCount = 0;
        for (int i = 0; i < Program.user.getMastCount(0); i++) {
            Group temp;
            temp = Program.user.getMastered().get(Integer.toString(i)).getCard();

            gridPane.add(temp, colCount % 3, rowCount);
            System.out.println(Program.user.getMastCount(0));

            colCount++;
            if (colCount % 3 == 0) {
                rowCount++;
            }
            if (Program.user.getMastCount(0) > 3) {
                gridPane.setPadding(pad);
            }

        }

    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private GridPane gridPane;

    @FXML
    void mainMenuItem(ActionEvent event) {
        myController.setScreen("Main");
    }

    @FXML
    void gameMenuItem(ActionEvent event) {
        myController.setScreen("Game");
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
    void testButtonOnAction(ActionEvent event) {

        for (int i = 0; i < Program.user.getMastCount(0); i++) {
            Group temp;
            temp = Program.user.getMastered().get(Integer.toString(i)).getCard();

            getGridPane().add(temp, colCount % 3, rowCount);

            colCount++;
            if (colCount % 3 == 0) {
                rowCount++;
            }

        }

    }

    /**
     * @return the gridPane
     */
    public GridPane getGridPane() {
        return gridPane;
    }

    /**
     * @param gridPane the gridPane to set
     */
    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

}
