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
import com.codename1.ui.events.ActionListener;
import entities.Reclamation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Andrew
 */
public class ReclamationService {

   
    ArrayList<Reclamation> reclamations;
    ConnectionRequest req;

    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ReclamationService instance = null;

    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    //1 rendre le constructeur private
    private ReclamationService() {
        req = new ConnectionRequest();
    }

    //3 la methode qu'elle va ramplacer le constructeur 
    public static ReclamationService getinstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }

 
 
       public boolean addReclamation(String desc,String type) {
        
       
        String url = Statics.URL_REC + "new?description=" + desc + "&typeReclamation=" + type;

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
  

    public boolean DelReclamation(Reclamation f) {
  

        String url = Statics.URL_REC+"delete?id=" +f.getIdReclamation();

        req.setUrl(url);
        //GET =>
        req.setPost(true);
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

    public ArrayList<Reclamation> parseReclamations(String jsonText) {
        try {
            
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> formationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) formationsListJson.get("root");
           
            for (Map<String, Object> obj : list) {
                
                Reclamation f = new Reclamation();
                int id = (int) Float.parseFloat(obj.get("idReclamation").toString());
                
              
                
                String desc = obj.get("description").toString();
                String type= obj.get("typeReclamation").toString();
                    
               f.setIdReclamation(id);
              
                f.setDescription(desc);
                f.setType(type);
             
                reclamations.add(f);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    //methode d'affichage
    public ArrayList<Reclamation> getAllReclamations() {
        String url = Statics.URL_REC+"afficher";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return reclamations;
    }

}
