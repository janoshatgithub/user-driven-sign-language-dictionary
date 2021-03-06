package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.HomePage;
import java.util.logging.Logger;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;


/**
 * User driven sign language dictionary wicket application.
 * 
 * @author Jan S. Hansen
 */
public class Application extends WebApplication {
    static final Logger logger = Logger.getLogger(Application.class.getName());
    
    public Application() {
        logger.info("Application started");
    }

    @Override
    public Class getHomePage() {
        return HomePage.class;
    }
    
    @Override
    public Session newSession(Request request, Response response) {
        return new WicketSession(request);
    }
}