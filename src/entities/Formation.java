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
public class Formation {

    private int id_formation;
    private int id_formateur;
    private String description;
    private String nom_formation;
    private String nom_formateur;
    private int duree;
    private int prix;
    private float rating;

    public Formation() {

    }

    public Formation(int id_formateur, String nom_formation, String description, int duree, int prix) {

        this.id_formateur = id_formateur;
        this.nom_formation = nom_formation;
        this.description = description;
        this.duree = duree;
        this.prix = prix;
       
    }

    public Formation(int id_formation, int id_formateur, String nom_formation, String description, int duree, int prix) {
        this.id_formation = id_formation;
        this.id_formateur = id_formateur;
        this.nom_formation = nom_formation;
        this.description = description;
        this.duree = duree;
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public int getId_formateur() {
        return id_formateur;
    }

    public int getId_formation() {
        return id_formation;
    }

    public String getNom_formation() {
        return nom_formation;
    }

    public int getDuree() {
        return duree;
    }

    public int getPrix() {
        return prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId_formateur(int id_formateur) {
        this.id_formateur = id_formateur;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public void setNom_formation(String nom_formation) {
        this.nom_formation = nom_formation;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setNom_formateur(String nom_formateur) {
        this.nom_formateur = nom_formateur;
    }

    public String getNom_formateur() {
        return nom_formateur;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    

    @Override
    public String toString() {
        return "formation: " + nom_formation ;
    }

        

}

  
