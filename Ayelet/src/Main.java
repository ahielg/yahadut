import ayeleyHashahar.generator.TextGeneratorMerger;
import ayeleyHashahar.parameters.MailGeneratorProperties;
import ayeleyHashahar.sender.MailSender;
import ayeleyHashahar.util.FileUtils;
import ayeleyHashahar.util.LocalEncrypter;
import hebDate.RegularHebrewDate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static ayeleyHashahar.cons.MailSenderCons.*;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean isMail = true;
        boolean isHalacha = true;
        boolean isDemo = false;
        String messages = null;
        String pass = null;
                                          /*
        Calendar a1= Calendar.getInstance();
        a1.set(2013,Calendar.MARCH,24);
        String s1 = RegularHebrewDate.getNextHoliday(a1);

        System.out.println(s1);
        if (1==1) return;*/

        //args = new String[]{ALL_PARAM};
        //args = new String[]{ALL_PARAM, MSG_PARAM, "לרפואת בנימין בן מאי נעימה"};
        //args = new String[]{ALL_PARAM, MSG_PARAM, "מחפשים וורטים או סיפור חסידי לשבת ? אתם מוזמנים לבקר באתר : http://morbas.blogspot.co.il"};
        //args = new String[]{DEMO_PARAM};

        if (args.length != 0) {
            List<String> a = Arrays.asList(args);
            if (a.contains(DEMO_PARAM)) {
                isDemo = true;
            }

            if (a.contains(HALACHA_ONLY_PARAM)) {
                isHalacha = true;
                isMail = false;
            } else if (a.contains(ALL_PARAM)) {
                isHalacha = true;
            } else if (a.size() == 1 && !a.get(0).equals(DEMO_PARAM)) { //Encrypt the password
                System.out.println("Encrypted '" + args[0] + "' is: " + LocalEncrypter.encrypt(args[0]));
                return;
            }
            if (a.contains(PASS) && a.size() > a.indexOf(PASS)) {
                pass = a.get(a.indexOf(PASS) + 1);
            }
            if (a.contains(MSG_PARAM) && a.size() > a.indexOf(MSG_PARAM)) {
                for (int i = a.indexOf(MSG_PARAM) + 1; i < a.size() && !a.get(i).startsWith("-"); i++) {
                    messages += a.get(i) + ' ';
                }
            }
        }

        try {
            pass = LocalEncrypter.decrypt(pass);
        } catch (Exception ignored) {
        }

        Calendar instance = Calendar.getInstance();
        FileUtils.loadPropertiesFiles();

        MailGeneratorProperties mailGeneratorProperties = new MailGeneratorProperties(false, true, true, instance);
        if (messages != null) {
            mailGeneratorProperties.setHodaot(messages);
        }

        if (isDemo) {
            //instance.set(2013,Calendar.MARCH,24);
            RegularHebrewDate regularHebrewDate = new RegularHebrewDate(instance);
            System.out.println(regularHebrewDate.getHebrewDateAsHebString());
            mailGeneratorProperties = new MailGeneratorProperties(false, true, true, instance);
            if (messages != null) {
                mailGeneratorProperties.setHodaot(messages);
            }
            String subject = TextGeneratorMerger.getInstance().generateMailSubject(mailGeneratorProperties);
            System.out.println(subject);
            //mailGeneratorProperties = new MailGeneratorProperties(false, true, false, instance);
            //instance.add(Calendar.DAY_Oחג שמח....F_MONTH,2);
            String mail = TextGeneratorMerger.getInstance().generateAllTexts(mailGeneratorProperties);
            System.out.println(mail);
            return;
        }

        if (isMail) {
            sendAll(instance, messages, pass);
        }

        if (isHalacha) {
            sendHalachaOnly(instance, messages, pass);
        }


        /*ComplexZmanimCalendar telAvivToday = CalendarUtils.getTelAvivToday();
                StringBuilder s = new StringBuilder();
                telAvivToday.getSunset();
                System.out.println(telAvivToday.getCandelLighting());
                System.out.println(RegularHebrewDate.getCandleAndHavdala());;
                CityLocaleList cityLocaleList = new CityLocaleList(MailSender.class.getResource(MailSenderCons.LOCATIONS_FILE).getPath());
                CityLocale telAviv = cityLocaleList.getCityLocale(Cities.TelAviv);
                System.out.println(telAviv.getName());
        */
    }

    private static void sendHalachaOnly(Calendar instance, String hodaot, String pass) throws Exception {

        MailGeneratorProperties mailGeneratorProperties = new MailGeneratorProperties(false, true, false, instance);
        String[] mailAddresses = MailSender.getMailAddresses(SEND_MAIL_TO_HALACHA);
        String subject = sendMail(hodaot, mailGeneratorProperties, mailAddresses, pass);
        System.out.println("* Halacha Sent! [" + subject + "]");
    }

    private static void sendAll(Calendar instance, String hodaot, String pass) throws Exception {
        MailGeneratorProperties mailGeneratorProperties = new MailGeneratorProperties(false, true, true, instance);
        String[] mailAddresses = MailSender.getMailAddresses(SEND_MAIL_TO);

        String subject = sendMail(hodaot, mailGeneratorProperties, mailAddresses, pass);
        System.out.println("* Ayelet hashahr Sent! [" + subject + "]");
    }


    private static String sendMail(String hodaot, MailGeneratorProperties mailGeneratorProperties, String[] mailAddresses, String pass) throws Exception {
        if (hodaot != null) {
            mailGeneratorProperties.setHodaot(hodaot);
        }

        String mail = TextGeneratorMerger.getInstance().generateAllTexts(mailGeneratorProperties);
        String subject = TextGeneratorMerger.getInstance().generateMailSubject(mailGeneratorProperties);
        MailSender.sendMail(mail, mailAddresses, subject, pass);
        return subject;
    }

}
