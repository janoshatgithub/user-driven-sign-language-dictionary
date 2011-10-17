package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.ApplicationUser;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

/**
 * Wicket session.
 *
 * @author Jan S. Hansen
 */
public class WicketSession extends WebSession {
    private ApplicationUser user;

    public WicketSession(Request request) {
        super(request);
    }

    public static WicketSession get() {
        return (WicketSession) WicketSession.get();
    }

    public boolean isAuthenticated() {
        return (user != null);
    }

    public ApplicationUser getApplicationUser() {
        return user;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.user = applicationUser;
    }
}