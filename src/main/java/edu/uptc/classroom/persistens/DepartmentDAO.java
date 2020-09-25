/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.persistens;

import edu.uptc.classroom.model.Department;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jairoarmando
 */
public class DepartmentDAO {

    private ConnectDB connect;

    public DepartmentDAO() {
        connect = new ConnectDB("jdbc:mysql://localhost:3306/colombia", "uptc", "estudiante");
    }
    
    /**
     * Método que retorna un objeto ResultSet con los datos de los Departamentos almacenados en la Base de Datos.
     * @return El Conjunto de Resultados
     * @throws SQLException Propaga una Excepción si hay error en la consulta a la Base de Datos.
     */

    public ResultSet loadDepartments() throws SQLException {
        if (connect.getConnect()) {
            Statement statement = connect.getConnection().createStatement();
            return statement.executeQuery("select * from department");
        }

        return null;
    }
    
    /**
     * Método que inserta un registro en la Base de Datos
     * @param dpto Especifica el objeto a insertar
     * @return El número de registros insertados
     * @throws SQLException Propaga una Excepción si hay error en la consulta a la Base de Datos.
     */

    public int insertDepartment(Department dpto) throws SQLException {

        if (connect.getConnect()) {
            Statement statement = connect.getConnection().createStatement();
            return statement.executeUpdate("insert into department values('"+dpto.getCode()+"','"+dpto.getName()+"')");
        }

        return 0;
    }

}
