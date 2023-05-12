/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Formation;
import services.EmploiService;
import services.ServiceTask;

/**
 *
 * @author Atef
 */
public class AddEmploi2 extends BaseForm {

    String[] niveauExperience = {"bac+3", "bac+4", "bac+5"};
    private ComboBox<String> choixNiveauExperience = new ComboBox<>((Object[]) niveauExperience);

// Create the ChoiceBox for type_contrat
    String[] typeContrat = {"temps plein", "temps partiel", "contractuel"};
    private ComboBox<String> choixTypeContrat = new ComboBox<>((Object[]) typeContrat);

    public AddEmploi2(Form previous) {

        setTitle("Ajouter Emploi");
        setLayout(BoxLayout.y());

        TextField tfNomFormation = new TextField("", "Titre");
        TextField tfDescription = new TextField("", "Description");
        TextField tfPrix = new TextField("", "Salaire");

        Button btnAddFormation = new Button("Ajouter Emploi");

        btnAddFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (tfNomFormation.getText().length() < 4 || tfDescription.getText().length() < 4
                        || tfPrix.getText().length() == 0) {

                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    int prix = 0;
                    try {

                        prix = Integer.parseInt(tfPrix.getText());
                    } catch (NumberFormatException ex) {
                        Dialog.show("Alert", "Please enter a valid integer for Salaire", "OK", null);
                        return;
                    }
                    String Nom = tfNomFormation.getText();
                    String Description = tfDescription.getText();
                    String niv = choixNiveauExperience.getSelectedItem();
                    String type = choixTypeContrat.getSelectedItem();
                    
                    if (EmploiService.getInstance().addEmploi(Nom, Description, prix, niv, type)) {
                        Dialog.show("Success", "Emploi Ajouter", new Command("OK"));
                        new ListEmploi().show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));

                    }

                }
            }
        });

        addAll(tfNomFormation, tfDescription, choixNiveauExperience, choixTypeContrat, tfPrix, btnAddFormation);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> previous.showBack());
    }
}
