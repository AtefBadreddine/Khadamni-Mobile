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
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.ui.table.TableLayout;
import entities.Reclamation;

import java.util.ArrayList;
import services.ReclamationService;

/**
 *
 * @author Atef
 */
public class ListReclamations extends BaseForm {

    public ListReclamations(Form previous) {
        setTitle("Liste reclamations");
        setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

        ArrayList<Reclamation> Reclamations = ReclamationService.getinstance().getAllReclamations();

        TableLayout tl = new TableLayout(2, 2);
        tl.setGrowHorizontally(true);
        setLayout(tl);

        add(new Label("Nom Reclamation")).getAllStyles().setPadding(0, 0, 10, 0);
        add(new Label("Actions")).getAllStyles().setPadding(0, 0, 10, 0);

        for (Reclamation f : Reclamations) {
            String description = f.getDescription();
            if (description.length() > 20) {
                description = description.substring(0, 20) + "...";
            }
            add(new Label(description)).getAllStyles().setPadding(0, 0, 10, 0);
            Button showButton = new Button("Voir");
            showButton.addActionListener(e -> {
                new ConsulterReclamation(this, f).show();
            });
            add(showButton).getAllStyles().setPadding(0, 0, 10, 0);

        }
        Button showButton = new Button("Ajouter Reclamation");
        showButton.addActionListener(e -> {
            new AjouterReclamation(this).show();
        });
        add(showButton);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.showBack());

    }

}
