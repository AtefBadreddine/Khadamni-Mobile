/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hp
 */
public class Stages {



    private int id_stage;
    private String poste;
    private String nom_entreprise;
    private String adresse_stage;
    private String duree_stage;
    private String type_stage;
    private int id_user;

    public Stages() {
    }

    public Stages(int id_stage, String poste, String nom_entreprise, String adresse_stage, String duree_stage, String type_stage, int id_user) {
        this.id_stage = id_stage;
        this.poste = poste;
        this.nom_entreprise = nom_entreprise;
        this.adresse_stage = adresse_stage;
        this.duree_stage = duree_stage;
        this.type_stage = type_stage;
        this.id_user = id_user;
    }

    public Stages(String poste, String nom_entreprise, String adresse_stage, String duree_stage, String type_stage, int id_user) {
        this.poste = poste;
        this.nom_entreprise = nom_entreprise;
        this.adresse_stage = adresse_stage;
        this.duree_stage = duree_stage;
        this.type_stage = type_stage;
        this.id_user = id_user;
    }

    public Stages(String poste, String nom_entreprise, String adresse_stage, String duree_stage, String type_stage) {
        this.poste = poste;
        this.nom_entreprise = nom_entreprise;
        this.adresse_stage = adresse_stage;
        this.duree_stage = duree_stage;
        this.type_stage = type_stage;
    }
    
    

    public int getId_stage() {
        return id_stage;
    }

    public void setId_stage(int id_stage) {
        this.id_stage = id_stage;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public String getAdresse_stage() {
        return adresse_stage;
    }

    public void setAdresse_stage(String adresse_stage) {
        this.adresse_stage = adresse_stage;
    }

    public String getDuree_stage() {
        return duree_stage;
    }

    public void setDuree_stage(String duree_stage) {
        this.duree_stage = duree_stage;
    }

    public String getType_stage() {
        return type_stage;
    }

    public void setType_stage(String type_stage) {
        this.type_stage = type_stage;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

   
 


}
