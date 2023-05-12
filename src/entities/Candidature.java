/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;





public class Candidature {
    int id_candidature;
    User user;
    Emploi emploi;

    public Candidature(int id_candidature, User user, Emploi emploi) {
        this.id_candidature = id_candidature;
        this.user = user;
        this.emploi = emploi;
    }

    public Candidature(User user, Emploi emploi) {
        this.user = user;
        this.emploi = emploi;
    }

    public int getId_candidature() {
        return id_candidature;
    }

    public void setId_candidature(int id_candidature) {
        this.id_candidature = id_candidature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Emploi getEmploi() {
        return emploi;
    }

    public void setEmploi(Emploi emploi) {
        this.emploi = emploi;
    }

    @Override
    public String toString() {
        return "Candidature{" + "id_candidature=" + id_candidature + ", user=" + user + ", emploi=" + emploi + '}';
    }
  

    
}
