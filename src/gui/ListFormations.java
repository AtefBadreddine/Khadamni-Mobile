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

import java.util.ArrayList;
import services.ServiceTask;

/**
 *
 * @author Atef
 */
public class ListFormations extends BaseForm{

    
    
    public ListFormations(Form previous) {
        setTitle("Liste Formations");
        setLayout(new TableLayout(2,2));

       ArrayList<Formation> formations = ServiceTask.getinstance().getAllFormations();

       add(new Label("nom Formation"));
       add(new Label("Actions"));
       
       for (Formation f : formations) {
           add(new Label(f.getNom_formation()));
             Button showButton = new Button("Consulter");
            showButton.addActionListener(e -> {
                new ConsulterFormation(this, f).show();
            });
            add(showButton);

       }
       
          Button showButton = new Button("Ajouter Formation");
            showButton.addActionListener(e -> {
                new AjouterFormation(this).show();
            });
            add(showButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.showBack());

    }

}

