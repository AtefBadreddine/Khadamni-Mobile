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
public class Prerequis {
    
   enum TypeP {
        bac, bac_1, bac_2, bac_3, bac_4, bac_5
    }
   
   private int id_prerequis;
   private int id_stage; 
   private TypeP niveau_etude;
   private String description;

    public Prerequis() {
    }

    public Prerequis(int id_prerequis, int id_stage, TypeP niveau_etude, String description) {
        this.id_prerequis = id_prerequis;
        this.id_stage = id_stage;
        this.niveau_etude = niveau_etude;
        this.description = description;
    }

    public Prerequis(int id_stage, TypeP niveau_etude, String description) {
        this.id_stage = id_stage;
        this.niveau_etude = niveau_etude;
        this.description = description;
    }

    public int getId_prerequis() {
        return id_prerequis;
    }

    public void setId_prerequis(int id_prerequis) {
        this.id_prerequis = id_prerequis;
    }

    public int getId_stage() {
        return id_stage;
    }

    public void setId_stage(int id_stage) {
        this.id_stage = id_stage;
    }

    public String getNiveau_etude() {
        return niveau_etude.name();
    }

    public void setNiveau_etude(String niveau_etude) {
        this.niveau_etude = TypeP.valueOf(niveau_etude);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Prerequis{" + "id_prerequis=" + id_prerequis + ", id_stage=" + id_stage + ", niveau_etude=" + niveau_etude + ", description=" + description + '}';
    }
 

}
