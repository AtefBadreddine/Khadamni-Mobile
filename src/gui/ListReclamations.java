/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;


import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import entities.Formation;
import entities.Reclamation;

import java.util.ArrayList;
import services.ReclamationService;
import services.ServiceTask;

/**
 *
 * @author Atef
 */
public class ListReclamations extends BaseForm{

    
    
    public ListReclamations(Form previous) {
        setTitle("Liste reclamations");
        setLayout(new TableLayout(2,2));

       ArrayList<Reclamation> Reclamations = ReclamationService.getinstance().getAllReclamations();

       add(new Label("nom Reclamation"));
       add(new Label("Actions"));
       
       for (Reclamation f : Reclamations) {
           add(new Label(f.getDescription()));
             Button showButton = new Button("Consulter");
            showButton.addActionListener(e -> {
                new ConsulterReclamation(this, f).show();
            });
            add(showButton);

       }
           Button showButton = new Button("Ajouter Reclamation");
            showButton.addActionListener(e -> {
                new AjouterReclamation(this).show();
            });
            add(showButton);
       

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.showBack());

    }

}

