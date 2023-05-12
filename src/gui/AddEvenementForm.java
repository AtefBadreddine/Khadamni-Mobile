package gui;

import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Evenement;
import services.ServiceEvenement;

/**
 *
 * @author Mayla Bouzakoura
 */
public class AddEvenementForm extends Form {

    public AddEvenementForm(Form previous) {
        setTitle("Add a new event");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField("", "Nom d'évenement");
        TextField tfdescription = new TextField("", "Description d'évenement");
        Button btnValider = new Button("Add event");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String nom = tfnom.getText();
                String desc = tfdescription.getText();

                Evenement p = new Evenement(nom, desc);

                if (ServiceEvenement.getInstance().addTask(p)) {
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });

        addAll(tfnom, tfdescription, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
