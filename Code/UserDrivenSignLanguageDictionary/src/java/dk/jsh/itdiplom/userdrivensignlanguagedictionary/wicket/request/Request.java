package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.WordBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;

import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;

/**
 * Request page.
 * 
 * @author Jan S. Hansen
 */
public final class Request extends BasePage {

    public Request() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        
        //Link to create new request
        BookmarkablePageLink createNewLink = 
                new BookmarkablePageLink("createLink", 
                        NewRequest.class);
        borderBodyContainer.add(createNewLink);
        
        WicketSession wicketSession = WicketSession.get();
        List<Word> allWords = 
                WordBusiness.getAllWordsCreatedByUser(wicketSession.getApplicationUser());
        PageableListView pageableListView =
                new PageableListView("pageable", allWords, 5) {
            @Override
            protected void populateItem(final ListItem item) {
                final Word word = (Word)item.getModelObject();
                Label wordLabel = new Label("word", word.getWord());
                wordLabel.add(new AttributeModifier("title",
                        new Model(word.getDescription())));
                item.add(wordLabel);
                item.add(new Label("created", 
                        standardDateTimeFormat.format(word.getCreatedDateTime()))); 
                List<String> wordGroupList = word.getSortedWordGroups();
                item.add(new Label("groups", makeWordGroupString(wordGroupList)));
            }

            private String makeWordGroupString(List<String> wordGroupList) {
                StringBuilder groups = new StringBuilder();
                int noOfGroups = wordGroupList.size();
                if (noOfGroups > 0) {
                    for (int i = 0; i < noOfGroups; i++) {
                        String wordGroup = wordGroupList.get(i);
                        if (i > 0 && i < noOfGroups - 1) {
                            groups.append(", ");
                        }
                        else if (i == noOfGroups -1) {
                            groups.append(" og ");
                        }
                        groups.append(wordGroup);
                    }
                }
                else {
                    groups.append("Ikke tilknyttet nogen gruppe");
                }
                groups.append(".");
                return groups.toString();
            }
        };
        
        borderBodyContainer.add(pageableListView);
        borderBodyContainer.add(new PagingNavigator("navigator", pageableListView));
        
    }
}