package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.login;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author hansejan
 */
public final class Login extends BasePage {
    private String errorMessage = "";
    private TextField<String> userLogin;
    private TextField<String> password;
    
    /**
     * Constructor.
     */
    public Login() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);

        PropertyModel errorMessageModel =
                new PropertyModel(this, "errorMessage");
        menuBorder.add(new Label("error", errorMessageModel));
        //Add a form as an inner class.
        Form form = new Form("form") {
            //Handles required fields error.
            @Override
            protected void onError() {
                List<String> emptyFields = new ArrayList<String>();
                if (!userLogin.checkRequired()) {
                    emptyFields.add("Login");
                    userLogin.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    userLogin.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!password.checkRequired()) {
                    emptyFields.add("Password");
                    password.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    password.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                StringBuilder errorMessage = new StringBuilder();
                if (emptyFields.size() > 0) {
                    if (emptyFields.size() == 1) {
                        errorMessage.append("Feltet ");
                        errorMessage.append("'").append(emptyFields.get(0))
                                .append("'");
                    }
                    else {
                        errorMessage.append("Felterne ");
                        int fieldCounter = 1;
                        for (String field : emptyFields) {
                            errorMessage.append("'").append(field).append("'");
                            if (fieldCounter < emptyFields.size() -1) {
                                errorMessage.append(", ");
                            }
                            if (fieldCounter == emptyFields.size() -1) {
                                errorMessage.append(" og ");
                            }
                            fieldCounter++;
                        }
                    }
                    errorMessage.append(" skal udfyldes.");
                }
                setErrorMessage(errorMessage.toString());
            }
         };
        menuBorder.add(form);

        //Add fields to the form.
        userLogin = new TextField("userlogin", new Model(""));
        userLogin.setRequired(true);
        form.add(userLogin);

        password = new PasswordTextField("password", new Model(""));
        password.setRequired(true);
        form.add(password);
        
        //Add button to the form.
        form.add(new Button("barilogin") {
            @Override
            public void onSubmit() {
                setErrorMessage("Fejl i login eller password.");
                /*
                ApplicationUser bariUser =
                        BariUserBusiness.isValidUser(userLogin.getModelObject(),
                        password.getModelObject());
                if (bariUser != null) {
                    BariSession bariSession = BariSession.get();
                    bariSession.setBariUser(bariUser);
                    setResponsePage(Overview.class);
                }
                else {
                    setErrorMessage("Fejl i login eller password.");
                }
                 
                 */
            }
      });
    }

    /**
     * Returns an error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set error message.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}