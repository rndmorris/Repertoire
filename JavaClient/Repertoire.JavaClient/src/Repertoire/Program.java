/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.IOException;
import java.util.ResourceBundle.Control;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
//import org.json.JSONArray;
//import org.json.JSONException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Tucker
 */
public class Program extends Application  {
    
    public static User user = new User("Account1", "Tucker");
    
    
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
    
    final int initHeight = 450;
    final int initWidth = 800;
    
    //Test file for JSON
    public String testFile = "src\\Repertoire\\testDictionary.json";
    
    
            
    
    @Override
    public void start(Stage stage) {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.setPrefHeight(initHeight);
        mainContainer.setPrefWidth(initWidth);
        
        
        mainContainer.loadScreen(screen1ID, screen1File);
        mainContainer.loadScreen(screen2ID, screen2File);
        mainContainer.loadScreen(screen3ID, screen3File);
        mainContainer.loadScreen(screen4ID, screen4File);
        mainContainer.loadScreen(screen5ID, screen5File);
        mainContainer.loadScreen(screen6ID, screen6File);
        mainContainer.loadScreen(screen7ID, screen7File);
        
        mainContainer.setScreen(screen1ID);
        
        Pane root = new Pane();     //was GroupLayout
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
       
        Scale scale = new Scale();
        scale.xProperty().bind(root.widthProperty().divide(initWidth));
        scale.yProperty().bind(root.heightProperty().divide(initHeight));
        root.getTransforms().add(scale);
        
        String css = Program.class.getResource("RepTheme.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        // stage.setTitle("Repertoire");
        // stage.getIcons()
        
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        
        // Set listener for .setRoot
        scene.rootProperty().addListener(new ChangeListener<Parent>() {
            @Override
                public void changed(ObservableValue<? extends Parent> arg0, Parent oldValue, Parent newValue){
                    scene.rootProperty().removeListener(this);
                    scene.setRoot(root);
                    ((Region)newValue).setPrefWidth(initWidth);
                    ((Region)newValue).setPrefHeight(initHeight);
                    root.getChildren().clear();
                    root.getChildren().add(newValue);
                    scene.rootProperty().addListener(this);
        }
    });
        
        
      
        //stage.setFullScreen(true);
        
        
        //Testing JSON file for validity
        Dictionary test = new Dictionary();
        try {
        test.isJsonValid(testFile);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(System.getProperty("user.dir"));
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
    
    
    
    
}
