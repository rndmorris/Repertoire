/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tucker
 */
public class Program extends Application  {
    
    public static String screen1ID = "Main";
    public static String screen1File = "Main.fxml";
    public static String screen2ID = "Game";
    public static String screen2File = "Game.fxml";
    public static String screen3ID = "Inventory";
    public static String screen3File = "Inventory.fxml";
    public static String screen4ID = "Profile";
    public static String screen4File = "Profile.fxml";
    public static String screen5ID = "Study";
    public static String screen5File = "Study.fxml";
    public static String screen6ID = "Library";
    public static String screen6File = "Library.fxml";
    public static String screen7ID = "Settings";
    public static String screen7File = "Settings.fxml";
    
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        
        mainContainer.loadScreen(screen1ID, screen1File);
        mainContainer.loadScreen(screen2ID, screen2File);
        mainContainer.loadScreen(screen3ID, screen3File);
        mainContainer.loadScreen(screen4ID, screen4File);
        mainContainer.loadScreen(screen5ID, screen5File);
        mainContainer.loadScreen(screen6ID, screen6File);
        mainContainer.loadScreen(screen7ID, screen7File);
        
        mainContainer.setScreen(screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
}
