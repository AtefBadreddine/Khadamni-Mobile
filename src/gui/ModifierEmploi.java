/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Emploi;
import gui.AddEmploi.EventCallbackClass;
import services.EmploiService;

/**
 *
 * @author dhiaa
 */
public class ModifierEmploi extends BaseForm {
    
    Emploi e = new Emploi();

     public ModifierEmploi(Form previous, Emploi f) {
        setTitle("Modifier Emploi");
        setLayout(BoxLayout.y());
         System.out.println(f);
        TextField tfNomFormation = new TextField(f.getTitre(), "Nom Emploi");
        TextField tfDescription = new TextField(f.getDescription(), "Description");
        String prix2 = String.valueOf(f.getSalaire());
        TextField tfPrix = new TextField(prix2, "Prix");
           
        Button btnAddFormation = new Button("Modifier");

        btnAddFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (tfNomFormation.getText().length() < 4 || tfDescription.getText().length() < 5
                      ) {

                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    int  prix = 0;
                    try {
                         prix = Integer.parseInt(tfPrix.getText());
                    }
                    catch (Exception e) {
                        Dialog.show("Error", "salaire doit etre entier", "OK", null);
                    }
                    
                    
                    f.setTitre(tfNomFormation.getText());
                    f.setDescription(tfDescription.getText());
                    
                    f.setSalaire(prix);
                    
                    if (EmploiService.getInstance().modifierEmploi(f) ){
                        Dialog.show("Success", "Emploi updated successfully", "OK", null);
                         
                          new ListEmploi().show();
                    } else {
                        Dialog.show("Error", "Error while connecting to server", "OK", null);
                    }
                }
            }
        });
         addAll(tfNomFormation, tfDescription, tfPrix, btnAddFormation);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> previous.showBack());
    }
}
