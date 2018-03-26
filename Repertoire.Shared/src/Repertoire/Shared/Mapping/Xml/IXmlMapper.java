/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Xml;

import Repertoire.Shared.EntityLists.EntityList;
import javax.xml.bind.JAXBException;
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
    public Document documentFromEntityList(ListType entityList) throws JAXBException ;
}
