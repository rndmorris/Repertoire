/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Xml;

import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import Repertoire.Shared.EntityLists.EntityList;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.w3c.dom.Document;

/**
 *
 * @author rndmorris
 * @param <ListType>
 */
public interface IXmlMapper<ListType extends EntityList>{

    /**
     *
     * @param entityList
     * @return
     * @throws JAXBException
     */
    public Document documentFromEntityList(ListType entityList) throws JAXBException;
    public String stringFromEntityList(ListType entityList) throws JAXBException;
    public ListType entityListFromUrl(String url) throws JAXBException, MalformedURLException;
    public ListType entityListFromUrl(URL url) throws JAXBException, MalformedURLException;
}
