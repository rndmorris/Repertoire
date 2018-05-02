/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Dictionaries;

import Repertoire.Dictionary;
import Repertoire.Shared.Entities.AvailableDictionary;
import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import Repertoire.Shared.Hashing;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author rndmorris
 */
public class LibraryManager {

    private static final File INSTALL_FOLDER = new File("installedDictionaries");
    private static final File LIST_FILE = new File(INSTALL_FOLDER.getAbsolutePath() + File.separator + "dictionaries.xml");
    private static AvailableDictionaryList LIST;
     
    private static File getDictionaryFolder(AvailableDictionary dict) {
        String dictIdHash = Hashing.Sha256ToBase16(dict.getDictionaryId());
        return new File(INSTALL_FOLDER.getAbsolutePath() + File.separator + dictIdHash);
    }

    public static void installDictionary(AvailableDictionary dict) {
        if (false == dictionaryIsInstalled(dict)) {
            File dictFolder = getDictionaryFolder(dict);
            if (dictFolder.exists()) {
                recursiveDelete(dictFolder);
            }

            dictFolder.mkdirs();
            try {
                downloadDictionary(dict, dictFolder);
            } catch (IOException ex) {
                Logger.getLogger(LibraryManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!LIST.contains(dict)) {
                LIST.add(dict);
            }
            saveListToFile();
        }
    }

    public static void uninstallDictionary(AvailableDictionary dict) {
        File dictFolder = getDictionaryFolder(dict);
        recursiveDelete(dictFolder);
        for (int i = 0; i < LIST.size(); i++) {
            if (LIST.get(i).getDictionaryId() == dict.getDictionaryId()) {
                LIST.remove(i);
                break;
            }
        }
    }

    public static void loadDictionary(AvailableDictionary dict) {
        if (dictionaryIsInstalled(dict)) {
            try {
                File testFile = new File(getDictionaryFolder(dict) + File.separator +"dictionary.json");
                Dictionary.dataInit(testFile.toURI().toURL());
            }catch (MalformedURLException ex)
            {
                ex.printStackTrace(System.err);
            }
        }
        else {
            throw new IllegalArgumentException("Dictionary id " + dict.getDictionaryId() + "is not installed");
        }
    }
    
    public static void loadListFromFile() {
        if (LIST_FILE.exists() == false) {
            LIST = new AvailableDictionaryList();
            INSTALL_FOLDER.mkdirs();
            saveListToFile();
        }
        else {
            JAXBContext context;
            Unmarshaller unmarshaller;
            try {
                context = JAXBContext.newInstance(AvailableDictionaryList.class);
                unmarshaller = context.createUnmarshaller();
                LIST = (AvailableDictionaryList) unmarshaller.unmarshal(LIST_FILE);
            } catch (JAXBException ex) {
                Logger.getLogger(LibraryManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void saveListToFile() {
        JAXBContext context;
        Marshaller marshaller;
        try {
            context = JAXBContext.newInstance(AvailableDictionaryList.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(LIST, LIST_FILE);
        } catch (JAXBException ex) {
            Logger.getLogger(LibraryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean dictionaryIsInstalled(AvailableDictionary dict) {
        boolean output = false;
        for (AvailableDictionary item : LIST) {
            if (item.getDictionaryId() == dict.getDictionaryId()) {
                if (item.getVersionId() >= dict.getVersionId())
                {
                    output = true;
                    break;
                }
                else {
                    break;
                }
            }
        }
        return output;
    }


    public static AvailableDictionaryList getLIST() {
        return LIST;
    }
    

    private static void downloadDictionary(AvailableDictionary dict, File dictFolder) throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:8080/api/Dictionary/Download?VersionId=" + dict.getDictionaryId());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "dictionary.json";
 
            try (InputStream inputStream = conn.getInputStream()) {
                String saveFilePath = dictFolder.getAbsolutePath() + File.separator + fileName;
                
                try (FileOutputStream outputStream = new FileOutputStream(saveFilePath)) {
                    int bytesRead;
                    byte[] buffer = new byte[4096];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }

        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        conn.disconnect();
    }

    private static void recursiveDelete(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                recursiveDelete(child);
            }
        }
        file.delete();
    }

}
