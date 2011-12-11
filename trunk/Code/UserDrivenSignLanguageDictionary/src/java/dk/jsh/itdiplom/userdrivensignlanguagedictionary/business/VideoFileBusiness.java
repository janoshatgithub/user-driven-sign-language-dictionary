package dk.jsh.itdiplom.userdrivensignlanguagedictionary.business;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.VideoFile;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Business metods for VideoFile.
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
    
    /**
     * Get all video files for a word.
     * 
     * @return A list of vidoe files
     */
    public static List<VideoFile> getAllVideoFilesForAWord(Word word) {
        List<VideoFile> videoFileList = new ArrayList<VideoFile>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = 
                  "select videofile from "
                + "dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity."
                + "VideoFile videofile "
                + "where videofile.toWord.id = :wordid "
                + "order by videofile.uploadedDateTime desc";
        Query query = session.createQuery(hql);
        query.setLong("wordid", word.getId());
        videoFileList = query.list();
        session.close();
        return videoFileList;
    }
}