/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Dictionaries;

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
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rndmorris
 */
@XmlRootElement(name = "installedDictionaries")
@XmlAccessorType(XmlAccessType.NONE)
public class InstalledDictionaryManager {

    private static InstalledDictionaryManager instance;
    private static File saveFile = new File("masterList.xml");
    private static File installFolder = new File("data");

    public static InstalledDictionaryManager get() {
        if (instance == null) {
            if (saveFile.exists()) {
                loadFromFile();
            } else {
                instance = new InstalledDictionaryManager();
            }
        }
        return instance;
    }

    public static void installDictionary(AvailableDictionary dict) {
        get().pInstallDictionary(dict);
    }

    public static void uninstallDictionary(AvailableDictionary dict) {
        get().pUninstallDictionary(dict);
    }

    public static void loadFromFile() {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(InstalledDictionaryManager.class);
        } catch (JAXBException ex) {
            Logger.getLogger(InstalledDictionaryManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(InstalledDictionaryManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            instance = (InstalledDictionaryManager) unmarshaller.unmarshal(saveFile);
        } catch (JAXBException ex) {
            Logger.getLogger(InstalledDictionaryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveToFile() {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(InstalledDictionaryManager.class);
        } catch (JAXBException ex) {
            Logger.getLogger(InstalledDictionaryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Marshaller marshaller = null;
        try {
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            Logger.getLogger(InstalledDictionaryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            marshaller.marshal(get(), get().saveFile);
        } catch (JAXBException ex) {
            Logger.getLogger(InstalledDictionaryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean dictionaryIsInstalled(AvailableDictionary dict) {
        return get().pDictionaryIsInstalled(dict);
    }


    public static AvailableDictionaryList getList() {
        return get().pGetList();
    }

    @XmlElement(name = "dictionaryId")
    private AvailableDictionaryList list;

    private InstalledDictionaryManager() {
        if (!installFolder.exists()) {
            installFolder.mkdirs();
        }
        list = new AvailableDictionaryList();
    }
    private boolean pDictionaryIsInstalled(AvailableDictionary dict) {
        return list.contains(dict);
    }
    private AvailableDictionaryList pGetList() {
        return list;
    }

    private void pInstallDictionary(AvailableDictionary dict) {
        String dictIdHash = Hashing.Sha256ToBase16((Long) dict.getDictionaryId());
        File dictFolder = new File(installFolder.getAbsolutePath() + File.separator + dictIdHash);

        if (dictFolder.exists()) {
            recursiveDelete(dictFolder);
        }

        dictFolder.mkdirs();
        try {
            downloadDictionary(dict, dictFolder);
        } catch (IOException ex) {
            Logger.getLogger(InstalledDictionaryManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!list.contains(dict)) {
            list.add(dict);
        }
        saveToFile();
    }

    private void downloadDictionary(AvailableDictionary dict, File dictFolder) throws MalformedURLException, IOException {
        URL url = new URL("http://localhost:8080/api/DownloadDictionary?versionId=" + dict.getDictionaryId());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String disposition = conn.getHeaderField("Content-Disposition");
            String contentType = conn.getContentType();
            int contentLength = conn.getContentLength();
            String fileName = "dictionary.json";
            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            }
 
            // opens input stream from the HTTP connection
            InputStream inputStream = conn.getInputStream();
            String saveFilePath = dictFolder.getAbsolutePath() + File.separator + fileName;
             
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            outputStream.close();
            inputStream.close();

        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        conn.disconnect();
    }

    private void pUninstallDictionary(AvailableDictionary dict) {
        File dictFolder = new File(installFolder.getAbsolutePath() + File.separator + Hashing.Sha256ToBase16(dict.getDictionaryId()));
        recursiveDelete(dictFolder);
        list.remove(dict);
    }

    private void recursiveDelete(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                recursiveDelete(child);
            }
        }
        file.delete();
    }

}
