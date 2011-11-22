package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;

/**
 * All request page.
 * 
 * @author Jan S. Hansen
 */
public final class AllRequest extends BasePage {

    public AllRequest() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
    }   
}