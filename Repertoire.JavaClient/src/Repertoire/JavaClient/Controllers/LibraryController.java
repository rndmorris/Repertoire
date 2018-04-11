/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;

import Repertoire.Program;
import Repertoire.Shared.Entities.AvailableDictionary;
import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import Repertoire.Shared.Mapping.Xml.AvailableDictionaryXmlMapper;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.xml.bind.JAXBException;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class LibraryController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    Button searchBtn;
    @FXML
    ChoiceBox pageSizeBox;
    @FXML
    TextField dictionaryNameField;
    @FXML
    TextField authorNameField;
    
    @FXML
    VBox entryBox;
    
    AvailableDictionaryList loadedEntries;
    SearchParams parameters;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pageSizeBox.setValue("10");
        pageSizeBox.getItems().addAll("10","25","50","100");
    }   
    
    @Override
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
    void deckMenuItem(ActionEvent event) {
        myController.setScreen("Inventory");
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
    void settingsMenuItem(ActionEvent event) {
        myController.setScreen("Settings");
        System.out.println("TEST");
    }
    
    @FXML
    void searchBtnPressed(ActionEvent ae) throws UnsupportedEncodingException, MalformedURLException, JAXBException {
        int pageSize = Integer.parseInt((String)pageSizeBox.getValue());
        String nameSearchTerm = dictionaryNameField.getText();
        String authorSearchTerm = authorNameField.getText();
        parameters = new SearchParams(pageSize, nameSearchTerm, authorSearchTerm);
        
        loadedEntries = search(parameters);
        displayEntryElements(parameters.getPageOffset()*parameters.getPageSize(),parameters.getPageSize());
    }
    
    AvailableDictionaryList search(SearchParams parameters) throws UnsupportedEncodingException, MalformedURLException, JAXBException
    {
        StringBuilder buildUrl = new StringBuilder();
        buildUrl
                .append("http://localhost:8080/api/AvailableDictionary")
                .append("?")
                .append("PageOffset=").append(parameters.getPageOffset())
                .append("&PageSize=").append(parameters.getPageSize());
        if (parameters.getNameSearchTerm().trim().equals("") == false)
        {
            buildUrl.append("&NameSearchTerm=").append(URLEncoder.encode(parameters.getNameSearchTerm(), "UTF-8"));
        }
        if (parameters.getAuthorSearchTerm().trim().equals("") == false)
        {
            buildUrl.append("&CreatorSearchTerm=").append(URLEncoder.encode(parameters.getAuthorSearchTerm(), "UTF-8"));
        }
        
        //String encodedString = URLEncoder.encode(buildUrl.toString(), "UTF-8");
        URL url = new URL(buildUrl.toString());
        
        return new AvailableDictionaryXmlMapper().entityListFromUrl(url);
    }
    
    void displayEntryElements(int startIndex, int count) throws MalformedURLException
    {
        entryBox.getChildren().clear();
        if ((0 <= startIndex && startIndex <= loadedEntries.size()) && (0 < count))
        {
            for (int index = startIndex; index < startIndex + count - 1 && index < loadedEntries.size(); index++)
            {
                AvailableDictionary entry = loadedEntries.get(index);
                HBox entryRow = new HBox();
                File imageFile = new File("dictionaryIcon.png");
                Image image = new Image(imageFile.toURI().toURL().toString());
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(64);
                imageView.setFitWidth(64);
                GridPane labels = new GridPane();
                Label name = new Label(entry.getDictionaryName());
                labels.add(name,0,0);
                Label author = new Label("by " + entry.getCreatedBy());
                labels.add(author,0,1);
                entryRow.getChildren().addAll(imageView,labels);
                entryBox.getChildren().add(entryRow);
            }
        }

    }
    
    final class SearchParams
    {
        private int pageSize;
        private int pageOffset;
        private String nameSearchTerm;
        private String authorSearchTerm;
        
        SearchParams(int pageSize, String nameSearchTerm, String authorSearchTerm)
        {
            setPageOffset(0);
            setPageSize(pageSize);
            setNameSearchTerm(nameSearchTerm);
            setAuthorSearchTerm(authorSearchTerm);
        }
        
        public int getPageSize()
        {
            return pageSize;
        }
        private void setPageSize(int value)
        {
            if (value < 1)
            {
                throw new IllegalArgumentException("New value cannot be less than 1.");
            }
            pageSize = value;
        }
        public int getPageOffset()
        {
            return pageOffset;
        }
        public void setPageOffset(int value)
        {
            if (value < 0)
            {
                throw new IllegalArgumentException("Value cannot be less than 0.");
            }
            pageOffset = value;
        }
        public String getNameSearchTerm()
        {
            return nameSearchTerm;
        }
        private void setNameSearchTerm(String value)
        {
            nameSearchTerm = value;
        }
        public String getAuthorSearchTerm()
        {
            return authorSearchTerm;
        }
        private void setAuthorSearchTerm(String value)
        {
            authorSearchTerm = value;
        }
    }
}
