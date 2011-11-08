package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.word;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.link.Link;

/**
 * Word page.
 * 
 * @author Jan S. Hansen
 */
public final class SelectedWord extends BasePage {

    public SelectedWord(Word word) {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        borderBodyContainer.add(new Label("word", word.getWord()));
        borderBodyContainer.add(new Label("description", word.getDescription()));
        Link uploadLink = new Link("uploadLink") {
            @Override
            public void onClick() {
            }
        };
        WicketSession wicketSession = WicketSession.get();
        if (!wicketSession.isAuthenticated()) {
            uploadLink.setVisible(false);
        }
        borderBodyContainer.add(uploadLink);
    }
}