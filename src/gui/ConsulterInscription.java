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
import java.util.ArrayList;
import services.ServiceTask;
import utils.Statics;

/**
 *
 * @author Atef
 */
public class ConsulterInscription extends BaseForm {

    public ConsulterInscription(Form previous, Formation f) {
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
                        new Label("MOY Rating: "),
                        new Label(f.getRating() == 0f ? "Aucune évaluation" : String.valueOf(f.getRating()))
                ),
                FlowLayout.encloseCenter(
                        new Label("Votre note: "),
                        new Label(f.getUserRating() == 0f ? "Aucune note attribuée" : String.valueOf(f.getUserRating()))
                )
        );
        for (Component cmp : content) {
            cmp.getAllStyles().setAlignment(Component.CENTER);
        }
        content.setScrollableY(true);
        content.setScrollVisible(false);

        StarRating sr = new StarRating();
        Slider starRank = sr.createStarRankSlider();
        TextField ratingTF = new TextField("", "Rating");
        ratingTF.setEditable(false);
        starRank.addActionListener(evt -> ratingTF.setText(String.valueOf(starRank.getProgress())));

        Button addBtn = new Button("Ajouter Avis");
        Button delBtn = new Button("Annuler");

        addBtn.addActionListener(evt -> {
            String note = ratingTF.getText();
            try {
                   int r = Integer.parseInt(note);
                   if (r == 0 || note.length() == 0)  {
                       Dialog.show("Alert", "Rating doit etre different à 0", "ok", null);
                       return ;
                   }
            }
            catch (Exception e) {
                 Dialog.show("Alert", "Veuillez selectionnez une note", "ok", null);
                 return ;
            }
         
        
         
                if (ServiceTask.getinstance().addRating(f, note)) {
                Dialog.show("Alert", "Rating ajouté", "ok", null);
              
              
                 Formation updated = ServiceTask.getinstance().getFormation(f.getId_formation());
      
                new ConsulterInscription(previous, updated).show();

            } else {
                Dialog.show("Alert", "Err while connecting to server ", "ok", null);
            }
            
          
        });

        delBtn.addActionListener(evt -> {
                if (ServiceTask.getinstance().annulerInscription(f)) {
                Dialog.show("Alert", "Inscription annulée avec succées", "ok", null);
              
              
                 ListEmploi le = new ListEmploi();
                 ListFormations lf = new ListFormations(le);
                 
                 new MesInscriptions(lf).show();
      
              

            } else {
                Dialog.show("Alert", "Err while connecting to server ", "ok", null);
            }
        });

        Container actions = FlowLayout.encloseCenter(addBtn, delBtn);

        addComponent(BorderLayout.CENTER, content);
        addComponent(BorderLayout.SOUTH, BoxLayout.encloseY(
                FlowLayout.encloseCenterMiddle(starRank, ratingTF),
                actions
        ));

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());
    }
}
