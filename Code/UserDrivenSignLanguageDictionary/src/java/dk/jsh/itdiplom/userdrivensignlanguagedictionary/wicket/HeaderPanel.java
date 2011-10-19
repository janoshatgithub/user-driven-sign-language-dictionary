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
    public HeaderPanel(String componentName, String exampleTitle)
    {
        super(componentName);
             add(new Label("exampleTitle", exampleTitle));
    }
}
