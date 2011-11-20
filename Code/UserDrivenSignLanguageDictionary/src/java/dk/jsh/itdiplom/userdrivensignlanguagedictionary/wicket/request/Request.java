package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.WordBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.Text;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.word.SelectedWord;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
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
        borderBodyContainer.add(new FeedbackPanel("feedback"));
        
        WicketSession wicketSession = WicketSession.get();
        List<Word> allWords = 
                WordBusiness.getAllWordsCreatedByUser(wicketSession.getApplicationUser());
        if (allWords.size() == 0) {
            info("Ingen forspørgelser.");
        }
        
        PageableListView pageableListView =
                new PageableListView("pageable", allWords, 4) {
            @Override
            protected void populateItem(final ListItem item) {
                final Word word = (Word)item.getModelObject();
                Label wordLabel = new Label("word", word.getWord());
                Link wordLink = new Link("wordLink") {
                    @Override
                    public void onClick() {
                        Page page = new SelectedWord(word);
                        setResponsePage(page);
                    }
                };
                wordLink.add(new AttributeModifier("title", true,
                    new Model(word.getDescription())));
                wordLink.add(wordLabel);
                item.add(wordLink);
                
                item.add(new Label("created", 
                        standardDateTimeFormat.format(word.getCreatedDateTime()))); 
                List<String> wordGroupList = word.getSortedWordGroups();
                item.add(new Label("groups", Text.makeWordGroupString(wordGroupList)));
                item.add(new AttributeModifier("class",
                    true, new AbstractReadOnlyModel<String>() {
                    @Override
                    public String getObject()
                    {
                        return (item.getIndex() % 2 == 1) ? "even" : "odd";
                    }
                }));
            }
        };
        
        borderBodyContainer.add(pageableListView);
        borderBodyContainer.add(new PagingNavigator("navigator", pageableListView));
    }
}