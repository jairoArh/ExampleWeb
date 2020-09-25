/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.model;

import edu.uptc.classroom.persistens.DepartmentDAO;
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
    private DepartmentDAO department;

    public Management() throws FileNotFoundException {
        departments = new ArrayList<>();
        towns = new ArrayList<>();
        file = new FileJSON();
        department = new DepartmentDAO();
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
    
    public void loadDepartments( ) throws SQLException{
        ResultSet rs = department.loadDepartments();
        
         while( rs.next( ) ){
                String code = rs.getString("code");
                String name = rs.getString("nameDepartment");
                departments.add( new Department( code, name ) );
        }
    } 
    
    /**
     * Método que permite agregar un registro de Departamento en la Base de Datos.
     * @param code Especifica el código del Departamento
     * @param name Especifica el nombre del Departamento
     * @return El número de registros insertados en la base de datos.
     */
    
    public int addDepartmentDB(String code, String name){
        try {
            return department.insertDepartment( new Department(code, name));
        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
   
}
