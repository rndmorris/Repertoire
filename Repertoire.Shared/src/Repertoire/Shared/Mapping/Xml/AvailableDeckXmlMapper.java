/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Xml;

import Repertoire.Shared.EntityLists.AvailableDeckList;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;

/**
 *
 * @author rndmorris
 */
public class AvailableDeckXmlMapper extends XmlMapper<AvailableDeckList> {
    
    private Marshaller prepareMarshaller() throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(AvailableDeckList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }
    
    @Override
    public Document documentFromEntityList(AvailableDeckList entityList) throws JAXBException
    {
        Marshaller marshaller = prepareMarshaller();
        DOMResult res = new DOMResult();
        marshaller.marshal(entityList,res);
        
        return (Document) res.getNode();
    }

    @Override
    public String stringFromEntityList(AvailableDeckList entityList) throws JAXBException {
        Marshaller marshaller = prepareMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.marshal(entityList, sw);
        
        return sw.toString();
    }
}
