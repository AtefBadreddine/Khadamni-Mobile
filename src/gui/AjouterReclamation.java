/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Formation;
import services.ReclamationService;
import services.ServiceTask;


/**
 *
 * @author Atef
 */
public class AjouterReclamation extends BaseForm{

    public AjouterReclamation(Form previous) {

        setTitle("Ajouter Reclamation");
        setLayout(BoxLayout.y());

    
        TextField tfDescription = new TextField("", "Description");
        TextField tfDuree = new TextField("", "type");
       

        Button btnAddFormation = new Button("Ajouter");

        btnAddFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (  tfDescription.getText().length() < 5
                        || tfDuree.getText().length() < 4 ) {

                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    String desc = tfDescription.getText();
                    String type = tfDuree.getText();
                        
                  
                    if (ReclamationService.getinstance().addReclamation(desc, type)) {
                        Dialog.show("Success", "Reclamation added successfully", "OK", null);
                        ListEmploi le = new ListEmploi();
                        new ListReclamations(le).showBack();
                    } else {
                        Dialog.show("Error", "Error while connecting to server", "OK", null);
                    }
                }
            }
        });

        addAll( tfDescription, tfDuree, btnAddFormation);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> previous.showBack());
    }
}
