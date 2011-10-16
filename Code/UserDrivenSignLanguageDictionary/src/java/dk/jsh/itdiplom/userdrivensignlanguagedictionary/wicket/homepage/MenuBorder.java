package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket.homepage;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;

/**
 * Border component.
 * 
 * @author Jan S. Hansen
 */
public class MenuBorder extends Border
{
    /**
     * Constructor
     * 
     * @param id
     *            The id of this component
     */
    public MenuBorder(final String id)
    {
        super(id);
        add(new BoxBorder("navigationBorder"));
        add(new BoxBorder("bodyBorder"));
    }
}