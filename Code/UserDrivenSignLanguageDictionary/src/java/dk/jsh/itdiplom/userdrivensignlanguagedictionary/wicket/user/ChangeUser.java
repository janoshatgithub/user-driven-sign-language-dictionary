package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.user;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;

/**
 * Change user.
 * 
 * @author Jan S. Hansen
 */
public final class ChangeUser extends BasePage {

    public ChangeUser() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
    }
}