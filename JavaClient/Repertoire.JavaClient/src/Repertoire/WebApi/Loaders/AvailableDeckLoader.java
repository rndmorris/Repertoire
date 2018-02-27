/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.WebApi.Loaders;

/**
 *
 * @author rndmorris
 */
public class AvailableDeckLoader extends ApiLoader{

    @Override
    public String GetSourceUrl() {
        return "http://localhost:8080/api/decks/available";
    }

    @Override
    protected Object MapFromXml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
