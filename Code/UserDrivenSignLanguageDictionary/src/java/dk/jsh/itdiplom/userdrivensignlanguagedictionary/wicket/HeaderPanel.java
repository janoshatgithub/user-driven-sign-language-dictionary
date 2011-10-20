package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 * Header panel.
 * 
 * @author Jan S. Hansen
 */
public class HeaderPanel extends Panel {

    /**
     * Construct.
     * @param componentName name of the component
     * @param exampleTitle title of the example
     */
    public HeaderPanel(String componentName, String userName)
    {
        super(componentName);
        String text = "Bruger: ";
        if (userName != null) {
            text += userName;
        }
        else {
            text += "Ikke logget på";
        }
        add(new Label("userName", text));
    }
}
