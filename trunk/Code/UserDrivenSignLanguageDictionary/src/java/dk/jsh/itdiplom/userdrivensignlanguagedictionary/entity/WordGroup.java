package dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Word group entity class.
 * 
 * @author Jan S. Hansen
 */
@Entity
public class WordGroup implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    @Column(nullable = false)
    protected Integer version;
    @Column(length=30, nullable = false, unique=true)
    protected String name;
    @Column(length=250, nullable = true)
    protected String description;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date createdDateTime;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_wordgroup_applicationuser")
    protected ApplicationUser createdBy;

    public WordGroup() {
    }
    
    public WordGroup(String name, String description, Date createdDateTime, 
            ApplicationUser createdBy) {
        this.name = name;
        this.description = description;
        this.createdDateTime = createdDateTime;
        this.createdBy = createdBy;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApplicationUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ApplicationUser createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}