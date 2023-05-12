/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import entities.Formation;
import entities.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Andrew
 */
public class ServiceTask {

    ArrayList<Task> tasks;
    ArrayList<Formation> formations;
    ConnectionRequest req;

    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ServiceTask instance = null;

    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    //1 rendre le constructeur private
    private ServiceTask() {
        req = new ConnectionRequest();
    }

    //3 la methode qu'elle va ramplacer le constructeur 
    public static ServiceTask getinstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

 
 
       public boolean addFormation(Formation f) {
        String name = f.getNom_formation();
        String desc = f.getDescription();
        int prix = f.getPrix();
        int duree = f.getDuree();

        String url = Statics.URL_NEW + "?nomFormation=" + name + "&description=" + desc + "&prix=" + prix + "&duree=" + duree;

        req.setUrl(url);
        //GET =>
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //si le code return 200 
                //
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }
       
          public boolean UpdateFormation(Formation f) {
        String name = f.getNom_formation();
        String desc = f.getDescription();
        int prix = f.getPrix();
        int duree = f.getDuree();

        String url = Statics.URL_UPDATE + f.getId_formation()+ "?nomFormation=" + name + "&description=" + desc + "&prix=" + prix + "&duree=" + duree;

        req.setUrl(url);
        //GET =>
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //si le code return 200 
                //
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

    public boolean DelFormation(Formation f) {
  

        String url = Statics.URL_DELETE+f.getId_formation();

        req.setUrl(url);
        //GET =>
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //si le code return 200 
                //
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

    public ArrayList<Formation> parseFormations(String jsonText) {
        try {
            formations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> formationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) formationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Formation f = new Formation();
                int id = (int) Float.parseFloat(obj.get("idFormation").toString());
                int idFormateur = (int) Float.parseFloat(obj.get("idFormateur").toString());
                int prix = (int) Float.parseFloat(obj.get("prix").toString());
                int duree = (int) Float.parseFloat(obj.get("duree").toString());
                float rating = Float.parseFloat(obj.get("rating").toString());
                String nom = obj.get("nomFormation").toString();
                String desc = obj.get("description").toString();
                String nomFormateur = obj.get("nomFormateur").toString();

                f.setId_formation(id);
                f.setRating(rating);
                f.setNom_formation(nom);
                f.setNom_formateur(nomFormateur);
                f.setId_formateur(idFormateur);
                f.setDescription(desc);
                f.setPrix(prix);
                f.setDuree(duree);
                formations.add(f);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return formations;
    }

    //methode d'affichage
    public ArrayList<Formation> getAllFormations() {
        String url = Statics.BASE_URL;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                formations = parseFormations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return formations;
    }

}
