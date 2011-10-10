package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.HomePage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Wicket application.
 * 
 * @author Jan S. Hansen
 */
public class Application extends WebApplication {
    public Application() {
    }

    public Class getHomePage() {
        return HomePage.class;
    }
}
