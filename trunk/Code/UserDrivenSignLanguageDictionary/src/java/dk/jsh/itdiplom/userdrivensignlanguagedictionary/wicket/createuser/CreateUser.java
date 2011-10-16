package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.createuser;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import org.apache.wicket.PageParameters;

/**
 * Create user page.
 * 
 * @author Jan S. Hansen
 */
public final class CreateUser extends BasePage {

    public CreateUser() {
        add(new MenuBorder("mainNavigation"));
    }
    
    public CreateUser(PageParameters params) {
        //TODO:  process page parameters
    }
}