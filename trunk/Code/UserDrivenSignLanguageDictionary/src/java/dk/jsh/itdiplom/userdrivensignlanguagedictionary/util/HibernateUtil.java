package dk.jsh.itdiplom.userdrivensignlanguagedictionary.util;

import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 * Hibernate session factory.
 *
 * @author Jan S. Hansen
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory =
               new AnnotationConfiguration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /** 
     * Get a Hibernate session factory.
     * 
     * @return a SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Close SessionFactory.
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}