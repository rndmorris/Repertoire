/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.WebApi.Loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rndmorris
 * @param <T>
 */
public abstract class ApiLoader<T> implements IApiLoader<T> {
    
    @Override
    public List<T> Load()
    {
        List<T> rVal = new ArrayList<>();
        
        
        
        
        return rVal;
    }

    public abstract String GetSourceUrl();
    protected String LoadXml()
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            URL inputUrl = new URL(GetSourceUrl());
            BufferedReader in = new BufferedReader(
                new InputStreamReader(inputUrl.openStream())
            );
            
            String inputLine;
            while((inputLine = in.readLine()) != null)
            {
                sb.append(inputLine);
            }
        }
        catch (IOException ex)
        {
        }
        
        return sb.toString();
    }
    protected abstract T MapFromXml();
}
