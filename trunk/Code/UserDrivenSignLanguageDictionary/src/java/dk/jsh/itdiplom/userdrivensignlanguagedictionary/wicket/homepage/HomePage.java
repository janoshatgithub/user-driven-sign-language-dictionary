package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;

/**
 * Home/search page.
 * 
 * @author Jan S. Hansen
 */
public class HomePage extends BasePage {
    public HomePage() {
        add(new MenuBorder("mainNavigation"));
    }
}