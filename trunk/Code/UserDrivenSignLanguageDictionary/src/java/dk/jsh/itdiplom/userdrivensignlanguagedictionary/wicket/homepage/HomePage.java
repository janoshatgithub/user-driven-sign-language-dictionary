package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * HomePage
 * 
 * @author Jan S. Hansen
 */
public class HomePage extends BasePage {
    public HomePage() {
        add(new Label("message", "Hello, World! Janosh was here"));
    }
}
