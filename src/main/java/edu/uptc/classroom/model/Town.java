/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.model;

/**
 *
 * @author jairoarmando
 */
public class Town {
    private String code;
    private String department;
    private String name;

    public Town(String code, String department, String name) {
        this.code = code;
        this.department = department;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Town{" + "code=" + code + ", department=" + department + ", name=" + name + '}';
    }
    
}
