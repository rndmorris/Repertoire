/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;

import Repertoire.Dictionaries.LibraryManager;
import Repertoire.Shared.Entities.AvailableDictionary;
import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import Repertoire.Shared.Mapping.Xml.AvailableDictionaryListXmlMapper;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.xml.bind.JAXBException;

/**
 * FXML Controller class
 *
 * @author Tucker
 */
public class LibraryController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    AvailableDictionaryList loadedEntries;
    SearchParams curParams;
    
    @FXML
    Button buttonInstalled;
    @FXML
    Button buttonFindNew;
    @FXML
    Pane paneInstalled;
    
    @FXML
    VBox vboxInstalled;
    @FXML
    Pane paneFindNew;
    @FXML
    VBox vboxFindNew;
    @FXML
    ScrollPane scrollpaneFindNew;
    
    @FXML
    ChoiceBox pageSize;
    @FXML
    TextField searchTerm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pageSize.getItems().addAll(10,25,50,100);
        pageSize.setValue(10);
        
        paneInstalled.setVisible(false);
        paneFindNew.setVisible(false);
    }   
    
    
    @FXML
        void onBack(ActionEvent event) {
           myController.setScreen("Main");
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    
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
        int pageSizeVal = (int)pageSize.getValue();
        String searchTerms = searchTerm.getText();
        curParams = new SearchParams(pageSizeVal, searchTerms);
        
        loadedEntries = search(curParams);
        int leftBound = curParams.getPageOffset() * curParams.getPageSize();
        int rightBound = curParams.getPageOffset() * curParams.getPageSize() + curParams.getPageSize();
        if (rightBound > loadedEntries.size()){
            rightBound = loadedEntries.size() -1;
        }
        List<AvailableDictionary> sublist = loadedEntries;
        setDisplayElements(vboxFindNew,sublist);
        scrollpaneFindNew.setVvalue(0.0);
    }
    
    AvailableDictionaryList search(SearchParams parameters) throws UnsupportedEncodingException, MalformedURLException, JAXBException
    {
        StringBuilder buildUrl = new StringBuilder();
        buildUrl
                .append("http://localhost:8080/api/Dictionary/Available")
                .append("?")
                .append("PageOffset=").append(parameters.getPageOffset())
                .append("&PageSize=").append(parameters.getPageSize());
        if (parameters.getSearchTerms().trim().equals("") == false)
        {
            buildUrl.append("&SearchTerms=").append(URLEncoder.encode(parameters.getSearchTerms(), "UTF-8"));
        }
        
        URL url = new URL(buildUrl.toString());
        
        return new AvailableDictionaryListXmlMapper().entityListFromUrl(url);
    }
    
    private void setDisplayElements(VBox outputBox, List<AvailableDictionary> list) {
        outputBox.getChildren().clear();
        for (AvailableDictionary dict : list) {
            BorderPane entryRow = new BorderPane();
            entryRow.getStyleClass().clear();
            entryRow.getStyleClass().add("no-padding");
            
            ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/assets/img/dictionaryPlaceholderIcon.png")));
            image.setPreserveRatio(true);
            image.setFitHeight(64);
            image.setFitWidth(64);
            entryRow.setLeft(image);
            entryRow.setMaxWidth(outputBox.getWidth()-10);
            
            GridPane labels = new GridPane();
            Label name = new Label(dict.getDictionaryName());
            labels.add(name,0,0);
            Label author = new Label("by " + dict.getCreatedBy());
            labels.add(author,0,1);
            
            entryRow.setCenter(labels);
            
            HBox buttonSet = new HBox();
            if (LibraryManager.dictionaryIsInstalled(dict)) {
                ImageView loadImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/img/loadDictionary.png")));
                loadImg.setFitHeight(48);
                loadImg.setFitWidth(48);
                Button loadButton = new Button("",loadImg);
                loadButton.setOnAction(e -> {
                    LibraryManager.loadDictionary(dict);
                });
                ImageView delImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/img/deleteDictionary.png")));
                delImg.setFitHeight(48);
                delImg.setFitWidth(48);
                Button delButton = new Button("",delImg);
                delButton.setOnAction(e -> {
                    LibraryManager.uninstallDictionary(dict);
                    setPaneInstalled();
                });
                buttonSet.getChildren().add(loadButton);
                buttonSet.getChildren().add(delButton);
            }
            else {
                ImageView loadImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/img/downloadDictionary.png")));
                loadImg.setFitHeight(48);
                loadImg.setFitWidth(48);
                Button downloadBtn = new Button("",loadImg);
                downloadBtn.setOnAction(e -> {
                    LibraryManager.installDictionary(dict);
                });
                buttonSet.getChildren().add(downloadBtn);
            }
            entryRow.setRight(buttonSet);
            
            outputBox.getChildren().add(entryRow);
        }
    }
    
    @FXML
    private void buttonInstalledPressed(ActionEvent ae) {
        setPaneInstalled();
    }
    private void setPaneInstalled() {
        enablePaneOptionButtons();
        buttonInstalled.setDisable(true);
        hidePanes();
        paneInstalled.setVisible(true);
        setDisplayElements(vboxInstalled,LibraryManager.getLIST());
    }
    @FXML
    private void buttonFindNewPressed(ActionEvent ae) {
        setPaneFindNew();
    }
    private void setPaneFindNew() {
        enablePaneOptionButtons();
        buttonFindNew.setDisable(true);
        hidePanes();
        paneFindNew.setVisible(true);
    }
    private void enablePaneOptionButtons() {
        buttonInstalled.setDisable(false);
        buttonFindNew.setDisable(false);
    }
    private void hidePanes() {
        paneInstalled.setVisible(false);
        paneFindNew.setVisible(false);
    }
    
    final class SearchParams
    {
        private int pageSize;
        private int pageOffset;
        private String searchTerms;
        
        SearchParams(int pageSize, String searchTerms)
        {
            setPageOffset(0);
            setPageSize(pageSize);
            setSearchTerms(searchTerms);
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
        public String getSearchTerms()
        {
            return searchTerms;
        }
        private void setSearchTerms(String value)
        {
            searchTerms = value;
        }
    }
}
