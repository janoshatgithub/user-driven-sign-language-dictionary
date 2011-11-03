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
}