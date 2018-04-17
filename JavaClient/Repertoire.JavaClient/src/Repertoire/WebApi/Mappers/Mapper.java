/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.WebApi.Mappers;

import org.w3c.dom.Node;

/**
 *
 * @author rndmorris
 */
public abstract class Mapper<T> implements IMapper<T>{

    @Override
    abstract public T Map(Node xmlNode);
    
    
}
