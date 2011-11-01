package dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Word entity class.
 * 
 * @author Jan S. Hansen
 */
@Entity
public class Word implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    @Column(nullable = false)
    protected Integer version;
    @Column(length=50, nullable = false, unique=true)
    protected String word;
    @Column(length=250, nullable = true)
    protected String description;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date createdDateTime;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_word_applicationuser")
    protected ApplicationUser requestCreatedBy;

    public Word() {
    }
    
    public Word(String word, String description, Date createdDateTime, 
            ApplicationUser requestCreatedBy) {
        this.word = word;
        this.description = description;
        this.createdDateTime = createdDateTime;
        this.requestCreatedBy = requestCreatedBy;
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApplicationUser getRequestCreatedBy() {
        return requestCreatedBy;
    }

    public void setRequestCreatedBy(ApplicationUser requestCreatedBy) {
        this.requestCreatedBy = requestCreatedBy;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}