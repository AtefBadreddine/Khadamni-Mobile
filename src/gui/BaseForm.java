package gui;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

public class BaseForm extends Form {
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image galeriesImage = null;
        if(isCurrentInbox()) galeriesImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        Button emploiButton = new Button("Liste Emploi", galeriesImage);
        emploiButton.setUIID("SideCommand");
        emploiButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(emploiButton);//, new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(emploiButton);
        inbox.setUIID("SideCommand");
        emploiButton.addActionListener(e -> new ListEmploi().show());
        getToolbar().addComponentToSideMenu(inbox);
        

        
        getToolbar().addCommandToSideMenu("Liste Stage", calendarImage, e -> new ListStage(res).show());
        getToolbar().addCommandToSideMenu("Liste Formation", null, e -> new ListFormations(this).show());
         getToolbar().addCommandToSideMenu("Liste Reclamation", null, e -> new ListReclamations(this).show());
        
              getToolbar().addCommandToSideMenu("Liste Evenement", null, e -> new gui.HomeForm(this).show());
//        getToolbar().addCommandToSideMenu("Reclamation", trendingImage, e -> new TrendingForm(res).show());
        getToolbar().addCommandToSideMenu("Settings", null, e -> {});
        
        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Detra Mcmunn", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Long Beach, CA", "SideCommandSmall"));
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}