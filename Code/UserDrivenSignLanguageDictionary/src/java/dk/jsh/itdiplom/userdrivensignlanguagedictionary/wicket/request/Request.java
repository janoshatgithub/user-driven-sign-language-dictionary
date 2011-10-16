package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import org.apache.wicket.PageParameters;

/**
 * Request page.
 * 
 * @author Jan S. Hansen
 */
public final class Request extends BasePage {

    public Request() {
        add(new MenuBorder("mainNavigation"));
    }
    
    public Request(PageParameters params) {
        //TODO:  process page parameters
    }
}