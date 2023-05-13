/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;
import entities.Formation;
import java.util.ArrayList;
import services.ServiceTask;
import utils.Statics;

/**
 *
 * @author Atef
 */
public class MesInscriptions extends BaseForm {

    public MesInscriptions(Form previous) {
         setTitle("Mes Inscriptions");
        setLayout(new TableLayout(2,2));
     int id = SessionManager.getId();
        String url = Statics.URL_USERINSCRIPTIONS + "?id=" + String.valueOf(id);
       ArrayList<Formation> formations = ServiceTask.getinstance().getAllFormations(url);

       add(new Label("nom Formation"));
       add(new Label("Actions"));
       
       for (Formation f : formations) {
           add(new Label(f.getNom_formation()));
             Button showButton = new Button("Consulter");
            showButton.addActionListener(e -> {
                new ConsulterInscription(this, f).show();
            });
            add(showButton);

       }
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.showBack());

    }
    
    }
    
    

