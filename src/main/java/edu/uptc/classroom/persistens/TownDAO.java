/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.persistens;

import edu.uptc.classroom.model.Town;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairoarmando
 */
public class TownDAO {
    
    private ConnectDB connect;

    public TownDAO() {
        connect = new ConnectDB();
    }
    
    
    
    public ResultSet loadTowns() throws SQLException{
        if( connect.isConnect( ) ){
            Statement statement = connect.getConnection().createStatement();
            return statement.executeQuery("select * from town");
        }
        
        return null;
    }
    
    public int insertTown( Town town ) throws SQLException{
        if( connect.isConnect( ) ){
            Statement statement = connect.getConnection().createStatement();
            return statement.executeUpdate("insert into town values('"+ town.getCode()+"','"+town.getDepartment()+"','"+town.getName()+"')");
        }
        
        return 0;
    }
    
    public int deleteTown( String code ){
        if( connect.isConnect( ) ){
            Statement statement;
            try {
                statement = connect.getConnection().createStatement();
                return statement.executeUpdate("delete from town where code='"+code+"'");
            } catch (SQLException ex) {
                Logger.getLogger(TownDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return 0;
    }
    
}
