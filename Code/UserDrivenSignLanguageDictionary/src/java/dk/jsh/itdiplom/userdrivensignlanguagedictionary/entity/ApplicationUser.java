package dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Constants.UserRole;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * BariUser entity class.
 *
 * @author Jan S. Hansen
 */
@Entity
public class ApplicationUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Version
    @Column(nullable = false)
    protected Integer version;
    @Column(length=20, nullable = false, unique=true)
    protected String login;
    @Column(length=20, nullable = false)
    protected String password;
    @Column(length=50, nullable = false)
    protected String fullname;
    @Enumerated(EnumType.STRING)
    @Column(length=50, nullable = false)
    protected String email;
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date emailVerificationSent;
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date emailVerified;
    @Enumerated(EnumType.STRING)
    @Column(length=10, nullable = false)
    protected UserRole userRole;

    public ApplicationUser() {
    }

    public ApplicationUser(String login, String password, String fullname,
            String email, Date emailVerificationSent,
            Date emailVerified, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.emailVerificationSent = emailVerificationSent;
        this.emailVerified = emailVerified;
        this.userRole = userRole;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEmailVerificationSent() {
        return emailVerificationSent;
    }

    public void setEmailVerificationSent(Date emailVerificationSent) {
        this.emailVerificationSent = emailVerificationSent;
    }

    public Date getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Date emailVerified) {
        this.emailVerified = emailVerified;
    }
    
     public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}