package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.upload;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.word.SelectedWord;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.logging.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

/**
 * Upload page.
 * 
 * @author Jan S. Hansen
 */
public final class Upload extends BasePage {
    static final Logger logger = Logger.getLogger(Upload.class.getName());
    
    private FileUploadField fileUpload;
    private String UPLOAD_FOLDER = "C:\\Temp\\Upload\\";
    private String errorMessage = "";
    private Image errorIconImage = new Image("erroricon", 
            new ResourceReference(BasePage.class, "icons/attention.png"));
    
    public Upload(final Word word) {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        borderBodyContainer.add(new Label("word", word.getWord()));
        Link back = new Link("back") {
            @Override
            public void onClick() {
                Page page = new SelectedWord(word);
                setResponsePage(page);
            }
        };
        borderBodyContainer.add(back);
        
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                logger.info("Upload started");
                errorIconImage.setVisible(false);
                final FileUpload uploadedFile = fileUpload.getFileUpload();
		if (uploadedFile != null) {
                    // write to a new file
                    File newFile = new File(UPLOAD_FOLDER
                            + uploadedFile.getClientFileName());
                    if (newFile.exists()) {
                            newFile.delete();
                    }
                    try {
                        newFile.createNewFile();
                        uploadedFile.writeTo(newFile);
                        Runtime runtime = Runtime.getRuntime();
                        String cmdLine = "C:\\GoogleCode\\"
                                + "user-driven-sign-language-dictionary"
                                + "\\ffmpeg2theora-0.28.exe "  
                                + " -o " + UPLOAD_FOLDER + "jan.ogv " 
                                + UPLOAD_FOLDER + newFile.getName();
 
                        
                        
                        info("Filen " + newFile.getName() +
                                " er uploaded og konverteret.");
                    }
                    catch (Exception exception) {
                        setErrorMessage("Fejl under upload og konvertering.");
                    }
        	}
            }
        };            
        // Enable multipart mode (need for uploads file)
	form.setMultiPart(true);
 
	// max upload size, 10k
	//form.setMaxSize(Bytes.kilobytes(10));
 
	form.add(fileUpload = new FileUploadField("fileUpload"));
        
        borderBodyContainer.add(form);
        borderBodyContainer.add(new FeedbackPanel("feedback"));
        
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