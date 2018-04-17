/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire;


import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Tucker
 */
public class ScreensController extends StackPane {
    //Holds the screens to be displayed
    
    private HashMap<String, Node> screens = new HashMap<>();
    
    
    public ScreensController() {
        super();
    }
    
    
    
    //Add the screen to the collection
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }
    
    //Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return screens.get(name);
    }
    
    //Loads the fxml file, adds the screen to the screens collection and 
    //finally injects the screenPane to the controller.
    public boolean loadScreen(String name, String resource) {
        
        try{
            
            FXMLLoader screenLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) screenLoader.load();
            ControlledScreen screenController = ((ControlledScreen) screenLoader.getController());
            screenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    //This method tries to display the screen with a predefined name.
    //First it makes sure the screen has been already loaded. Then if there is more than
    //one screen, the new screen is added, and the current screen is removed.
    //If there isn't any screen being displayed, the new screen is just added to the root.
    public boolean setScreen(final String name) {
        if (screens.get(name) != null) { // screen loaded
            final DoubleProperty opacity = opacityProperty();
            
            if (!getChildren().isEmpty()) { // if there is more than one screen
                Timeline fade = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                new KeyFrame(new Duration(250), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        getChildren().remove(0);                    //remove the displayed screen
                        getChildren().add(0, screens.get(name));    //add new screen
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(250), new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, 0.0)));
                fade.play();
            
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));                   //no other screens being displayed
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2000), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            
            return true;
        } else {
            System.out.println("The screen cannot be set because it has not been loaded.");
            return false;
        } 
        }
    
    //This method will remove the screen with given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen does not exist");
            return false;
        } else {
            return true;
        }
    }
    
        
    }


   
