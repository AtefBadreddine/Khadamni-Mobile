/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;




/**
 *
 * @author dhiaa
 */
public class Emploi {

    int idemploi;
    String titre;
//    LocalDate dateCreation;
    int salaire;
    String niveau_experience;
    String type_contrat;
    String description;
    User user;

    public Emploi() {
    }

    public Emploi(int idemploi, String titre, int salaire, String niveau_experience, String type_contrat, String description, User user) {
        this.idemploi = idemploi;
        this.titre = titre;
        this.salaire = salaire;
        this.niveau_experience = niveau_experience;
        this.type_contrat = type_contrat;
        this.description = description;
        this.user = user;
    }

    public Emploi(String titre, int salaire, String niveau_experience, String type_contrat, String description, User user) {
        this.titre = titre;
        this.salaire = salaire;
        this.niveau_experience = niveau_experience;
        this.type_contrat = type_contrat;
        this.description = description;
        this.user = user;
    }

    public int getIdemploi() {
        return idemploi;
    }

    public void setIdemploi(int idemploi) {
        this.idemploi = idemploi;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getNiveau_experience() {
        return niveau_experience;
    }

    public void setNiveau_experience(String niveau_experience) {
        this.niveau_experience = niveau_experience;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Emploi{" + "idemploi=" + idemploi + ", titre=" + titre + ", salaire=" + salaire + ", niveau_experience=" + niveau_experience + ", type_contrat=" + type_contrat + ", description=" + description + ", user=" + user + '}';
    }
    
    

   

}
