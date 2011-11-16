package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.word;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.business.VideoFileBusiness;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.VideoFile;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.WicketSession;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.upload.Upload;
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

/**
 * Word page.
 * 
 * @author Jan S. Hansen
 */
public final class SelectedWord extends BasePage {

    public SelectedWord(final Word word) {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        borderBodyContainer.add(new Label("word", word.getWord()));
        borderBodyContainer.add(new Label("description", word.getDescription()));
        Link uploadLink = new Link("uploadLink") {
            @Override
            public void onClick() {
                Page page = new Upload(word);
                setResponsePage(page);
            }
        };
        WicketSession wicketSession = WicketSession.get();
        if (!wicketSession.isAuthenticated()) {
            uploadLink.setVisible(false);
        }
        borderBodyContainer.add(uploadLink);
        borderBodyContainer.add(new FeedbackPanel("feedback"));
        List<VideoFile> videoFileList = 
                VideoFileBusiness.getAllVideoFilesForAWord(word);
        if (videoFileList.size() == 0) {
            info("Ingen forslag er uploadet.");
        }
        PageableListView pageableListView =
                new PageableListView("pageable", videoFileList, 5) {
            @Override
            protected void populateItem(final ListItem item) {
                final VideoFile videoFile = (VideoFile)item.getModelObject();
                item.add(new Label("byUser", 
                        videoFile.getUploadedBy().getFullname()));
                item.add(new Label("dateTime", 
                        standardDateTimeFormat.format(videoFile.getUploadedDateTime())));
                Link videoLink = new Link("videoLink") {
                    @Override
                    public void onClick() {
                        //TODO
                    }
                };
                item.add(videoLink);
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
        borderBodyContainer.add(new PagingNavigator("navigator", 
                pageableListView));
    }
}