package dk.jsh.itdiplom.userdrivensignlanguagedictionary.business;

import dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.WordGroup;
import dk.jsh.itdiplom.userdrivensignlanguagedictionary.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Business metods for word groups. 
 * 
 * @author Jan S. Hansen
 */
public class WordGroupBusiness {
    private WordGroupBusiness() {}
    
    /**
     * Get all word groups.
     * 
     * @return A list of WordGroup.
     */
    public static List<WordGroup> getAllWordGroups() {
        List<WordGroup> wordGroupList = new ArrayList<WordGroup>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = 
                  "select wordGroup from "
                + "dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity."
                + "WordGroup wordGroup "
                + "order by wordGroup.name";
        Query query = session.createQuery(hql);
        wordGroupList = query.list();
        session.close();
        return wordGroupList;
    }
    
    /**
     * Search for groups.
     * 
     * @param search search string
     * @return a list of word groups that match the search criteria
     */
    public static List<WordGroup> search(String search) {
        List<WordGroup> wordGroupList = new ArrayList<WordGroup>();
        search = search.toLowerCase();
        search = search.replace("*", "%");
        search = search.replace("?", "_");
        boolean useLike = false;
        if (search.indexOf("%") != -1 || search.indexOf("_") != -1) {
            useLike = true;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        StringBuilder hql = new StringBuilder();
        hql.append("select wordGroup from ");
        hql.append("dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity.");
        hql.append("WordGroup wordGroup ");
        if (useLike) {
            hql.append("where lower(wordGroup.name) like :search ");
        }
        else {
            hql.append("where lower(wordGroup.name) = :search ");
        }
        hql.append("order by wordGroup.name");
        Query query = session.createQuery(hql.toString());
        query.setString("search", search);
        wordGroupList = query.list();
        session.close();
        return wordGroupList;
    }
}