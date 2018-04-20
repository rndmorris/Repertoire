/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.JavaClient.Controllers;

import Repertoire.Dictionaries.InstalledDictionaryManager;
import Repertoire.Dictionary;
import Repertoire.Program;
import Repertoire.Shared.Entities.AvailableDictionary;
import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import Repertoire.Shared.Hashing;
import Repertoire.Shared.Mapping.Xml.AvailableDictionaryListXmlMapper;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
        //setPaneInstalled();
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
        String nameSearchTerm = searchTerm.getText();
        String authorSearchTerm = searchTerm.getText();
        curParams = new SearchParams(pageSizeVal, nameSearchTerm, authorSearchTerm);
        
        loadedEntries = search(curParams);
        int leftBound = curParams.getPageOffset() * curParams.getPageSize();
        int rightBound = curParams.getPageOffset() * curParams.getPageSize() + curParams.getPageSize();
        if (rightBound > loadedEntries.size()){
            rightBound = loadedEntries.size() -1;
        }
        List<AvailableDictionary> sublist = loadedEntries.subList(leftBound,rightBound);
        setDisplayElements(vboxFindNew,sublist);
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
            if (InstalledDictionaryManager.dictionaryIsInstalled(dict)) {
                ImageView loadImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/img/loadDictionary.png")));
                loadImg.setFitHeight(48);
                loadImg.setFitWidth(48);
                Button loadButton = new Button("",loadImg);
                loadButton.setOnAction(e -> {
                    Dictionary test = new Dictionary();
                    try {
                        File testFile = new File("/home/rndmorris/GitRepos/Repertoire/Repertoire.JavaClient/data/"+Hashing.Sha256ToBase16(dict.getDictionaryId())+"/dictionary.json");
                        test.dataInit(testFile.toURI().toURL());
                    }catch (Exception ex)
                    {
                        ex.printStackTrace(System.err);
                    }
                });
                ImageView delImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/img/deleteDictionary.png")));
                delImg.setFitHeight(48);
                delImg.setFitWidth(48);
                Button delButton = new Button("",delImg);
                delButton.setOnAction(e -> {
                    InstalledDictionaryManager.uninstallDictionary(dict);
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
                    InstalledDictionaryManager.installDictionary(dict);
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
        setDisplayElements(vboxInstalled,InstalledDictionaryManager.getList());
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
