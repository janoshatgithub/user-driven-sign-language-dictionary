package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.html5;

import org.apache.wicket.ResourceReference;

/**
 * Video source.
 * 
 * @author Jan S. Hansen
 */
public class VideoSource {
    public enum VideoType {
        OGG("video/ogg"),
        MP4("video/mp4");
        
        private String type;
        
        VideoType(String type) {
            this.type = type;
        }
        
        public String getVideoType() {
            return type;  
        }
    };
    
    private ResourceReference source;
    private VideoType type;

    public VideoSource(ResourceReference source, VideoType type) {
        this.source = source;
        this.type = type;
    }
    
    public ResourceReference getSource() {
        return source;
    }

    public void setSource(ResourceReference source) {
        this.source = source;
    }

    public VideoType getType() {
        return type;
    }

    public void setType(VideoType type) {
        this.type = type;
    }
}