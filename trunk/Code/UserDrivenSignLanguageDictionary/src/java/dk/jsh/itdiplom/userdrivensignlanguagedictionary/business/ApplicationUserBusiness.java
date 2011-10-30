package dk.jsh.itdiplom.userdrivensignlanguagedictionary.business;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.ApplicationUser;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Business metods for ApplicationUser.
 *
 * @author Jan S. Hansen
 */
public class ApplicationUserBusiness {
    
    private ApplicationUserBusiness(){};

    /**
     * Gets a applicationUser from login and password.
     * 
     * @param login user login
     * @param password password
     * @return a ApplicationUser or null if login or password is wrong.
     */
    public static ApplicationUser isValidUser(String login, String password) {
        ApplicationUser appUser = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select appUser from "
                + "dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity."
                + "ApplicationUser appUser "
                + "where appUser.login = :login "
                + "and appUser.password = :password " 
                + "and appUser.emailVerified is not null";
        Query query = session.createQuery(hql);
        query.setString("login", login);
        query.setString("password", login);
        List<ApplicationUser> appUsers = query.list();
        if (appUsers.size() == 1) {
            appUser = appUsers.get(0);
        }
        else if (appUsers.size() > 1) {
            throw new RuntimeException("More then one user with login " +
                    login);
        }
        session.close();
        return appUser;
    }
    
    /**
     * Persist a new Application user.
     * 
     * @param newUser a new ApplicationUser
     */
    public static void saveNew(ApplicationUser newUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(newUser);
        tx.commit();
        session.close();
    }    
    
    /**
     * Test if a user login is in use.
     * 
     * @param login user login to test
     * @return true is user login is in use. 
     */
    public static boolean isUserLoginInUse(String login) {
        boolean inUse = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select appUser "
                + "from dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity."
                + "ApplicationUser appUser "
                + "where appUser.login = :login";
        Query query = session.createQuery(hql);
        query.setString("login", login);
        if (query.list().isEmpty()) {
            inUse = false;
        }
        else {
            inUse = true;
        };
        session.close();
        return inUse;
    }
    
    public static void setEmailVerified(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select appUser "
                + "from dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity."
                + "ApplicationUser appUser "
                + "where appUser.login = :login";
        Query query = session.createQuery(hql);
        query.setString("login", login);
        ApplicationUser user = (ApplicationUser)query.list().get(0);
        user.setEmailVerified(new Date());
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }
}