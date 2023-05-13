package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableLayout;
import entities.Formation;
import entities.Reclamation;
import services.ReclamationService;

public class ConsulterReclamation extends BaseForm {

    public ConsulterReclamation(Form previous, Reclamation f) {
        setTitle("Consulter");
        setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

        Container content = BoxLayout.encloseY(
                FlowLayout.encloseCenter(
                        new Label("Type Reclamation: "),
                        new Label(f.getType(), "FormTitle")
                ),
                FlowLayout.encloseCenter(
                        new Label("Description: "),
                        new TextArea(f.getDescription(), 5, 20, TextArea.UNEDITABLE | TextArea.BOTTOM)
                )
        );
        for (Component cmp : content) {
            cmp.getAllStyles().setAlignment(Component.CENTER);
        }

        content.setScrollableY(true);
        content.setScrollVisible(false);

        Button delBtn = new Button("Supprimer");

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (ReclamationService.getinstance().DelReclamation(f)) {
                    Dialog.show("Alert", "deleted successfuly", "ok", null);
                    ListEmploi h = new ListEmploi(com.codename1.ui.util.Resources.getGlobalResources());
                    new ListReclamations(h).showBack();

                } else {
                    Dialog.show("Alert", "Err while connecting to server ", "ok", null);
                }

            }
        });

        Container actions = FlowLayout.encloseCenter(delBtn);

        addComponent(BorderLayout.CENTER, content);
        addComponent(BorderLayout.SOUTH, BoxLayout.encloseY(
                actions
        ));

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());
    }

}
