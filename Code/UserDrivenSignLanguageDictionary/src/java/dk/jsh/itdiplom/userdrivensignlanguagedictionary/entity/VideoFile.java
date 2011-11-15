package dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * File entity class.
 * 
 * @author Jan S. Hansen
 */
@Entity
public class VideoFile implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    @Column(nullable = false)
    protected Integer version;
    @Column(length=100, nullable = false)
    protected String fileName;
    @Column(length=50, nullable = false)
    protected String resourceName;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date uploadedDateTime;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_file_applicationuser")
    protected ApplicationUser uploadedBy;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_file_word")
    protected Word toWord;
    
    public VideoFile() {
    };

    public VideoFile(String fileName, String resourceName, Date uploadedDateTime, 
            ApplicationUser uploadedBy, Word toWord) {
        this.fileName = fileName;
        this.resourceName = resourceName;
        this.uploadedDateTime = uploadedDateTime;
        this.uploadedBy = uploadedBy;
        this.toWord = toWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Word getToWord() {
        return toWord;
    }

    public void setToWord(Word toWord) {
        this.toWord = toWord;
    }

    public ApplicationUser getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(ApplicationUser uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Date getUploadedDateTime() {
        return uploadedDateTime;
    }

    public void setUploadedDateTime(Date uploadedDateTime) {
        this.uploadedDateTime = uploadedDateTime;
    }
}