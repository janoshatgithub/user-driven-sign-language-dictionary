package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.createuser;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;

/**
 * User created page.
 * 
 * @author Jan S. Hansen
 */
public final class UserCreated extends BasePage {

    public UserCreated() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
    }
}