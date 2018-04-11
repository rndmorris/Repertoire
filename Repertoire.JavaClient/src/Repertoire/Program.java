/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import Repertoire.JavaClient.Controllers.ScreensController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.apache.commons.lang.SerializationUtils;
//import org.json.JSONArray;
//import org.json.JSONException;

/**
 *
 * @author Tucker
 */
public class Program extends Application  {
    
    public static UserAccounts accounts = new UserAccounts();
    public static boolean newUser = false;
    
    public static User user = new User("Username", "ID");
    
    
    
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
    public static String screen8ID = "SignIn";
    public static String screen8File = "SignIn.fxml";
    public static String screen9ID = "Register";
    public static String screen9File = "Register.fxml";
    
    public static String startScreen = "SignIn";
    
    final int initHeight = 450;
    final int initWidth = 800;
    
    //Test file for JSON
    public static String testFile = "src\\Repertoire\\testDictionary.json";
    
    //Usernames .ser file
    public static String usernamesFile = "usernames.ser";
    
    
            
    
    @Override
    public void start(Stage stage) {
        
        deserialize();
        /*
        try {
            //Open FileInputStream to the file
            FileInputStream fis = new FileInputStream(User.fileName);
            
            //Deserialize and cast into String
            user =  (User)SerializationUtils.deserialize(fis);
            
            System.out.println(user.getMastCount());
            fis.close();
          
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("First Time User!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        
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
        mainContainer.loadScreen(screen8ID, screen8File);
        mainContainer.loadScreen(screen9ID, screen9File);
        
        if(newUser)startScreen = "Register";
        System.out.println(startScreen);
        mainContainer.setScreen(startScreen);
        
        Pane root = new Pane();     //was GroupLayout
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
       
        Scale scale = new Scale();
        scale.xProperty().bind(root.widthProperty().divide(initWidth));
        scale.yProperty().bind(root.heightProperty().divide(initHeight));
        root.getTransforms().add(scale);
        
        String css = Program.class.getResource("RepTheme.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
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
       /* Dictionary test = new Dictionary();
        try {
        test.dataInit(testFile);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(System.getProperty("user.dir"));
        */
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
    
    public void deserialize() {
        
        try {
            //Open FileInputStream to the file
            FileInputStream fis = new FileInputStream(usernamesFile);
            
            //Deserialize and cast into String
            accounts =  (UserAccounts)SerializationUtils.deserialize(fis);
            for (int i = 0; i < 6; i++){
            System.out.println(accounts.usernames[i]);
        }
            fis.close();
            
            
          
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("First Time User!");
            newUser = true;
            accounts.setCurrentUser("0");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
}
