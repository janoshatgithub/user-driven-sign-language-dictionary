package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.WordBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import java.util.List;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Home/search page.
 * 
 * @author Jan S. Hansen
 */
public class HomePage extends BasePage {
    private String errorMessage = "";
    private TextField<String> searchFor;
    private Image errorIconImage = new Image("erroricon", 
            new ResourceReference(BasePage.class, "icons/attention.png"));

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
                if (!searchFor.checkRequired()) {
                    setErrorMessage("Søgefeltet skal udfyldes.");
                }
            }
        };
        borderBodyContainer.add(form);
        searchFor = new TextField("searchFor", new Model(""));
        searchFor.setRequired(true);
        form.add(searchFor);
        
        //Add button to the form.
        form.add(new Button("search") {
            @Override
            public void onSubmit() {
                removeErrorMessage();
                List<Word> words = 
                        WordBusiness.search(searchFor.getModelObject());
            }
        });
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
}