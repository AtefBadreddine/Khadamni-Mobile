/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Mayla Bouzakoura
 */
public class HomeForm extends Form {

    public HomeForm(Form previous) {

        setTitle("Home");
        setLayout(BoxLayout.y());
        Button btnAddTask = new Button("Add new event");
        Button btnListTasks = new Button("List events");

        btnAddTask.addActionListener(e -> new AddEvenementForm(this).show());
        btnListTasks.addActionListener(e -> new ListEvenement(this).show());
        addAll(btnAddTask, btnListTasks);
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.showBack());

    }

}
