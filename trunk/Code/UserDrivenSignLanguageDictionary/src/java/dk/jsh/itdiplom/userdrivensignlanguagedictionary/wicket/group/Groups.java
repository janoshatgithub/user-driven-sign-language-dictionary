package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.group;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;

/**
 * My groups page.
 * 
 * @author Jan S. Hansen
 */
public final class Groups extends BasePage {

    public Groups() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
    }
}