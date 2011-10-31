package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.createuser;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.ApplicationUserBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.ApplicationUser;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Constants;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.EMailSender;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.Application;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import java.util.Date;
import javax.servlet.ServletContext;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * Create user page.
 * 
 * @author Jan S. Hansen
 */
public final class CreateUser extends BasePage {
    private String errorMessage = "";
    private TextField<String> fullName;
    private TextField<String> userLogin;
    private TextField<String> password;
    private TextField<String> repeatPassword;
    private TextField<String> email;
    private Image errorIconImage = new Image("erroricon", 
            new ResourceReference(BasePage.class, "icons/attention.png"));
    
    public CreateUser() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        
        PropertyModel errorMessageModel =
                new PropertyModel(this, "errorMessage");
        borderBodyContainer.add(new Label("error", errorMessageModel));
        //Add a form as an inner class.
        Form form = new Form("form") {
            //Handles required fields error.
            @Override
            protected void onError() {
                if (emptyRequiredFields()) {
                    setErrorMessage("Alle felter skal udfyldes.");
                    return;
                }

                //Test for to short login and password
                if (!userLogin.isValid()) {
                    setErrorMessage("Bruger kode skal være mindst 3 tegn langt");
                    userLogin.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                    return;
                }
                else {
                    userLogin.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!password.isValid()) {
                    setErrorMessage("Password skal være mindst 3 tegn langt");
                    password.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                    return;
                }
                else {
                    password.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                
                //Test if it is a valid email
                if (!email.isValid()) {
                    setErrorMessage("Email er ikke valid.");
                    email.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                    return;
                }
                else {
                    email.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
            }

            private boolean emptyRequiredFields() {
                //Test for empty/required fields
                boolean emptyFields = false;
                if (!fullName.checkRequired()) {
                    emptyFields = true;
                    fullName.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    fullName.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!userLogin.checkRequired()) {
                    emptyFields = true;
                    userLogin.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    userLogin.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!password.checkRequired()) {
                    emptyFields = true;
                    password.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    password.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!repeatPassword.checkRequired()) {
                    emptyFields = true;
                    repeatPassword.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    repeatPassword.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                if (!email.checkRequired()) {
                    emptyFields = true;
                    email.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                }
                else {
                    email.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                return emptyFields;
            }
        };
        borderBodyContainer.add(form);

        //Add fields to the form.
        fullName = new TextField("fullName", new Model(""));
        fullName.setRequired(true);
        form.add(fullName);

        userLogin = new TextField("userlogin", new Model(""));
        userLogin.setRequired(true);
        userLogin.add(StringValidator.minimumLength(3));
        form.add(userLogin);
        
        password = new PasswordTextField("password", new Model(""));
        password.setRequired(true);
        password.add(StringValidator.minimumLength(3));
        form.add(password);
        
        repeatPassword = new PasswordTextField("repeatPassword", new Model(""));
        repeatPassword.setRequired(true);
        repeatPassword.add(StringValidator.minimumLength(3));
        form.add(repeatPassword);

        email = new TextField("email", new Model(""));
        email.add(EmailAddressValidator.getInstance());
        email.setRequired(true);
        form.add(email);
        
        //Add button to the form.
        form.add(new Button("save") {
            @Override
            public void onSubmit() {
                //Test if password = repeat password
                if (!password.getModelObject().equals(repeatPassword.getModelObject())) {
                    setErrorMessage("Password og Gentag password er ikke ens");
                    password.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                    repeatPassword.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                    return;
                }
                else {
                    password.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                    repeatPassword.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                
                //Test if login is in use
                if (ApplicationUserBusiness.isUserLoginInUse(
                        userLogin.getModelObject())) {
                    setErrorMessage("Bruger koden bruges af en anden bruger.");
                    userLogin.add(new AttributeModifier("style", true,
                            new Model("border-color:red;")));
                    return;
                } 
                else {
                    userLogin.add(new AttributeModifier("style", true,
                            new Model("border-color:default;")));
                }
                
                ApplicationUser newUser = new ApplicationUser(
                        userLogin.getModelObject(),
                        password.getModelObject(),
                        fullName.getModelObject(),
                        email.getModelObject(),
                        new Date(),
                        null, 
                        Constants.UserRole.NORMAL);
               
                String mailBody = createMailBody();
                EMailSender eMailSender = EMailSender.getInstance();
                if (eMailSender.sendNoReplyEmail(email.getModelObject(),
                        "Velkommen til Tegn til tiden", mailBody)) {
                    ApplicationUserBusiness.saveNew(newUser);
                    setResponsePage(UserCreated.class);
                }
                else {
                    setErrorMessage("Kunne ikke afsende e-mail, tjek den "
                            + "indtastede e-mail adresse. "
                            + "Eller prøv igen senere.");
                }
            }

            private String createMailBody() {
                //Try to send an e-mail
                PageParameters pageParameters = new PageParameters("login=" 
                        + userLogin.getModelObject());
                CharSequence pageUrl = urlFor(EmailVerified.class, pageParameters);
                //TODO: Fix this.
                String fullURL = "http://localhost:8084/"
                        + "UserDrivenSignLanguageDictionary/wicket/" 
                        + pageUrl.toString();
                String link = "<a href='" + fullURL +"'>Bekræft email</a>";
                String email = "<a href='mailto:jan.sch.hansen@gmail.com?"
                        + "Subject=Spørgsmål til Tegn til tiden'>"
                        + "jan.sch.hansen@gmail.com</a>";
                StringBuilder mailBody = new StringBuilder();
                mailBody.append("Velkommen til Tegn til tiden. <br/><br/>");
                mailBody.append("Før du kan logge på systemet skal du trykke ");
                mailBody.append("på følgende link, for at bekrærft din mail adresse.<br/><br/>");
                mailBody.append("&nbsp;&nbsp;&nbsp;&nbsp;");
                mailBody.append(link);
                mailBody.append("<br/><br/>");
                mailBody.append("OBS! - Denne mail kan ikke besvares.<br/>");
                mailBody.append("Eventuelle spørgsmål kan rettes til Jan Scrhøder Hansen på ");
                mailBody.append("e-mail: ");
                mailBody.append(email);
                mailBody.append("<br/><br/>");
                mailBody.append("Med venlig hilsen<br/>");
                mailBody.append("Tegn til tiden");
                return mailBody.toString();
            }
        });

        //Add error items
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