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
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import entities.Formation;
import services.ServiceTask;


/**
 *
 * @author Atef
 */
public class ConsulterFormation extends BaseForm{
   

    public ConsulterFormation(Form previous,Formation f) {
        setTitle("Consulter");
        setLayout(new TableLayout(2,2));
         
           add(new Label("Nom Formation"));
           add(new Label(f.getNom_formation()));
           
                
           add(new Label("Nom Formateur"));
           add(new Label(f.getNom_formateur()));
           
                
           add(new Label("Description"));
           add(new Label(f.getDescription()));
           
                
           add(new Label("Duree"));
           String duree = String.valueOf(f.getDuree()) +" semaines";
           add(new Label(duree));
           
                
           add(new Label("Prix"));
           String prix = String.valueOf(f.getPrix()) +" TND";
           add(new Label(prix));
           
           String rating = "";     
           add(new Label("Rating"));
           if(f.getRating() == 0f) 
               rating = "aucune évaluation";
           else rating = String.valueOf(f.getRating());
           add(new Label(rating));
           
          Button EditBtn = new Button("Modifier");
          Button DelBtn = new Button("Supprimer");
          
            EditBtn.addActionListener(l -> new ModifierFormation(this,f).show());
          DelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                   
                    if(ServiceTask.getinstance().DelFormation(f)){
                         Dialog.show("Alert","deleted successfuly","ok",null);
                         ListEmploi h = new ListEmploi(com.codename1.ui.util.Resources.getGlobalResources());
                         new ListFormations(h).showBack();
                        
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                
            }
        });
          
          
          add(EditBtn);
          add(DelBtn);
        
        
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.showBack());
    }
        
    
}
