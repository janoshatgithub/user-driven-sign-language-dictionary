package dk.jsh.itdiplom.userdrivensignlanguagedictionary.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * E-Mail sender. Singleton pattern.
 *
 * @author Jan S. Hansen
 */
public class EMailSender {
    private static Properties emailProperties = new Properties();
    private static EMailSender singletonInstance; 
    
    /**
     * Private constructor to prevent use of new keyword outside this class.
     */
    private EMailSender(){};
    
    public static EMailSender getInstance() {
        if (singletonInstance == null) {
            //TODO: Get host from a property file.
            emailProperties.put("mail.smtp.host", "localhost"); 
            singletonInstance = new EMailSender();
        }
        return singletonInstance;
    }
    
    /**
     * Send an email.
     * 
     * @param fromEmailAddr from e-mail address
     * @param toEmailAddr to e-mail adress
     * @param subject e-mail subject
     * @param body e-mail body text
     * @return 
     */
    public boolean sendEmail(String fromEmailAddr, String toEmailAddr,
                    String subject, String body) {
        Session session = Session.getDefaultInstance(emailProperties, null);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmailAddr));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                toEmailAddr));

            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            System.err.println("Cannot send email. " + ex);
            return false;
        }
    }
    
    /**
     * Send an no-reply email.
     * 
     * @param toEmailAddr to e-mail adress
     * @param subject e-mail subject
     * @param body e-mail body text
     * @return 
     */
    public boolean sendNoReplyEmail(String toEmailAddr, String subject, 
            String body) {
        return sendEmail("NoReply@NoReply.dk", toEmailAddr, subject, body);
    }
}