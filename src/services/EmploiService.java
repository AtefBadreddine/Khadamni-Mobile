package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Emploi;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class EmploiService {
     private ConnectionRequest req;
     public ArrayList<Emploi> emplois;
     public static EmploiService instance = null;
       public boolean resultOK;
     
     private EmploiService() {
         req = new ConnectionRequest();
     }
     
     public static EmploiService getInstance() {
        if (instance == null) {
            instance = new EmploiService();
        }
        return instance;
    }
     
  public ArrayList<Emploi> getAllEmploi() {
    String url = "http://127.0.0.1:8000/emp/json";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            try {
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                emplois = new ArrayList<>();
                ArrayList<Map<String, Object>> emploisData = (ArrayList<Map<String, Object>>) response.get("root");
                for (Map<String, Object> emploiData : emploisData) {
                    
                   int id = (int) Float.parseFloat(emploiData.get("idEmploi").toString());
                     int salaire = (int) Float.parseFloat(emploiData.get("salaire").toString());
                    Emploi emploi = new Emploi();
                    emploi.setNiveau_experience((String) emploiData.get("niveauExperience"));
                    emploi.setType_contrat((String) emploiData.get("typeContrat"));
                    emploi.setIdemploi(id);
                    emploi.setDescription((String) emploiData.get("description"));
                    emploi.setTitre((String) emploiData.get("titre"));
                    emploi.setSalaire(salaire);
                    emplois.add(emploi);
                   
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return emplois;
}
  
  
 public boolean addEmploi(String Nom, String Description,int salaire,String niv,String type) {

 String url = "http://127.0.0.1:8000/emp/json/new?description=" + Nom + "&titre=" + Description + "&salaire=" + salaire+ "&niv="+niv+"&type="+type;


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
 
 
  public boolean deleteEmploi(String idEmp) {    
    
        String url = "http://127.0.0.1:8000/emp/json/"+idEmp ;
        System.out.println(url);
        req.setUrl(url);
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
  
    public boolean modifierEmploi(Emploi e) {    
        
       String url="http://127.0.0.1:8000/emp/json/edit/"+ e.getIdemploi() + "?description=" + e.getDescription() + "&titre=" + e.getTitre() + "&salaire=" + e.getSalaire();
      
       
        req.setUrl(url);
        req.setPost(true);
        
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
    public boolean UpdateEmploi(Emploi f) {
    boolean success = false;

    String url = "http://127.0.0.1:8000/emp/json/edit/" + f.getIdemploi()+
            "?titre=" + f.getTitre() +
            "&description=" + f.getDescription() +
            "&salaire=" + f.getSalaire();
    ConnectionRequest request = new ConnectionRequest(url, false);
    NetworkManager.getInstance().addToQueueAndWait(request);
    String response = new String(request.getResponseData());
    if (response != null && response.equals("Success")) {
        success = true;
    }

    return success;
}


  


}
