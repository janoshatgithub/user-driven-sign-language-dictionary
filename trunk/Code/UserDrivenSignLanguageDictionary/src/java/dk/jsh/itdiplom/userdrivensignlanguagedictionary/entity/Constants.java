package dk.jsh.itdiplom.userdrivensignlanguagedictionary.entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Constants and enums.
 *
 * @author Jan S. Hansen
 */
public class Constants {
    private Constants() {}

     /**
     * BaRI user role: ADMIN, DEVELOPER, NORMAL.
     */
    public enum UserRole {
        ADMIN("Administrator"),
        NORMAL("Alm. bruger");

        private String name;

        UserRole(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static List<String> getNames() {
            List<String> names = new ArrayList<String>();
            names.add(ADMIN.name);
            names.add(NORMAL.name);
            return names;
        }

        public static UserRole getName(String name) {
            if (ADMIN.name.equals(name)) {
                return ADMIN;
            }
            return NORMAL;
        }
     }
}