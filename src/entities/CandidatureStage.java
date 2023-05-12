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
public class CandidatureStage {
    private int id_candidature;
    private int id_user;
    private int id_stage;

    public CandidatureStage() {
    }

    public CandidatureStage(int id_candidature, int id_user, int id_stage) {
        this.id_candidature = id_candidature;
        this.id_user = id_user;
        this.id_stage = id_stage;
    }

    public CandidatureStage(int id_user, int id_stage) {
        this.id_user = id_user;
        this.id_stage = id_stage;
    }

    public int getId_candidature() {
        return id_candidature;
    }

    public void setId_candidature(int id_candidature) {
        this.id_candidature = id_candidature;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_stage() {
        return id_stage;
    }

    public void setId_stage(int id_stage) {
        this.id_stage = id_stage;
    }

    @Override
    public String toString() {
        return "CandidatureStage{" + "id_candidature=" + id_candidature + ", id_user=" + id_user + ", id_stage=" + id_stage + '}';
    }



}
