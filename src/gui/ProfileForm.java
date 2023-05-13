/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import gui.SessionManager;
import services.UserService;
/**
 * The user profile form
 *
 * @author Shai Almog
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ProfileForm extends BaseForm {
 
    public ProfileForm(Form previous) {
      


setTitle("Mon profile");
        setLayout(BoxLayout.y());
        
TextField nom = new TextField(SessionManager.getNom(), "nom", 20, TextField.ANY);
        nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);
       
        TextField prenom = new TextField(SessionManager.getPrenom(), "prenom", 20, TextField.ANY);
        prenom.setUIID("TextFieldBlack");
        addStringValue("prenom", prenom);
        
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        UserService uService= new UserService();
        addStringValue("Password", password);
Button editBtn = new Button("Modifier");
editBtn.addActionListener(e -> {
    System.out.println(nom.getText());
    boolean success = uService.updateUser(nom, prenom, password);
    if(success) {
        Dialog.show("Succès", "Vos informations ont été mises à jour avec succès", "OK", null);
        // Actualiser les informations de session
        SessionManager.setNom(nom.getText());
        SessionManager.setPrenom(prenom.getText());
        SessionManager.setMdp(password.getText());
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de la mise à jour des informations", "OK", null);
    }
});
add(FlowLayout.encloseCenter(editBtn));
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> previous.showBack());
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        
    }
}
