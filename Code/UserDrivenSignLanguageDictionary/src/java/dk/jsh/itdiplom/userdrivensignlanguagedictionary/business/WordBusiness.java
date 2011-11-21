package dk.jsh.itdiplom.userdrivensignlanguagedictionary.business;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.ApplicationUser;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.Word;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.WordGroup;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
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
    
    /**
     * Test if a word exists.
     * 
     * @param word word to test
     * @return true is the word exists 
     */
    public static boolean isWordInUse(String word) {
        boolean inUse = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select word "
                + "from dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity."
                + "Word word "
                + "where word.word = :word";
        Query query = session.createQuery(hql);
        query.setString("word", word);
        if (query.list().isEmpty()) {
            inUse = false;
        }
        else {
            inUse = true;
        };
        session.close();
        return inUse;
    }
    
    /**
     * Get all word created by a specific user.
     * 
     * @return A list of Word.
     */
    public static List<Word> getAllWordsCreatedByUser(ApplicationUser user) {
        List<Word> wordList = new ArrayList<Word>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = 
                  "select word from "
                + "dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity."
                + "Word word "
                + "where word.requestCreatedBy.id = :userid "
                + "order by word.word";
        Query query = session.createQuery(hql);
        query.setLong("userid", user.getId());
        wordList = query.list();
        session.close();
        return wordList;
    }
    
    /**
     * Search for words.
     * 
     * @param search search string
     * @return a list of words that match the search criteria
     */
    public static List<Word> search(String search) {
        List<Word> wordList = new ArrayList<Word>();
        search = search.toLowerCase();
        search = search.replace("*", "%");
        search = search.replace("?", "_");
        boolean useLike = false;
        if (search.indexOf("%") != -1 || search.indexOf("_") != -1) {
            useLike = true;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        StringBuilder hql = new StringBuilder();
        hql.append("select word from ");
        hql.append("dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.");
        hql.append("Word word ");
        if (useLike) {
            hql.append("where lower(word.word) like :search ");
        }
        else {
            hql.append("where lower(word.word) = :search ");
        }
        hql.append("order by word.word");
        Query query = session.createQuery(hql.toString());
        query.setString("search", search);
        wordList = query.list();
        session.close();
        return wordList;
    }

    /**
     * Search for words with specific groups.
     * 
     * @param search search string
     * @return a list of words that match the search criteria
     */
    public static List<Word> search(List<WordGroup> wordGroups) {
        List<Word> wordList = new ArrayList<Word>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        StringBuilder hql = new StringBuilder();
        hql.append("select word from ");
        hql.append("dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.");
        hql.append("Word word ");
        hql.append("where word.wordGroups.wordid in (");
        boolean first = true;
        for (WordGroup wordGroup : wordGroups) {
            if (first) {
                first = false;
            }
            else {
                hql.append(", ");
            }
            hql.append(wordGroup.getId());
        }
        hql.append(") ");
        hql.append("order by word.word");
        Query query = session.createQuery(hql.toString());
        wordList = query.list();
        session.close();
        return wordList;
    }
}