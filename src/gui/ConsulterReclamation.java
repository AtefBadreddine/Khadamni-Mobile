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
import entities.Reclamation;
import services.ReclamationService;
import services.ServiceTask;


/**
 *
 * @author Atef
 */
public class ConsulterReclamation extends BaseForm{
   

    public ConsulterReclamation(Form previous,Reclamation f) {
        setTitle("Consulter");
        setLayout(new TableLayout(2,2));
         
           add(new Label("Type Reclamation"));
           add(new Label(f.getType()));
           
               
                
           add(new Label("Description"));
           add(new Label(f.getDescription()));
           
                
       
           
           
         
          Button DelBtn = new Button("Supprimer");
          
          
          DelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                   
                    if(ReclamationService.getinstance().DelReclamation(f)){
                         Dialog.show("Alert","deleted successfuly","ok",null);
                         ListEmploi h = new ListEmploi(com.codename1.ui.util.Resources.getGlobalResources());
                         new ListReclamations(h).showBack();
                        
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                
            }
        });
          
          
     
          add(DelBtn);
        
        
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.showBack());
    }
        
    
}
