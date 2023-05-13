/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import entities.Formation;
import services.ServiceTask;


/**
 *
 * @author Atef
 */
public class ConsulterFormation extends BaseForm{
   

    public ConsulterFormation(Form previous,Formation f) {
        
        
        setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setTitle("Consulter");

      Container content = BoxLayout.encloseY(
        FlowLayout.encloseCenter(
                new Label("Nom de la formation: "),
                new Label(f.getNom_formation(), "FormTitle")
        ),
        FlowLayout.encloseCenter(
                new Label("Nom du formateur: "),
                new Label(f.getNom_formateur(), "FormSubTitle")
        ),
        FlowLayout.encloseCenter(
                new Label("Description: "),
                new Label(f.getDescription())
        ),
        FlowLayout.encloseCenter(
                new Label("Durée: "),
                new Label(String.valueOf(f.getDuree()) + " semaines")
        ),
        FlowLayout.encloseCenter(
                new Label("Prix: "),
                new Label(String.valueOf(f.getPrix()) + " TND")
        ),
        FlowLayout.encloseCenter(
                new Label("Rating: "),
                new Label(f.getRating() == 0f ? "Aucune évaluation" : String.valueOf(f.getRating()))
        )
);
      for (Component cmp : content) {
    cmp.getAllStyles().setAlignment(Component.CENTER);
}

        content.setScrollableY(true);
        content.setScrollVisible(false);

       

         Button editBtn = new Button("Modifier");
          Button delBtn = new Button("Supprimer");
          
            editBtn.addActionListener(l -> new ModifierFormation(this,f).show());
          delBtn.addActionListener(new ActionListener() {
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
          
        Container actions = FlowLayout.encloseCenter(editBtn, delBtn);

        addComponent(BorderLayout.CENTER, content);
        addComponent(BorderLayout.SOUTH, BoxLayout.encloseY(
                actions
        ));

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());
    }
        
    
}
