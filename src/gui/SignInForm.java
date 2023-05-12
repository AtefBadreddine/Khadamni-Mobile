package gui;


import com.codename1.ui.FontImage;
import com.codename1.ui.util.Resources;
import services.UserService;


public class SignInForm extends com.codename1.ui.Form {
private Resources theme;
    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
     
        username.setText("");
        password.setText("");
        //ajout de la croit de fermeture
//        getToolbar().addCommandToLeftBar("", mat, e -> new WalkthruForm().showBack());
        getContentPane().setUIID("SignInForm");
          UserService uService = new UserService();
        signIn.addActionListener(e -> {
             String Username = username.getText();
                String Password = password.getText();
    boolean success = uService.signin(Password,Username);
          
    
    if (success == true) {
        
 new ListEmploi().show();
    }
});
   
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField username = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField password = new com.codename1.ui.TextField();
    private com.codename1.ui.Button signIn = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button signup = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        signIn.addActionListener(callback);
        signup.addActionListener(callback);
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
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }
             if(sourceComponent == signup) {
                onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(password);
        gui_Component_Group_1.addComponent(username);
        password.setText("");
        password.setName("Text_Field_2");
        username.setText("");
        username.setName("Text_Field_1");
        gui_Container_1.addComponent(signIn);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        signIn.setText("Sign In");
        signIn.setName("Button_2");
        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, signup);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        signup.setText("Create New Account");
        signup.setUIID("CenterLabel");
        signup.setName("Button_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
  
 public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
                  new SignUpForm(theme).show();
    }
}
