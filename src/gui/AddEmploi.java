/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Emploi;
import java.util.ArrayList;
import services.EmploiService;

/**
 *
 * @author dhiaa
 */
public class AddEmploi extends BaseForm {

    private TextField titreField;
    private TextField salaireField;
    private TextField descriptionField;
    private Button addButton;
    private ConnectionRequest req;
    public ArrayList<Emploi> emplois;
    public static EmploiService instance = null;
    public boolean resultOK;

    public AddEmploi() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public AddEmploi(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_couleurHtml = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_Nom = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_Description = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_Photographe = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    //private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
         String[] niveauExperience = {"bac+3", "bac+4", "bac+5"};
    private ComboBox<String> choixNiveauExperience = new ComboBox<>((Object[]) niveauExperience);


// Create the ChoiceBox for type_contrat
        String[] typeContrat = {"temps plein", "temps partiel", "contractuel"};
       private ComboBox<String> choixTypeContrat = new ComboBox<>((Object[])typeContrat);
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {

        private com.codename1.ui.Component cmp;

        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if (sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Ajouter un Emploi");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(false);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        // Create the ChoiceBox for niveau_experience
  



// Add the ChoiceBoxes to the ComponentGroup
        gui_Component_Group_1.addComponent(choixNiveauExperience);
        gui_Component_Group_1.addComponent(choixTypeContrat);
        gui_Component_Group_1.addComponent(gui_Text_Field_Nom);
        gui_Component_Group_1.addComponent(gui_Text_Field_Description);
        gui_Component_Group_1.addComponent(gui_Text_Field_Photographe);

        gui_Text_Field_Nom.setText("Titre");
        gui_Text_Field_Nom.setName("Text_Field_Nom");
        gui_Text_Field_Description.setText("Description");
        gui_Text_Field_Description.setName("Text_Field_Description");
        gui_Text_Field_Photographe.setText("1500");
        gui_Text_Field_Photographe.setName("Text_Field_Photographe");
        gui_Container_1.addComponent(gui_Button_2);
        //gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Ajouter");
        gui_Button_2.setName("Button_Ajouter");
        //gui_Button_3.setText("Forgot Your Password");
        //gui_Button_3.setUIID("CenterLabelSmall");
        //gui_Button_3.setName("Button_3");
        //addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        //gui_Button_1.setText("Create New Account");
        //gui_Button_1.setUIID("CenterLabel");
        //gui_Button_1.setName("Button_1");
    }

    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        if (gui_Text_Field_Nom.getText().length() == 0 || gui_Text_Field_Description.getText().length() == 0
                || gui_Text_Field_Photographe.getText().length() == 0
                || gui_Text_Field_Nom.getText().equals("Titre")
                || gui_Text_Field_Description.getText().equals("Description")
                || gui_Text_Field_Photographe.getText().equals("Salaire")) {
            Dialog.show("Alert", "Veuillez remplir tous les champs");
        } else {
            //gui_Text_Field_couleurHtml gui_Text_Field_Nom gui_Text_Field_Description gui_Text_Field_Photographe

            String Nom = gui_Text_Field_Nom.getText();
            String Description = gui_Text_Field_Description.getText();
            int salaire = Integer.parseInt(gui_Text_Field_Photographe.getText());
            String niv = choixNiveauExperience.getSelectedItem();
            String type = choixTypeContrat.getSelectedItem();

            try {
                if (EmploiService.getInstance().addEmploi(Nom, Description, salaire,niv,type)) {
                    Dialog.show("Success", "Emploi Ajouter", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
            }
            new ListEmploi().show();
        }

    }

}
