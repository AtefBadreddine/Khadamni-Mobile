package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import entities.Formation;
import java.util.ArrayList;
import services.ServiceTask;
import utils.Statics;

public class ListFormations extends BaseForm {

    public ListFormations(Form previous) {
        setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setTitle("Liste Formations");

        int id = SessionManager.getId();
        String url = Statics.BASE_URL + "?id=" + id;

        ArrayList<Formation> formations = ServiceTask.getinstance().getAllFormations(url);

        TableLayout tl = new TableLayout(2, 2);
        tl.setGrowHorizontally(true);
        setLayout(tl);

        add(new Label("Nom Formation")).getAllStyles().setPadding(0, 0, 10, 0);
        add(new Label("Actions")).getAllStyles().setPadding(0, 0, 10, 0);

        for (Formation f : formations) {
            add(new Label(f.getNom_formation())).getAllStyles().setPadding(0, 0, 10, 0);
            Button showButton = new Button("Consulter");
            showButton.addActionListener(e -> new ConsulterFormation(this, f).show());
            add(showButton).getAllStyles().setPadding(0, 0, 10, 0);
        }

        Button addBtn = new Button("Ajouter Formation");
        addBtn.addActionListener(e -> new AjouterFormation(this).show());
        add(addBtn).getAllStyles().setMargin(20, 0, 20, 0);

        Button mesInscBtn = new Button("Mes inscriptions");
        mesInscBtn.addActionListener(e -> new MesInscriptions(this).show());
        add(mesInscBtn).getAllStyles().setMargin(0, 0, 20, 0);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> previous.showBack());
    }
}
