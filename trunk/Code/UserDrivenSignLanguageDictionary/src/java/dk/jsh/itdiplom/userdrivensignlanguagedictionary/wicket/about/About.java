package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.about;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.BasePage;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage.MenuBorder;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.html5.Html5Video;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.html5.VideoSource;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.border.Border.BorderBodyContainer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 * About page.
 *
 * @author Jan S. Hansen
 */
public final class About extends BasePage {

    
    public About() {
        MenuBorder menuBorder = new MenuBorder("mainNavigation");
        add(menuBorder);
        BorderBodyContainer borderBodyContainer = menuBorder.getBodyContainer();
        
        final List<VideoSource> videoSources = new ArrayList<VideoSource>();
        videoSources.add(new VideoSource(new ResourceReference(About.class, "movie.ogg"), 
                 VideoSource.VideoType.OGG));
        
        IModel<List<VideoSource>> videoSourceList = 
                new AbstractReadOnlyModel<List<VideoSource>>() {
            @Override
            public List<VideoSource> getObject() {
                return videoSources;
            }
        };
        Html5Video html5Video = new Html5Video("aboutVideo", videoSourceList);
        
        borderBodyContainer.add(html5Video);
    }
}