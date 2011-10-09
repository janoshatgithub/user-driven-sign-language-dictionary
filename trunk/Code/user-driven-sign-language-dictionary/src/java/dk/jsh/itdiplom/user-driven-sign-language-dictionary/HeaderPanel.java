/*
 * HeaderPanel.java
 *
 * Created on 9. oktober 2011, 15:51
 */
 
package dk.jsh.itdiplom.user-driven-sign-language-dictionary;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author hansejan
 * @version 
 */

public class HeaderPanel extends Panel {

    /**
     * Construct.
     * @param componentName name of the component
     * @param exampleTitle title of the example
     */

    public HeaderPanel(String componentName, String exampleTitle)
    {
        super(componentName);
        add(new Label("exampleTitle", exampleTitle));
    }

}
