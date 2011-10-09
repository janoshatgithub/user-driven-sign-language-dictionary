/*
 * HomePage.java
 *
 * Created on 9. oktober 2011, 16:07
 */

package dk.jsh.itdiplom.userdrivensignlanguagedictionary;           

import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends BasePage {

    public HomePage() {
        add(new Label("message", "Hello, World!"));
    }

}
