/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Atef
 */
public class Inscription {
    private int id_inscription;
    private int id_formation;
    private int id_user;
    private String date_inscription;

    public Inscription(int id_formation, int id_user) {
        this.id_formation = id_formation;
        this.id_user = id_user;
    }

    public void setId_inscription(int id_inscription) {
        this.id_inscription = id_inscription;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_formation() {
        return id_formation;
    }

    public int getId_inscription() {
        return id_inscription;
    }

    public int getId_user() {
        return id_user;
    }

    public void setDate_inscription(String date_inscription) {
        this.date_inscription = date_inscription;
    }

    public String getDate_inscription() {
        return date_inscription;
    }
    
}
