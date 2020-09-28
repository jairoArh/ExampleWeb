/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.persistens;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairoarmando
 */
public class ConnectDB {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection connection; 
    private final String URL = "jdbc:mysql://localhost:3306/colombia?useSSL=false";
    private final String USER = "uptc";
    private final String PASSWORD = "estudiante";
    
    public boolean isConnect( ){
        boolean state = false;
        try {
            Class.forName( DRIVER ).newInstance( );
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            state = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return state;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
