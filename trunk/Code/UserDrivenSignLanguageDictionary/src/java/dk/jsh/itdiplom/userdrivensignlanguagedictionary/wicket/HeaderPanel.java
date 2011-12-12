package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;           

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.ApplicationUser;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 * Header panel.
 * 
 * @author Jan S. Hansen
 */
public class HeaderPanel extends Panel {

    /**
     * Constructor
     * 
     * @param appUser Application user
     * @param exampleTitle title of the example
     */
    public HeaderPanel(String componentName, ApplicationUser appUser)
    {
        super(componentName);
        StringBuilder text = new StringBuilder("Bruger: ");
        if (appUser != null) {
            text.append(appUser.getFullname());
            text.append(" - ");
            text.append(appUser.getEmail());
        }
        else {
            text.append("Ikke logget på");
        }
        add(new Label("userName", text.toString()));
    }
}