/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.WebApi.Loaders;

import java.util.List;

/**
 *
 * @author rndmorris
 */
public interface IApiLoader<T> {
    public List<T> Load();
}
