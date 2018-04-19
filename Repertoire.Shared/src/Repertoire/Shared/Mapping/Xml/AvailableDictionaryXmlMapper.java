/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Xml;

import Repertoire.Shared.Entities.AvailableDictionary;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author rndmorris
 */
public class AvailableDictionaryXmlMapper {
    public static AvailableDictionary map(URL inputURL) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(AvailableDictionary.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (AvailableDictionary)unmarshaller.unmarshal(inputURL);
    }
    public static String map(AvailableDictionary inputDict) throws PropertyException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(AvailableDictionary.class);
        Marshaller marshaller = context.createMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        marshaller.marshal(inputDict,sw);
        return sw.toString();
    }
}
