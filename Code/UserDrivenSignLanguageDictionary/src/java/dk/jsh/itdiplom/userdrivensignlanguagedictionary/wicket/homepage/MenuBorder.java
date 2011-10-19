package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.createuser.CreateUser;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.login.Login;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request.Request;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * Border component.
 * 
 * @author Jan S. Hansen
 */
public class MenuBorder extends Border
{
    /**
     * Constructor
     * 
     * @param id
     *            The id of this component
     */
    public MenuBorder(final String id)
    {
        super(id);
        WicketSession session = WicketSession.get();
        
        BoxBorder navigationBorder = new BoxBorder("navigationBorder");
        BookmarkablePageLink loginLink = new BookmarkablePageLink("login", 
                Login.class); 
        if (session.isAuthenticated()) {
            loginLink.setVisible(false);
        }
        else {
            loginLink.setVisible(true);
        }
        
        
        navigationBorder.add(new BookmarkablePageLink("search", 
                HomePage.class));
        navigationBorder.add(new BookmarkablePageLink("request",
                Request.class));
        navigationBorder.add(new BookmarkablePageLink("createuser", 
                CreateUser.class));
        navigationBorder.add(loginLink);
        add(navigationBorder);
        add(new BoxBorder("bodyBorder"));
    }
}