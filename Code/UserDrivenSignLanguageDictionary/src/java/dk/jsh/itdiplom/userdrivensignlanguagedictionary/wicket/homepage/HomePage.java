package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.WordBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.WordGroupBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.WordGroup;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.Text;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.word.SelectedWord;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Home/search page.
 * 
 * @author Jan S. Hansen
 */
public class HomePage extends BasePage {
    private String errorMessage = "";
    private DropDownChoice<String> searchFor;
    private TextField<String> searchText;
    private Image errorIconImage = new Image("erroricon", 
            new ResourceReference(BasePage.class, "icons/attention.png"));
    private List<Word> wordsFound = new ArrayList<Word>(); 
    private PageableListView pageableListView;
    private FeedbackPanel feedbackPanel;
    
    public HomePage() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        PropertyModel errorMessageModel =
            new PropertyModel(this, "errorMessage");
        borderBodyContainer.add(new Label("error", errorMessageModel));
        errorIconImage.setVisible(false);
        borderBodyContainer.add(errorIconImage);

        Form form = new Form("form") {
            //Handles required fields error.
            @Override
            protected void onError() {
                if (!searchText.checkRequired()) {
                    feedbackPanel.setVisible(false);
                    setErrorMessage("Søgefeltet skal udfyldes.");
                }
            }
        };
        borderBodyContainer.add(form);
        
        searchFor = new DropDownChoice("searchFor",
                new Model(SearchType.WORD.getDescription()), 
                SearchType.getDescriptions());
        form.add(searchFor);
        searchText = new TextField("searchText", new Model(""));
        searchText.setRequired(true);
        form.add(searchText);
        
        //Add button to the form.
        form.add(new Button("search") {
            @Override
            public void onSubmit() {
                removeErrorMessage();
                if (SearchType.getSearchType(searchFor.getModelObject()) 
                        == SearchType.WORD) {
                    wordsFound = WordBusiness.search(searchText.getModelObject());
                }
                else {
                    List<WordGroup> wordGroups = 
                            WordGroupBusiness.search(searchText.getModelObject());
                    if (!wordGroups.isEmpty()) {
                        wordsFound = WordBusiness.search(wordGroups);
                    }
                }
                if (wordsFound.isEmpty()) {
                    feedbackPanel.setVisible(true);
                    info("Ingen ord fundet.");
                }
                else {
                    feedbackPanel.setVisible(false);
                }
                pageableListView.setList(wordsFound);
            }
        });
        
        //Search results
        feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setVisible(false);
        borderBodyContainer.add(feedbackPanel);
        pageableListView =
                new PageableListView("pageable", wordsFound, 4) {
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
    
    /**
     * Set error message.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        errorIconImage.setVisible(true);
    }
    
    /**
     * Remove error message.
     */
    public void removeErrorMessage() {
        this.errorMessage = "";
        errorIconImage.setVisible(false);
    }
    
    public enum SearchType {
        WORD("Ord"),
        GROUP("Gruppe");

        private String description;

        SearchType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static List<String> getDescriptions() {
            List<String> descriptions = new ArrayList<String>();
            descriptions.add(WORD.description);
            descriptions.add(GROUP.description);
            return descriptions;
        }

        public static SearchType getSearchType(String description) {
            if (WORD.description.equals(description)) return WORD;
            return GROUP;
        }
     }
}