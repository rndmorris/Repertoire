/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Dictionaries;

import Repertoire.Shared.Entities.AvailableDictionary;
import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        int dictIdHash = ((Long) dict.getDictionaryId()).hashCode();
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
        URL url = new URL("http://localhost:8080/Downloads?dictId=" + dict.getDictionaryId());
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File(dictFolder.getAbsolutePath() + File.separator + "data.json"));
        byte[] buffer = new byte[512];
        while (true) {
            int len = in.read(buffer);
            if (len == -1) {
                break;
            }
            fos.write(buffer, 0, len);
        }
        in.close();
        fos.flush();
        fos.close();
    }

    private void pUninstallDictionary(AvailableDictionary dict) {
        File dictFolder = new File(installFolder.getAbsolutePath() + File.separator + ((Long) dict.getDictionaryId()).hashCode());
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
