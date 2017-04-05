package ayeleyHashahar.sender;

import ayeleyHashahar.cons.MailSenderCons;
import ayeleyHashahar.util.FileUtils;
import ayeleyHashahar.util.LocalEncrypter;
import hebDate.RegularHebrewDate;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @author ahiel
 * @date 19/07/12 23:30
 */
public class MailSender {
    private static final Pattern COMPILE = Pattern.compile(",");

    private MailSender() {
    }

    public static void sendMail(String body, String[] to, String subject, String pass) throws Exception {
        String host = "smtp.gmail.com";
        String from = MailSenderCons.properties.getProperty(MailSenderCons.MAIL_USERNAME).replace('#','@');

        if (pass == null) {
            pass = LocalEncrypter.decrypt(MailSenderCons.properties.getProperty(MailSenderCons.MAIL_PASSWORD));
        }
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.starttls.enable", "true"); // added this line
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.user", from);
        props.setProperty("mail.smtp.password", pass);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");

        //String[] to = getMailAddresses();

        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        message.addRecipient(Message.RecipientType.BCC, new InternetAddress(from));
        // To get the array of addresses
        for (String address : to) { // changed from a while loop
            //noinspection ObjectAllocationInLoop
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(address));
        }

        String holiday = new RegularHebrewDate().getHoliday();
        if (holiday != null && !holiday.isEmpty()) {
            holiday += ", ";
        }

        //message.setSubject("איילת השחר - " + holiday + RegularHebrewDate.getNextParsha());
        message.setSubject(subject);

        message.setText(body);
        message.setContent(body, "text/html; charset=UTF-8");
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static String[] getMailAddresses(String sendMailToPropertyName) {
        Properties dynamic_properties = MailSenderCons.dynamic_properties;
        String senders = dynamic_properties.getProperty(MailSenderCons.SEND_MAIL_TO_DEBUG);
        if (senders == null) {
            try {
                senders = FileUtils.fileToString(dynamic_properties.getProperty(sendMailToPropertyName));
            } catch (Exception e) {
                senders = dynamic_properties.getProperty(sendMailToPropertyName);
            }
        }
        return COMPILE.split(senders.replace('#', '@'));
    }
         /*
    public void saveDraftMessage(MimeMessage draftMessage) throws MessagingException {
        IMAPStore imapStore;
        Folder draftsMailBoxFolder = imapsStore.getFolder("[Gmail]/Drafts");//[Gmail]/Drafts
        draftsMailBoxFolder.open(Folder.READ_WRITE);
        draftMessage.setFlag(Flags.Flag.DRAFT, true);
        MimeMessage draftMessages[] = {draftMessage};
        draftsMailBoxFolder.appendMessages(draftMessages);
    }  */

}
