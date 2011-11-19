package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.video;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.VideoFile;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.Application;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.about.About;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.html5.Html5Video;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.html5.VideoSource;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.word.SelectedWord;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;


/**
 * Page to show a video
 * 
 * @author Jan S. Hansen
 */
public final class SelectedVideo extends BasePage {

    public SelectedVideo(final Word word, final VideoFile videoFile) {
        MenuBorder menuBorder = new MenuBorder("mainNavigation"); 
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        
        Link back = new Link("back") {
            @Override
            public void onClick() {
                Page page = new SelectedWord(word);
                setResponsePage(page);
            }
        };
        borderBodyContainer.add(back);
        
        borderBodyContainer.add(new Label("word", word.getWord()));
        borderBodyContainer.add(new Label("wordDescription", word.getDescription()));
        borderBodyContainer.add(new Label("videoDescription", videoFile.getDescription()));

        final List<VideoSource> videoSources = new ArrayList<VideoSource>();
        videoSources.add(new VideoSource(new ResourceReference(Application.class,
                "uploadedvideo/" + videoFile.getResourceName()), 
                 VideoSource.VideoType.OGG));
        
        IModel<List<VideoSource>> videoSourceList = 
                new AbstractReadOnlyModel<List<VideoSource>>() {
            @Override
            public List<VideoSource> getObject() {
                return videoSources;
            }
        };
        Html5Video html5Video = new Html5Video("video", videoSourceList);
        
  
        borderBodyContainer.add(html5Video);
    }
}