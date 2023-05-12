/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Stages;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author dhiaa
 */
public class StageService {
    private ConnectionRequest req;
    public ArrayList<Stages> stages;
    public static StageService instance = null;
    public boolean resultOK;

    public StageService() {
        req = new ConnectionRequest();
     
    }

    public static StageService getInstance() {
        if (instance == null) {
            instance = new StageService();
        }
        return instance;
    }
    
     public ArrayList<Stages> getAllStage() {
    String url = "http://127.0.0.1:8000/stage/json";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            try {
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                stages = new ArrayList<>();
                ArrayList<Map<String, Object>> emploisData = (ArrayList<Map<String, Object>>) response.get("root");
                for (Map<String, Object> emploiData : emploisData) {
                   
                    Stages stage = new Stages();
                   int id = (int) Float.parseFloat(emploiData.get("idStage").toString());
                  
                    stage.setPoste((String) emploiData.get("poste"));
                    stage.setId_stage(id);
                    stage.setNom_entreprise((String) emploiData.get("nomEntreprise"));
                     stage.setAdresse_stage((String) emploiData.get("adresseStage"));
                      stage.setDuree_stage( (String) emploiData.get("dureeStage"));
                        stage.setType_stage((String) emploiData.get("typeStage"));
                
                    stages.add(stage);
                   
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return  stages;
}
     
      public boolean addStage(String Poste, String Entreprise,String Adresse,String duree,String Type) {

 String url = "http://127.0.0.1:8000/stage/json/new?poste=" + Poste + "&nom_entreprise=" + Entreprise + "&adresse_stage=" + Adresse + "&duree_stage=" +duree+ "&type_stage=" + Type;


    if (url.contains("#")) {
        String part1 = url.substring(0, url.indexOf("#"));
        String part2 = url.substring(url.indexOf("#") + 1, url.length());
        url = part1 + "%23" + part2;
    }

    req = new ConnectionRequest(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return resultOK;
}
}
       
       
    
