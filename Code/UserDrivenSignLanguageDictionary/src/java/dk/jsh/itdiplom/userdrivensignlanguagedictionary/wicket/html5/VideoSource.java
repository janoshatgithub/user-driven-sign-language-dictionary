package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.html5;

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
    
    private String source;
    private VideoType type;

    public VideoSource(String source, VideoType type) {
        this.source = source;
        this.type = type;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public VideoType getType() {
        return type;
    }

    public void setType(VideoType type) {
        this.type = type;
    }
}