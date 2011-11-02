package dk.jsh.itdiplom.userdrivensignlanguagedictionary.business;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Business metods for Word. 
 * 
 * @author Jan S. Hansen
 */
public class WordBusiness {
     private WordBusiness(){};
     
    /**
     * Persist a new Word.
     * 
     * @param newWord a new Word
     */
    public static void saveNew(Word newWord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(newWord);
        tx.commit();
        session.close();
    }    

}