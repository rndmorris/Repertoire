/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.WebApi.Mappers;

import Repertoire.WebApi.Objects.AvailableDeck;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author rndmorris
 */
public class AvailableDeckMapper extends Mapper<AvailableDeck>{

    @Override
    public AvailableDeck Map(Node xmlNode) {
        AvailableDeck deck = new AvailableDeck();
        
        NodeList props = xmlNode.getChildNodes();
        for (int i = 0; i < props.getLength(); i++)
        {
            Node prop = props.item(i);
            String val = prop.getNodeValue();
            String propName = prop.getNodeName();
            switch(propName)
            {
                case "createdBy" :
                    deck.setCreatedBy(val);
                    break;
                case "deckId" :
                    deck.setDeckId(Long.parseLong(val));
                    break;
                case "deckName" :
                    deck.setDeckName(val);
                    break;
                case "definitionUpdatedOn" :
                    try
                    {
                        deck.setDefinitionUpdatedOn(DateFormat.getInstance().parse(val));
                    }
                    catch (ParseException ex)
                    {}
                    break;
                case "description" :
                    deck.setDescription(val);
                    break;
                case "fileUpdatedOn" :
                    try
                    {
                        deck.setFileUpdatedOn(DateFormat.getInstance().parse(val));
                    }
                    catch (ParseException ex)
                    {}
                    break;
                case "currentVersion" :
                    deck.setCurrentVersion(Integer.parseInt(val));
                    break;
                case "versionId" :
                    deck.setVersionId(Long.parseLong(val));
                    break;
                default:
                    break;
            }
        }
        
        return deck;
    }
}
