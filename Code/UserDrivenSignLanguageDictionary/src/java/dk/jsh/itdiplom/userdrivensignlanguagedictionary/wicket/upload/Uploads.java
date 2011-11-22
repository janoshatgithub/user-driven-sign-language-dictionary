package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.upload;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;


/**
 * My uploads page.
 * 
 * @author Jan S. Hansen
 */
public final class Uploads extends BasePage {

    public Uploads() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
    }
}