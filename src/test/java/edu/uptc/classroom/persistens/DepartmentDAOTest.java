/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.persistens;

import com.mysql.cj.xdevapi.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jairoarmando
 */
public class DepartmentDAOTest {
    
    private DepartmentDAO dpto;

    @Test
    public void testLoadDepartments( ) {
        dpto = new DepartmentDAO();
        try {
            ResultSet rs = dpto.loadDepartments();
            while( rs.next( ) ){
                String code = rs.getString("code");
                String name = rs.getString("nameDepartment");
                //System.out.println( "Codigo=" + code + " Nombre=" + name );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
