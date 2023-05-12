/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Mayla Bouzakoura
 */
public class Evenement {

    private int idEvenement;
    private String nomEvenement, descriptionEvenement, Inviter;
    private Date dateEvenement;

    public Evenement() {
    }

    public Evenement(int idEvenement, String nomEvenement, String descriptionEvenement, String Inviter, Date dateEvenement) {
        this.idEvenement = idEvenement;
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.Inviter = Inviter;
        this.dateEvenement = dateEvenement;
    }

    public Evenement(String nomEvenement, String descriptionEvenement, String Inviter, Date dateEvenement) {
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.Inviter = Inviter;
        this.dateEvenement = dateEvenement;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public String getInviter() {
        return Inviter;
    }

    public void setInviter(String Inviter) {
        this.Inviter = Inviter;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + idEvenement + ", nomEvenement=" + nomEvenement + ", descriptionEvenement=" + descriptionEvenement + ", Inviter=" + Inviter + ", dateEvenement=" + dateEvenement + '}';
    }

    public Evenement(String nomEvenement, String descriptionEvenement) {
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
    }

}

