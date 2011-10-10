/*
 * WicketExamplePage.java
 *
 * Created on 9. oktober 2011, 16:07
 */
 
package dk.jsh.itdiplom.userdrivensignlanguagedictionary.wicket;           

import org.apache.wicket.markup.html.WebPage;

/** 
 *
 * @author hansejan
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        add(new HeaderPanel("headerpanel", "Welcome To Wicket")); 
        add(new FooterPanel("footerpanel", "Powered by Wicket and the NetBeans Wicket Plugin"));
    } 

}
