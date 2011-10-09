/*
 * HomePage.java
 *
 * Created on 9. oktober 2011, 15:51
 */

package dk.jsh.itdiplom.user-driven-sign-language-dictionary;           

import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends BasePage {

    public HomePage() {
        add(new Label("message", "Hello, World!"));
    }

}
