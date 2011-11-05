package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.WordBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;

import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.PropertyModel;

/**
 * Request page.
 * 
 * @author Jan S. Hansen
 */
public final class Request extends BasePage {
    //choices in radio button
    private static final List<String> radioChoices = Arrays
			.asList(new String[] {"Mine", "Alle"});
    private String selected = "Mine";

    public Request() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        Form form = new Form("form") {
            
            
        };
        borderBodyContainer.add(form);
        
        RadioChoice<String> userOrAll = new RadioChoice<String>(
            "showAllRequest", new PropertyModel<String>(this, "selected"), 
                radioChoices);
        userOrAll.setSuffix("");
        form.add(userOrAll);
        
        //Link to create new request
        BookmarkablePageLink createNewLink = 
                new BookmarkablePageLink("createLink", 
                        NewRequest.class);
        borderBodyContainer.add(createNewLink);
        
        List<Word> allWords = WordBusiness.getAllWords();
        PageableListView pageableListView =
                new PageableListView("pageable", allWords, 10) {
            @Override
            protected void populateItem(final ListItem item) {
                final Word word = (Word)item.getModelObject();
                item.add(new Label("word", word.getWord()));
                item.add(new Label("created", 
                        standardDateTimeFormat.format(word.getCreatedDateTime()))); 
                item.add(new Label("byUser", 
                        word.getRequestCreatedBy().getFullname())); 
            }
        };
        borderBodyContainer.add(pageableListView);
        borderBodyContainer.add(new PagingNavigator("navigator", pageableListView));
        
    }
}