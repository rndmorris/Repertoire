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
public interface IMapper<T> {
    public T Map(Node xmlNode);
}
