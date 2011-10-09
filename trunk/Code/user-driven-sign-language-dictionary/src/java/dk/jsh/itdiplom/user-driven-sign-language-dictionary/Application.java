/*
 * Application.java
 *
 * Created on 9. oktober 2011, 15:51
 */
 
package dk.jsh.itdiplom.user-driven-sign-language-dictionary;           

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
