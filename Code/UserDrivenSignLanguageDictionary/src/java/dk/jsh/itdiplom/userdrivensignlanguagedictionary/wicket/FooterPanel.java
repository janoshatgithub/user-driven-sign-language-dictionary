package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 * Footer panel.
 * 
 * @author Jan S. Hansen
 */
public final class FooterPanel extends Panel {

    public FooterPanel(String id, String text) {
        super(id);
        add(new Label("footerpanel_text", text));
    }
}