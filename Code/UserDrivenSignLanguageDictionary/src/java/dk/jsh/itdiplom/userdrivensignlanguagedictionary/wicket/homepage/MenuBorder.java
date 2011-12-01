package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.about.About;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.user.CreateUser;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.group.AllGroups;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.group.Groups;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.login.Login;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request.AllRequest;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request.Request;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.upload.Uploads;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.user.ChangeUser;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

/**
 * Menu border component.
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
        
        addMenuLink(repeatingView, HomePage.class, "Søg", "Søg efter ord");
        addMenuLink(repeatingView, About.class, "Om denne side", 
                "Information om denne side");
        if (session.isAuthenticated()) {
            addMenuLink(repeatingView, AllRequest.class, "Alle forespørgsler", 
                    "Vis oversigt over alle forespørgsler, som mangler forslag.");
            addMenuLink(repeatingView, AllGroups.class, "Alle grupper", 
                    "Vis oversigt over alle grupper");
            addMenuLink(repeatingView, Request.class, "Mine forespørgsler", 
                    "Vis oversigt over egne forespørgsler");
            addMenuLink(repeatingView, Groups.class, "Mine grupper", 
                    "Vis oversigt over egne grupper");
            addMenuLink(repeatingView, Uploads.class, "Mine uploads", 
                    "Vis oversigt over egne uploads");
            addMenuLink(repeatingView, ChangeUser.class, "Ret brugeroplysninger",
                    "Vis/ret egne brugeroplysninger");
            addLogoffMenuLink(navigationBorder, session);
        }
        else {
            addMenuLink(repeatingView, AllRequest.class, "Alle forespørgsler", 
                    "Vis oversigt over alle forespørgsler, som mangler forslag.");
            addMenuLink(repeatingView, AllGroups.class, "Alle grupper", 
                    "Vis oversigt over alle grupper");
            addMenuLink(repeatingView, CreateUser.class, "Ny bruger", 
                    "Opret ny bruger");
            addLoginMenuLink(navigationBorder);
        }
        
        add(navigationBorder);
        add(new BoxBorder("bodyBorder"));
    }

    private void addMenuLink(RepeatingView repeatingView, Class pageClass,
            String text, String title) {
        WebMarkupContainer parent =
                new WebMarkupContainer(repeatingView.newChildId());
        repeatingView.add(parent);
        BookmarkablePageLink link = new BookmarkablePageLink("menuItemLink", 
                pageClass); 
        link.add(new AttributeModifier("title", true,
                    new Model(title)));
        parent.add(link);
        link.add(new Label("menuItemText", text));
    }
    
    private void addLoginMenuLink(BoxBorder navigationBorder) {
        BookmarkablePageLink loginLink = 
                new BookmarkablePageLink("loginLogOffMenuItemLink", 
                        Login.class); 
        navigationBorder.add(loginLink);
        loginLink.add(new AttributeModifier("title", true,
            new Model("Log på systemet.")));
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
        logoffLink.add(new AttributeModifier("title", true,
            new Model("Log af systemet.")));
        logoffLink.add(new Label("loginLogoffText", "Log af"));
    }
}