package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.about;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;

/**
 * About page.
 *
 * @author Jan S. Hansen
 */
public final class About extends BasePage {

    public About() {
        add(new MenuBorder("mainNavigation"));
    }
}
