/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;

import Repertoire.Program;
import Repertoire.User;
import static Repertoire.Program.user;
import Repertoire.Shared.Hashing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.lang.SerializationUtils;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class SignInController implements Initializable, ControlledScreen{

    
    ScreensController myController;
    int lastUserIndex ;
    String[] lastUserInfo;
    String usernameToDisplay;
    String accountToLoad;
    private String password;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(!Program.newUser) {
        lastUserIndex = Integer.parseInt(Program.accounts.usernames[5]);
        lastUserInfo = Program.accounts.usernames[lastUserIndex].split(",");
        usernameToDisplay = lastUserInfo[0];
        password = lastUserInfo[1];
        accountToLoad = usernameToDisplay + ".ser";
        lastUser.setText(usernameToDisplay);
        
        }
            
        
        
        
    }    
    
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private PasswordField passField;
    
    @FXML
    private Label lastUser;
    
    
    @FXML
    void createAccountClicked(ActionEvent event) {
        myController.setScreen("Register");
    }

    @FXML
    void signInClicked(ActionEvent event) throws NoSuchAlgorithmException {
        
        if(authenticatePassword()) {
        System.out.println("tester");
        loadAccount(accountToLoad);
        myController.setScreen("Main");
        System.out.println("tester");
        }
        else {
            errorLabel.setText("Incorrect Password");
        }
    }
    
    public void loadAccount(String filename) {
        
        try {
            //Open FileInputStream to the file
            FileInputStream fis = new FileInputStream(filename);
            
            //Deserialize and cast into String
            Program.user =  (User)SerializationUtils.deserialize(fis);
            
           // System.out.println(user.getMastCount(0));
            fis.close();
            
            System.out.println("Successful");
            //System.out.println(user.getMastCount(0));
          
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("First Time User!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public boolean authenticatePassword() throws NoSuchAlgorithmException {
        boolean b = false;
        String passEncoded = Hashing.Sha256ToBase64(passField.getText());
        if (passEncoded.equals(password))b = true;
        return b;
    }
}
