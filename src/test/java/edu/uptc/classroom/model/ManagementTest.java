/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.model;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jairoarmando
 */
public class ManagementTest {
    
    private Management mng;
    
    private void setup(){
        try {
            mng = new Management( );
            mng.loadDepartments( "resources/files/", "departments.json");
            mng.loadTowns("resources/files/", "towns.json");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setupDB() throws SQLException{
        mng = new Management( );
        mng.loadDepartmentsDB( );
        mng.loadTownsDB( );
    }

    @Test
    public void testGetDepartments( ) {
        setup( );
        ArrayList<Department> dptos = mng.getDepartments();
        assertEquals(33, dptos.size());
        assertEquals("91",dptos.get(0).getCode( ) );
        assertNotNull( mng.getDepartments().get(0).getTowns( ) );
    }
    
    @Test
    public void testGetTowns(){
       setup( );
       ArrayList<Town> towns = mng.getTowns();
       assertEquals(1115, towns.size());
       assertEquals("Medellín", towns.get(0).getName());
       assertNotNull( towns.get(0).getDepartment());
       assertEquals("05", towns.get(0).getDepartment());
    }
    
    @Test
    public void testGetTownsDepartment(){
        setup();
        assertNotNull( mng.getTownsDpto("05"));
        assertNotNull( mng.getTownsDpto("15"));
        assertNotNull( mng.getTownsDpto("85"));
        assertNull( mng.getTownsDpto("0884525"));
        assertEquals("Tunja", mng.getTownsDpto("15").get(0).getName());
    }
    
    @Test
    public void testFindTown(){
        setup();
        assertNotNull(mng.findTown("15001"));
        assertNull(mng.findTown("150este01"));
        assertEquals("Chivor", mng.findTown("15236").getName());
    }
    
    @Test
    public void testGetDepartment(){
        setup();
        assertNotNull( mng.getDepartment("15001") );
        assertNotNull( mng.getDepartment("15236") );
        assertNull( mng.getDepartment("no existe") );
        assertEquals("Boyacá",mng.getDepartment("15001").getName());
    }
    
    @Test
    public void getDepartmentsDB(){
        try {
            setupDB();
            assertEquals(33,mng.getDepartments().size());
            
        } catch (SQLException ex) {
            Logger.getLogger(ManagementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
  
    
}
