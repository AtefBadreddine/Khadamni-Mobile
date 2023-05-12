package gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import entities.Emploi;
import services.StageService;

public class AddStage extends Form {

   

    public AddStage(Form previous) {
        setTitle("Ajouter Stage");

        TextField tfposte = new TextField("", "Poste");
        TextField tfDescription = new TextField("", "nom entreprise");
        TextField tfadr = new TextField("", "adresse");
        TextField tfduree = new TextField("", "duree");
        TextField tftype = new TextField("", "type");
       

        Button btnAddFormation = new Button("Ajouter Stage");
        btnAddFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfposte.getText().length() < 4 || tfDescription.getText().length() < 4
                        || tftype.getText().length() == 0) {

                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                   
                    String post = tfposte.getText();
                    String Description = tfDescription.getText();
                    String type = tftype.getText();
                    String Adresse = tfadr.getText();
                    String duree = tfduree.getText();
                            
                   

                    if (StageService.getInstance().addStage(post, Description, Adresse, duree,type)) {
                        Dialog.show("Success", "Stage added successfully", "OK", null);
                       new ListStage().show();
                        
                    } else {
                        Dialog.show("Error", "Something went wrong. Please try again later.", "OK", null);
                    }
                }
            }
        });

        addAll(tfposte, tfDescription, tftype, tfadr,tfduree, btnAddFormation);
        getToolbar().addMaterialCommandToLeftBar("", com.codename1.ui.FontImage.MATERIAL_ARROW_BACK, ev -> previous.showBack());
    }
}