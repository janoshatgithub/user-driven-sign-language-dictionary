package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.user;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.ApplicationUserBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import org.apache.wicket.PageParameters;

/**
 * E-mail verified page
 * 
 * @author Jan S. Hansen
 */
public final class EmailVerified extends BasePage {
    
    public EmailVerified(PageParameters params) {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        CharSequence charSequence = params.getCharSequence("login");
        ApplicationUserBusiness.setEmailVerified(charSequence.toString());;
    }
}