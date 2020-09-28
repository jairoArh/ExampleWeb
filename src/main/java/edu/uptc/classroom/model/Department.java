/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.model;

import java.util.ArrayList;

/**
 *
 * @author jairoarmando
 */
public class Department {
    private String code;
    private String name;
    private ArrayList<Town> towns;

    public Department( ) {
        towns = new ArrayList<>();
    }

    public Department(String code, String name) {
        this.code = code;
        this.name = name;
        towns = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addTown(String code, String name ){
        towns.add( new Town(code, this.code, name ) );
    }
    
    public Town findTown( String code ){
        
        for( Town town : towns ){
            if( code.compareTo( town.getCode()) == 0 ){
                return town;
            }
        }
        
        return null;
        
    }
    
    public ArrayList<Town> getTowns(){
        return (ArrayList<Town>) towns.clone();
    }
    

    @Override
    public String toString() {
        return "Department{" + "code=" + code + ", name=" + name + '}';
    }
    
}
