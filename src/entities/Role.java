/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author choua
 */
public class Role {
    private int id_role; 
    private String type; 

    public Role() {
    }

    public Role(int id_role, String type) {
        this.id_role = id_role;
        this.type = type;
    }

    public Role(String type) {
        this.type = type;
    }

    public int getId_role() {
        return id_role;
    }

    public String getType() {
        return type;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Role{" + "id_role=" + id_role + ", type=" + type + '}';
    }
    
    
}
