package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.WordBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.Text;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.upload.Upload;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.word.SelectedWord;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

/**
 * All request page.
 * 
 * @author Jan S. Hansen
 */
public final class AllRequest extends BasePage {

    public AllRequest() {
        final WicketSession wicketSession = WicketSession.get();
        
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        borderBodyContainer.add(new FeedbackPanel("feedback"));
        
        List<Word> wordsWithoutUploads = 
                WordBusiness.getAllWordsWithoutUploads();
        if (wordsWithoutUploads.size() == 0) {
            info("Ingen forspørgelser unden forslag.");
        }
        PageableListView pageableListView =
                new PageableListView("pageable", wordsWithoutUploads, 6) {
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

                Link uploadLink = new Link("uploadLink") {
                    @Override
                    public void onClick() {
                        Page page = new Upload(word);
                        setResponsePage(page);
                    }
                };
                if (!wicketSession.isAuthenticated()) {
                    uploadLink.setEnabled(false);
                }
                item.add(uploadLink);
                
                
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