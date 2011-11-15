package dk.jsh.itdiplom.userdrivensignlanguagedictionary.business;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.VideoFile;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Business metods for File.
 * 
 * @author Jan S. Hansen
 */
public class VideoFileBusiness {
    private VideoFileBusiness(){};
    
    /**
     * Persist a new video file
     * 
     * @param newWord a new video file
     */
    public static void saveNew(VideoFile newFile) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(newFile);
        tx.commit();
        session.close();
    }    
}