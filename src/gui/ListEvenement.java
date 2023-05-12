package gui;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import entities.*;
import services.ServiceEvenement;
import java.util.*;

/**
 *
 * @author Mayla Bouzakoura
 */
public class ListEvenement extends Form {

    Form previous;

    public static Evenement currentV = null;
    Button addBtn;
    TextField searchField;

    public ListEvenement(Form previous) {

        this.previous = previous;
        setTitle("List evenement");
        setLayout(BoxLayout.y());

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();

        this.refreshTheme();
    }

    private void addGUIs() {
        searchField = new TextField("", "Search by description");
        searchField.addDataChangeListener((type, index) -> {
            refresh();
        });
        this.add(searchField);

        ArrayList<Evenement> listEvenement = ServiceEvenement.getInstance().getAll();
        if (listEvenement.size() > 0) {
            String searchText = searchField.getText();
            for (Evenement v : listEvenement) {
                if (searchText.isEmpty() || v.getDescriptionEvenement().toLowerCase().contains(searchText.toLowerCase())) {
                    Component model = makeevenementModel(v);
                    this.add(model);
                }
            }
        } else {
            this.add(new Label("No data"));
        }
    }

    Label nom, description;

    private Container makeModelWithoutButtons(Evenement v) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("containerRounded");

        nom = new Label("Nom: " + v.getNomEvenement());
        nom.setUIID("labelDefault");
        
        description = new Label("Description: " + v.getDescriptionEvenement());
        description.setUIID("labelDefault");

        evenementModel.addAll(
                nom, description
        );

        return evenementModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeevenementModel(Evenement v) {

        Container evenementModel = makeModelWithoutButtons(v);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Update");
        editBtn.setUIID("buttonWhiteCenter");

        editBtn.addActionListener(action -> {
            currentV = v;

        });

        deleteBtn = new Button("Delete");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirm Deletion");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Do you really want to delete this event?"));
            Button btnClose = new Button("Cancel");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirm");

            btnConfirm.addActionListener(actionConf -> {

                ServiceEvenement.getInstance().delete(v.getIdEvenement());
                new ListEvenement(previous).show();
            });

            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        evenementModel.add(btnsContainer);

        return evenementModel;
    }
}