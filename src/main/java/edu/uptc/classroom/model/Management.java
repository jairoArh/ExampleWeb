/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.model;

import edu.uptc.classroom.persistens.DepartmentDAO;
import edu.uptc.classroom.persistens.TownDAO;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairoarmando
 */
public class Management {
    private ArrayList<Department> departments;
    private ArrayList<Town> towns;
    private FileJSON file;
    private DepartmentDAO departmentDAO;
    private TownDAO townDAO;

    public Management()  {
        departments = new ArrayList<>();
        towns = new ArrayList<>();
        file = new FileJSON();
        departmentDAO = new DepartmentDAO();
        townDAO = new TownDAO();
    }
    
    /***
     * Método que busca un Departamento por código
     * @param code Parámeto de entrada que especifica el código del departamento a buscar
     * @return Un objeto Department si existe, si no, retorna null
     */
    public Department findDepartment( String code ){
        for( Department department : departments ){
            if ( code.compareTo( department.getCode() ) == 0 ){
                return department;
            }
        }
        
        return null;
    }
    

    /***
     * Método que permite cargar en el arreglo de departamentos los datos que se encuentran en el archivo de tipo JSON
     * @param path Especifica la ruta del archivo
     * @param name Especifica el nombre del archivo
     * @throws FileNotFoundException Propaga una Excepción si el Archivo no Existe
     */
     
    public void loadDepartments( String path, String name ) throws FileNotFoundException {
       file.openFile(path, name);
        
       departments = file.getDepartments();
       
    }
    
    /***
     * Método que retorna el Arreglo de Departamentos
     * @return Un ArrayList de Department
     */
    
    public ArrayList<Department> getDepartments(){
        
        Collections.sort( departments, ( o1, o2 )->{
            return o1.getName().compareTo(o2.getName());
        
        });
        
        return (ArrayList<Department>) departments.clone();
       
    }
    
    /***
     * Método que carga en el arreglo de objetos de tipo Town, los datos del Archivo JSON
     * @param path Especifica la ruta del archivo
     * @param name Especifica el nombre del archivo
     * @throws FileNotFoundException Propaga una Excepción si el archivo no existe,
     */

    public void loadTowns( String path, String name ) throws FileNotFoundException {
        file.openFile(path, name);
        ArrayList<TownAux> townsAux = file.getTowns();
        
        for( TownAux town : townsAux ){
             Department dpto = findDepartment( town.getDepartment( ) );
             dpto.addTown( town.getCode( ), town.getName( ) );
             towns.add( new Town(town.getCode(), dpto.getCode(), town.getName( ) ) );
        }
        
    }
    
    /**
     * Método que retorna el arreglo de municipios
     * @return El ArrayList de Town
     */
    
    public ArrayList<Town> getTowns(){
        
        return (ArrayList<Town>) towns.clone();
    }
    
    /**
     * Método qur retorna los Municipios de un Departamento especificado por código
     * @param codeDpto Especifgica el código del Departamento del cual se retornan los Municipios
     * @return Un Arreglo con los municipios del Departamento, o null si el departamento no existe.
     */
    
    public ArrayList<Town> getTownsDpto( String codeDpto ){
        Department dpto = findDepartment( codeDpto );
        
        return dpto != null ? dpto.getTowns() : null;
    }
    
    /**
     * Método que Busca un Municipio por código
     * @param code Especifica el código del Municipio a Buscar
     * @return Un Objeto Town si existe, si no, retorna null
     */
    
    public Town findTown( String code ){
        for ( Town town : towns ){
            if( town.getCode().equals(code)){
                return town;
            }
        }
        
        return null;
    }
    
    /**
     * Método que retorna el Departamento al que pertenece un Municipio especificado por código.
     * @param codeTown Especifica el código del municipio del cual se quiere obtener el Departamento al que pertenece
     * @return Un objeto Department, si existe, ni no, retorna null
     */
    
    public Department getDepartment( String codeTown ){
        Town town = findTown(codeTown);
        
        return town != null ? findDepartment( town.getDepartment( ) ) : null;
    }
    
    /**
     * Método que carga en el arreglo los Departamentos que se encuentran en una Tabla de una Base de Datos
     * @throws SQLException Propaga una Excepción si el gestor de la base de datos retorna algún tipo de error.
     */
    
    public void loadDepartmentsDB( ) throws SQLException{
        ResultSet rs = departmentDAO.loadDepartments();
        
         while( rs.next( ) ){
                String code = rs.getString("code");
                String name = rs.getString("nameDepartment");
                departments.add( new Department( code, name ) );
        }
    } 
    
    /**
     * Método que agrega en el arreglo de municipios los registros de la tabla en la Base de Datos
     * @throws SQLException 
     */
    
    public void loadTownsDB() throws SQLException{
        ResultSet rs = townDAO.loadTowns();
        while(rs.next()){
            String code = rs.getString("code");
            String department = rs.getString("ref_department");
            String name = rs.getString("name_town");
            towns.add( new Town(code, department, name) );
            Department dpto = findDepartment( department );
            dpto.addTown(code, name);
        }
        
    }
    
    /**
     * Método que permite agregar un registro de Departamento en la Base de Datos.
     * @param code Especifica el código del Departamento
     * @param name Especifica el nombre del Departamento
     * @return Verdadero si se puede agregar el Registro, falso, si no se puede
     */
    
    public boolean addDepartmentDB(String code, String name){
        try {
            if( findDepartment(code) == null ){
                Department dpto = new Department(code, name);
                departments.add( dpto );
                departmentDAO.insertDepartment( dpto );
                
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    /**
     * Método que agrega un Municipio con persistencia a la Base de Datos.
     * @param code Especifica el código del Municipio
     * @param dpto Especifica el Departamento al cual pertenece el municipio
     * @param name Especifica el Nopmbre del Municipio
     * @return Verdadero si se puede agregar el Registro, falso, si no se puede
     */
    
    public boolean addTown( String code, String dpto, String name ){
        if( findTown( code ) == null ){
            Town town = new Town(code, dpto, name);
            towns.add( town );
            Department department = findDepartment( dpto );
            department.addTown(code, name);
            try {
                townDAO.insertTown(town);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return false;
    }
    
    /**
     * Método para eliminar un objeto del arreglo de objetos y de la base de datos.
     * @param code Especifica el código o valor de la clave del objeto a borrar
     * @return Verdadero si puede eliminar, Falso si no.
     */
    
    public boolean deleteTownDB( String code ){
        Town town = findTown( code );
        if( town != null ){
            if( towns.remove(town) ){
                int delete = townDAO.deleteTown( code );
                
                return delete > 0;
                
            }   
        }
        
        return false;
    }
   
}
