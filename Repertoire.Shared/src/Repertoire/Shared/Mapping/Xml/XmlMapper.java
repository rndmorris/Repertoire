/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Xml;

import Repertoire.Shared.EntityLists.EntityList;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.w3c.dom.Document;

/**
 *
 * @author rndmorris
 * @param <ListType>
 */
public abstract class XmlMapper<ListType extends EntityList> implements IXmlMapper<ListType> {
    
    /**
     *
     * @param entityList
     * @return
     * @throws JAXBException
     */
    @Override
    public abstract Document documentFromEntityList(ListType entityList) throws JAXBException;
    public abstract String stringFromEntityList(ListType entityList) throws JAXBException;
    
}



















