package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;           

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.ApplicationUser;
import java.text.SimpleDateFormat;
import org.apache.wicket.markup.html.WebPage;

/** 
 * Abstract base page with header and footer panel.
 * 
 * @author Jan S. Hansen
 */
public abstract class BasePage extends WebPage {
    protected final static SimpleDateFormat standardDateTimeFormat =
        new SimpleDateFormat("dd/MM-yyyy HH:mm");

    public BasePage() { 
        super(); 
        WicketSession session = WicketSession.get();
        ApplicationUser appUser = null;
        if (session.isAuthenticated()) {
            appUser = session.getApplicationUser(); 
        }
        add(new HeaderPanel("headerpanel", appUser)); 
        add(new FooterPanel("footerpanel", "Udviklet af Jan Schrøder Hansen - "
                + "Efteråret 2011"));
    } 
}
