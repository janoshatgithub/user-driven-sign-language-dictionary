package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.about.About;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.createuser.CreateUser;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.login.Login;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request.Request;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RepeatingView;

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
        final WicketSession session = WicketSession.get();
        
        BoxBorder navigationBorder = new BoxBorder("navigationBorder");
        
        RepeatingView repeatingView = new RepeatingView("menuItems");
        navigationBorder.add(repeatingView);
        
        addMenuLink(repeatingView, HomePage.class, "Søg");
        addMenuLink(repeatingView, About.class, "Om denne side");
        if (session.isAuthenticated()) {
            addMenuLink(repeatingView, Request.class, "Mine forespørgelser");
            addLogoffMenuLink(navigationBorder, session);
        }
        else {
            addMenuLink(repeatingView, CreateUser.class, "Ny bruger");
            addLoginMenuLink(navigationBorder);
        }
        
        add(navigationBorder);
        add(new BoxBorder("bodyBorder"));
    }

    private void addMenuLink(RepeatingView repeatingView, Class pageClass,
            String text) {
        WebMarkupContainer parent =
                new WebMarkupContainer(repeatingView.newChildId());
        repeatingView.add(parent);
        BookmarkablePageLink link = new BookmarkablePageLink("menuItemLink", 
                pageClass); 
        parent.add(link);
        link.add(new Label("menuItemText", text));
    }
    
    private void addLoginMenuLink(BoxBorder navigationBorder) {
        BookmarkablePageLink loginLink = 
                new BookmarkablePageLink("loginLogOffMenuItemLink", 
                        Login.class); 
        navigationBorder.add(loginLink);
        loginLink.add(new Label("loginLogoffText", "Log på"));
    }

    private void addLogoffMenuLink(BoxBorder navigationBorder,
            final WicketSession session) {
        Link logoffLink = new Link("loginLogOffMenuItemLink") {
            @Override
            public void onClick() {
                session.setApplicationUser(null);
                setResponsePage(HomePage.class);
            }
        };
        navigationBorder.add(logoffLink);
        logoffLink.add(new Label("loginLogoffText", "Log af"));
    }
}