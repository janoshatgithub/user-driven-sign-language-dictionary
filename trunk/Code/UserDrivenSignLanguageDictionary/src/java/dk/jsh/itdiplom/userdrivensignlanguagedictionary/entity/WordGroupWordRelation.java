package dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Word group/Word many to many relation.
 * 
 * @author Jan S. Hansen
 */
@Entity
@Table(name="WordGroupWordRelation",
       uniqueConstraints = {
            @UniqueConstraint(columnNames={"wordGroup_id", "word_id"})
})
public class WordGroupWordRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    @Column(nullable = false)
    protected Integer version;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_wordgroupwordrelation_wordgroup")
    protected WordGroup wordGroup;
    @ManyToOne(optional=false)
    @org.hibernate.annotations.ForeignKey(name="fk_wordgroupwordrelation_word")
    protected Word word;

    public WordGroupWordRelation() {
    }

    public WordGroupWordRelation(WordGroup wordGroup, Word word) {
        this.wordGroup = wordGroup;
        this.word = word;
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

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public WordGroup getWordGroup() {
        return wordGroup;
    }

    public void setWordGroup(WordGroup wordGroup) {
        this.wordGroup = wordGroup;
    }
}