/*
 * FooterPanel.java
 *
 * Created on 9. oktober 2011, 16:07
 */
 
package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author hansejan
 * @version 
 */

public final class FooterPanel extends Panel {

    public FooterPanel(String id, String text) {
        super(id);
        add(new Label("footerpanel_text", text));
    }

}
