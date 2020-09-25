/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jairoarmando
 */
public class FileJSONTest {
    private FileJSON file;
    
   

    @Test
    public void testGetDepartments( ) {
        file = new FileJSON( );
        file.openFile("resources/files/","departments.json");
        try {
            ArrayList<Department> dptos = file.getDepartments();
            assertEquals(33,dptos.size());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileJSONTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetTowns(){
        file = new FileJSON( );
        file.openFile("resources/files/","towns.json");
        
        try {
            ArrayList<TownAux> towns = file.getTowns();
            assertEquals(1115, towns.size() );
             //towns.forEach( town -> System.out.println(town));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileJSONTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
