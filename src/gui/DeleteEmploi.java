/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import entities.Emploi;
import java.util.ArrayList;
import services.EmploiService;

/**
 *
 * @author dhiaa
 */
public class DeleteEmploi extends BaseForm  {
    
   
    private TextField idEmpField;
    private Button deleteButton;
     private ConnectionRequest req;
     public ArrayList<Emploi> emplois;
     public static EmploiService instance = null;
       public boolean resultOK;
    public DeleteEmploi () {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public DeleteEmploi (com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
         this.req = new ConnectionRequest();
    }
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new BorderLayout());
        
        // Title label
        Label titleLabel = new Label("Delete Emploi");
        titleLabel.getAllStyles().setFgColor(0x000000);
        titleLabel.getAllStyles().setPadding(0, 0, 3, 0);
        addComponent(BorderLayout.NORTH, titleLabel);
        
        // ID field
        Label idEmpLabel = new Label("ID Emploi:");
        idEmpLabel.getAllStyles().setFgColor(0x333333);
        addComponent(BorderLayout.WEST, idEmpLabel);
        
        idEmpField = new TextField("", "Enter ID here");
        addComponent(BorderLayout.CENTER, idEmpField);
        
        // Delete button
        deleteButton = new Button("Delete Emploi");
        deleteButton.addActionListener(e -> {
            boolean success = deleteGalerie(idEmpField.getText());
            if (success) {
                Dialog.show("Success", "Emploi deleted successfully", "OK", null);
                idEmpField.setText("");
            } else {
                Dialog.show("Error", "Error deleting emploi", "OK", null);
            }
        });
        addComponent(BorderLayout.SOUTH, deleteButton);
    }
    
    
      public boolean deleteGalerie(String idEmp) {    
        // http://127.0.0.1:8000/emploi/20
        String url ="http://127.0.0.1:8000/emploi/" + idEmp ;
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
}

    

