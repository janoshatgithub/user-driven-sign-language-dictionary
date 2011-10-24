package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.html5;

import java.util.List;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

/**
 * HTML 5 Video tag.
 * 
 * This is created with "Integrating HTML5 and Wicket" as a insperation. 
 * see http://wicketbyexample.com/integrating-html5-and-wicket/
 * 
 * @author Jan S. Hansen
 */
public class Html5Video extends WebMarkupContainer {
    
    private IModel<List<VideoSource>> sources;
 
    public Html5Video(String id, final IModel<List<VideoSource>> model) {
            super(id, model);
            this.sources = wrap(model);
    }

    
    @Override
    protected void onComponentTag(ComponentTag tag) {
        checkComponentTag(tag, "video");
        tag.put("autobuffer", true);
        tag.put("autoplay", true);
        tag.put("loop", false);
        tag.put("controls", true);
        super.onComponentTag(tag);
    }

    @Override
    protected void onComponentTagBody(MarkupStream markupStream,
        ComponentTag openTag) {
 
        if (sources != null) {
            final AppendingStringBuffer buffer = new AppendingStringBuffer();
            List<VideoSource> videoSources = sources.getObject();
            for (VideoSource videoSource : videoSources) {
                buffer.append("\n<source ");
                buffer.append("src='");
                ResourceReference resourceReference = videoSource.getSource();
                buffer.append(urlFor(resourceReference));
                buffer.append("'");
                String videoType = videoSource.getType().getVideoType(); 
                if (videoType != null) {
                    buffer.append(" type='");
                    buffer.append(videoType);
                    buffer.append("'");
                }
 
                buffer.append(" />");
            }
 
            buffer.append("\n");
 
            getResponse().write(buffer.toString());
 
        }        
        super.onComponentTagBody(markupStream, openTag);
    }
}