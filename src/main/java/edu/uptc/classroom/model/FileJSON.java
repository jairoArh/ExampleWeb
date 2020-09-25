/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author jairoarmando
 */
public class FileJSON {
    
    private String path;
    private String name;

    public FileJSON() {
        
    }
    
    public void openFile( String path, String name ){
        this.path = path;
        this.name = name;
    }
    
    public ArrayList<Department> getDepartments() throws FileNotFoundException{
        Gson gson = new Gson();
        
        
        Type type = new TypeToken<ArrayList<Department>>(){}.getType();
        ArrayList<Department> out = gson.fromJson(new FileReader( path + name ), type );
        
        return out;
    }
    
    public ArrayList<TownAux> getTowns() throws FileNotFoundException{
        Gson gson = new Gson();
        
        Type type = new TypeToken<ArrayList<TownAux>>(){}.getType();
        ArrayList<TownAux> out = gson.fromJson(new FileReader( path + name ), type );
        
        return out;
    }
    
}
