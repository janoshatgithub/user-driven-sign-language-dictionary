/*
 * Application.java
 *
 * Created on 9. oktober 2011, 16:07
 */
 
package dk.jsh.itdiplom.userdrivensignlanguagedictionary;           

import org.apache.wicket.protocol.http.WebApplication;
/** 
 *
 * @author hansejan
 * @version 
 */

public class Application extends WebApplication {

    public Application() {
    }

    public Class getHomePage() {
        return HomePage.class;
    }

}
