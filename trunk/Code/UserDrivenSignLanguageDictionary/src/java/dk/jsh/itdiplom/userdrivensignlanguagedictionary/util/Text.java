package dk.jsh.itdiplom.userdrivensignlanguagedictionary.util;

import java.util.List;

/**
 * Text utilities
 * 
 * @author Jan S. Hansen
 */
public class Text {
    
    /**
     * Make at list of word groups.
     * 
     * @param wordGroupList a WordGroupList
     * @return a string of wordGroups
     */
    public static String makeWordGroupString(List<String> wordGroupList) {
        StringBuilder groups = new StringBuilder();
        int noOfGroups = wordGroupList.size();
        if (noOfGroups > 0) {
            for (int i = 0; i < noOfGroups; i++) {
                String wordGroup = wordGroupList.get(i);
                if (i > 0 && i < noOfGroups - 1) {
                    groups.append(", ");
                }
                else if (i == noOfGroups -1) {
                    groups.append(" og ");
                }
                groups.append(wordGroup);
            }
        }
        else {
            groups.append("Ikke tilknyttet nogen gruppe");
        }
        groups.append(".");
        return groups.toString();
    }
}