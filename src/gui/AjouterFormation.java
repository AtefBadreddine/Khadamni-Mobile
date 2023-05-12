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
import services.ServiceTask;


/**
 *
 * @author Atef
 */
public class AjouterFormation extends BaseForm{

    public AjouterFormation(Form previous) {

        setTitle("Ajouter Formation");
        setLayout(BoxLayout.y());

        TextField tfNomFormation = new TextField("", "Nom Formation");
        TextField tfDescription = new TextField("", "Description");
        TextField tfDuree = new TextField("", "Durée");
        TextField tfPrix = new TextField("", "Prix");

        Button btnAddFormation = new Button("Ajouter Formation");

        btnAddFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (tfNomFormation.getText().length() < 4 || tfDescription.getText().length() < 5
                        || tfDuree.getText().length() == 0 || tfPrix.getText().length() == 0) {

                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    int duree, prix;
                    try {
                        duree = Integer.parseInt(tfDuree.getText());
                        prix = Integer.parseInt(tfPrix.getText());
                    } catch (NumberFormatException ex) {
                        Dialog.show("Alert", "Please enter a valid integer for Duree and Prix", "OK", null);
                        return;
                    }

                    Formation formation = new Formation(0, tfNomFormation.getText(), tfDescription.getText(),
                            duree, prix);
                    if (ServiceTask.getinstance().addFormation(formation)) {
                        Dialog.show("Success", "Formation added successfully", "OK", null);
                       ListEmploi le = new ListEmploi();
                        new ListFormations(le).showBack();
                        
                    } else {
                        Dialog.show("Error", "Error while connecting to server", "OK", null);
                    }
                }
            }
        });

        addAll(tfNomFormation, tfDescription, tfDuree, tfPrix, btnAddFormation);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> previous.showBack());
    }
}
