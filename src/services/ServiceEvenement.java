/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Evenement;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mayla Bouzakoura
 */
public class ServiceEvenement {

    public ArrayList<Evenement> evenement;

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req, cr;
    public int resultCode;

    private ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public boolean addTask(Evenement p) {
        String url = Statics.Url_Signin + "/addEvenementJSON/new?description=" + p.getDescriptionEvenement()
                + "&nom=" + p.getNomEvenement();

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

    public ArrayList<Evenement> getAll() {
        evenement = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.Url_Signin + "/evenementmobile/list");
        System.out.println("url = " + cr);
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    evenement = (ArrayList<Evenement>) getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return evenement;
    }

    private ArrayList<Evenement> getList() {
        JSONParser jsonp = new JSONParser();
        ArrayList<Evenement> ListEvenement = new ArrayList<>();

        try {
            Map<String, Object> parsedJson = jsonp.parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));

            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Evenement p = new Evenement();

                String description = (String) obj.get("descriptionevenement");
                String nom = (String) obj.get("nomevenement");

//            p.setIdEvenement(id);
                p.setDescriptionEvenement(description);
                p.setNomEvenement(nom);

                ListEvenement.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ListEvenement;
    }

    public int delete(int id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.Url_Signin + "/deleteportfolioJSON/" + id);
        System.out.println("url delete : " + cr);
        cr.setHttpMethod("POST");
        // cr.addArgument("id", String.valueOf(id));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }

    public int edit(Evenement v) {
        return manage(v);
    }

    public int manage(Evenement v) {

        cr = new ConnectionRequest();

        cr.setHttpMethod("GET");
        cr.setUrl(Statics.Url_Signin + "//?id=");

        cr.addArgument("id", String.valueOf(v.getIdEvenement()));
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

}
