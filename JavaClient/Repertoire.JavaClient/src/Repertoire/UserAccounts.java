/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;

import java.io.Serializable;

/**
 *
 * @author Tucker
 */
public class UserAccounts implements Serializable{
    
    String usernames[] = new String[6];
    int size = 0;
    
    public void UserAccounts(){
        
    }
    
    public void setCurrentUser(String index) {
        usernames[5] = index;
    }
    
    public String[] getUsers() {
        return usernames;
    }
    
    public void addUser(String username, String pass, String accountID) {
        String userID = username + "," + pass + "," + accountID;
        usernames[size] = userID;
        size++;
    }
    
    public int getSize() {
        return size;
    }
    
    
}
