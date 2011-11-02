package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.request;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.WordBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import java.util.Date;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * New request page.
 * 
 * @author Jan S. Hansen
 */
public final class NewRequest extends BasePage {
    private String errorMessage = "";
    private TextField<String> word;
    private TextArea<String> description;
    private Image errorIconImage = new Image("erroricon", 
            new ResourceReference(BasePage.class, "icons/attention.png"));
    

    public NewRequest() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();

        Form form = new Form("form") {
            //Handles required fields error.
            @Override
            protected void onError() {
                if (emptyRequiredFields()) {
                    setErrorMessage("Alle felter skal udfyldes.");
                    return;
                }
            }
            
            private boolean emptyRequiredFields() {
                //Test for empty/required fields
                boolean emptyFields = false;
                if (!word.checkRequired()) {
                    emptyFields = true;
                    word.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    word.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!description.checkRequired()) {
                    emptyFields = true;
                    description.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    description.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                return emptyFields;
            }

        };
        borderBodyContainer.add(form);
        
        //Add fields to the form.
        word = new TextField("word", new Model(""));
        word.setRequired(true);
        form.add(word);
        
        description = new TextArea("description", new Model(""));
        description.setRequired(true);
        form.add(description);
        
        //Add button to the form.
        form.add(new Button("save") {
            @Override
            public void onSubmit() {
                //Test if word exists
                //TODO
                WicketSession session = WicketSession.get();
                Word newWord = new Word(word.getModelObject(), 
                        description.getModelObject(), new Date(), 
                        session.getApplicationUser());
                
                WordBusiness.saveNew(newWord);
                setResponsePage(Request.class);
            }
        });
        
        //Add error items
        PropertyModel errorMessageModel =
                new PropertyModel(this, "errorMessage");
        borderBodyContainer.add(new Label("error", errorMessageModel));
        errorIconImage.setVisible(false);
        borderBodyContainer.add(errorIconImage);
    }

    /**
     * Set error message.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        errorIconImage.setVisible(true);
    }
   
}