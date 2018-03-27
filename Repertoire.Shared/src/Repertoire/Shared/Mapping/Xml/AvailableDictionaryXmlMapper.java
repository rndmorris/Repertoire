/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Xml;

import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;

/**
 *
 * @author rndmorris
 */
public class AvailableDictionaryXmlMapper extends XmlMapper<AvailableDictionaryList> {
    
    private Marshaller prepareMarshallerForXml() throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(AvailableDictionaryList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }
    
    @Override
    public Document documentFromEntityList(AvailableDictionaryList entityList) throws JAXBException
    {
        Marshaller marshaller = prepareMarshallerForXml();
        DOMResult res = new DOMResult();
        marshaller.marshal(entityList,res);
        
        return (Document) res.getNode();
    }

    @Override
    public String stringFromEntityList(AvailableDictionaryList entityList) throws JAXBException {
        Marshaller marshaller = prepareMarshallerForXml();
        StringWriter sw = new StringWriter();
        marshaller.marshal(entityList, sw);
        
        return sw.toString();
    }
    
    @Override
    public AvailableDictionaryList entityListFromUrl(String url) throws JAXBException, MalformedURLException
    {
        return entityListFromUrl(new URL(url));
    }

    @Override
    public AvailableDictionaryList entityListFromUrl(URL url) throws JAXBException, MalformedURLException {
        JAXBContext context = JAXBContext.newInstance(AvailableDictionaryList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        
        return (AvailableDictionaryList) unmarshaller.unmarshal(url);
    }
}
