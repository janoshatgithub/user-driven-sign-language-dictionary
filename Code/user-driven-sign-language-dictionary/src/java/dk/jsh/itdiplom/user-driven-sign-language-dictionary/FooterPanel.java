/*
 * FooterPanel.java
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

public final class FooterPanel extends Panel {

    public FooterPanel(String id, String text) {
        super(id);
        add(new Label("footerpanel_text", text));
    }

}
