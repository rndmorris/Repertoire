/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;


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
    
    private String nameOfUser;
    private String id;
    private MessageDigest digest;
    private String passEncoded;
    private String cPassEncoded;
    private String passApproved;
    
    
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
            nameOfUser = username.getText();
            id = Integer.toString(Program.accounts.getSize());
            Program.user = new User(nameOfUser, id);
            Program.accounts.addUser(nameOfUser, passApproved, id);
            
            
        }
            
        myController.setScreen(Program.screen1ID);
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
        digest = MessageDigest.getInstance("SHA-256");
        passEncoded = Base64.getEncoder().encodeToString(digest.digest(password.getText().getBytes(StandardCharsets.UTF_8)));
        cPassEncoded = Base64.getEncoder().encodeToString(digest.digest(confirmPassword.getText().getBytes(StandardCharsets.UTF_8)));
        
        if(passEncoded.equals(cPassEncoded))
        {
        result = true;
        passApproved = passEncoded;
        }
        else errorLabel.setText("Passwords do not match");
        return result;
    }
    
}
