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

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.User;
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


public class forgotpassword extends BaseForm {
 public forgotpassword() {
        
         this(com.codename1.ui.util.Resources.getGlobalResources());
         
    }
    public forgotpassword(Resources res) {
      


setTitle("forget password");
        setLayout(BoxLayout.y());
        
     TextField tfId = new TextField("", "enter your e-mail");
        
       
        Button suppCbtn = new Button("Send");
        
   suppCbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((tfId.getText().length()==0 ))
               Dialog.show("Alert", "enter you email", new Command("OK"));
           else
           {
                     UserService sc = new UserService();
                     User c =new User();
                     c.setEmail(tfId.getText());
                            sc.SendReset(c);   
            }
               
               
              
                  });
                    addAll(tfId,suppCbtn);
                    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e -> new SignInForm(res).show());
}
    
    
}