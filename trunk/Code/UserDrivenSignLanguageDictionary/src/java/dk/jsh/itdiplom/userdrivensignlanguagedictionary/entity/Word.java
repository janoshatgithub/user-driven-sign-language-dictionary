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
    
    
    
    
}
