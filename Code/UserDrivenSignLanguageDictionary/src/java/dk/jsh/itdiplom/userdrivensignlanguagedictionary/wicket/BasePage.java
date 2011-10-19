package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;           

import org.apache.wicket.markup.html.WebPage;

/** 
 * Abstract base page with header and footer panel.
 * 
 * @author Jan S. Hansen
 */
public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        WicketSession session = WicketSession.get();
        String userName = "";
        if (session.isAuthenticated()) {
            userName = session.getApplicationUser().getFullname();
        }
        add(new HeaderPanel("headerpanel", userName)); 
        add(new FooterPanel("footerpanel", "Udviklet af Jan Schrøder Hansen"));
    } 
}
