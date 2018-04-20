/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;


import Repertoire.Program;
import Repertoire.Shared.Hashing;
import Repertoire.User;
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

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class RegisterController implements Initializable, ControlledScreen {

    
    ScreensController myController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
       @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField username;
    
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
     @FXML
    void submitClicked(ActionEvent event) throws NoSuchAlgorithmException {
        if(checkUsername() && checkPassword()) {
            String nameOfUser = username.getText();
            String id = Integer.toString(Program.accounts.getSize());
            Program.user = new User(nameOfUser, id);
            Program.accounts.addUser(nameOfUser, Hashing.Sha256ToBase64(password.getText()), id);
            
                
        myController.setScreen("Main");
        }
        
    }
    
    public boolean checkUsername() {
        boolean result = true;
        String[] temp = Program.accounts.usernames;
        try {
        for (int i = 0; i < 5; i++) {
            if(username.getText().equals(temp[i])){
                errorLabel.setText("Username taken");
                result = false;
            }
        }
        } catch (NullPointerException ex) {
            System.out.println("First Time User - RegisterContoller");
        }
        return result;
    }
    
    public boolean checkPassword() throws NoSuchAlgorithmException {
        boolean result = false;
        String passEncoded = Hashing.Sha256ToBase64(password.getText());
        String cPassEncoded = Hashing.Sha256ToBase64(confirmPassword.getText());
        
        if(passEncoded.equals(cPassEncoded))
        {
            result = true;
        }
        else errorLabel.setText("Passwords do not match");
        return result;
    }
    
}
