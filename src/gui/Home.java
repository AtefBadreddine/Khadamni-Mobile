/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Andrew
 */
public class Home extends BaseForm {
    public Home(){
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option "));
        Button b1 = new Button("Ajouter formation");
        Button b2 = new Button("Afficher les formations");
        
        b1.addActionListener(l -> new AjouterFormation(this).show());
        b2.addActionListener(l -> new ListFormations(this).show());
        addAll(b1,b2);
                
    }
    
}
