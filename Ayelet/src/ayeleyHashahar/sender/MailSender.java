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

/**
 * User: ahiel
 * Date: 19/07/12
 * Time: 23:30
 */
public class MailSender {
    private MailSender() {
    }

    public static void sendMail(String body, String[] to, String subject, String pass) throws Exception {
        String host = "smtp.gmail.com";
        String from = MailSenderCons.properties.getProperty(MailSenderCons.MAIL_USERNAME).replace('#','@');
        if (pass == null) {
            pass = LocalEncrypter.decrypt(MailSenderCons.properties.getProperty(MailSenderCons.MAIL_PASSWORD));
        }
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true"); // added this line
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        //String[] to = getMailAddresses();

        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        // To get the array of addresses
        for (String address : to) { // changed from a while loop
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(address));
        }

        String holiday = new RegularHebrewDate().getHoliday();
        if (!"".equals(holiday)) {
            holiday = holiday + ", ";
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
        return senders.replace('#','@').split(",");
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
