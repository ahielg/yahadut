package ayeleyHashahar.generator;

import ayeleyHashahar.cons.MailSenderCons;
import ayeleyHashahar.generator.generators.*;
import ayeleyHashahar.parameters.GeneratorParameters;
import ayeleyHashahar.parameters.MailGeneratorProperties;
import ayeleyHashahar.util.FileUtils;
import hebDate.HebrewDate;
import hebDate.RegularHebrewDate;
import hebrewDate.CalendarDate;
import hebrewDate.CalendarUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;

/**
 * User: ahiel
 * Date: 06/11/12
 * Time: 23:47
 */
public class TextGeneratorMerger {
    private static TextGeneratorMerger instance = new TextGeneratorMerger();
    private static String AYELET_HASHACHAR = "איילת השחר";
    HashSet<TextGenerator> textGenerators;


    private TextGeneratorMerger() {
        textGenerators = new HashSet<TextGenerator>();
        textGenerators.add(new ParashaGenerator());
        textGenerators.add(new HalachaGenerator());
        textGenerators.add(new MusarLashonNew());
        textGenerators.add(new VetenChelkenu());
        textGenerators.add(new HolidaysGenerator());
        //textGenerators.add(new MusarMitzvotGenerator());
        if (MailSenderCons.dynamic_properties.size() == 0) {
            FileUtils.loadPropertiesFiles();
        }
    }

    public static synchronized TextGeneratorMerger getInstance() {
        return instance;
    }

    public static GeneratorParameters createGeneratorParameters(MailGeneratorProperties mailGeneratorProperties) {

        String baseDir = MailSenderCons.properties.getProperty(MailSenderCons.BASE_DIR);
        String halachaFile;
        if (!new HebrewDate(mailGeneratorProperties.getDate()).isHebrewLeapYear()) {
            halachaFile = baseDir + MailSenderCons.properties.getProperty(MailSenderCons.HALACHA_TEMPLATE_LOCATION);
        } else {
            halachaFile = baseDir + MailSenderCons.properties.getProperty(MailSenderCons.HALACHA_TEMPLATE_LOCATION_ADAR_A);
        }
        String musarFile = baseDir + MailSenderCons.properties.getProperty(MailSenderCons.MUSAR_TEMPLATE_LOCATION);
        String parashaFile = baseDir + MailSenderCons.properties.getProperty(MailSenderCons.PARASHA_TEMPLATE_LOCATION);
        String holidaysFile = baseDir + MailSenderCons.properties.getProperty(MailSenderCons.HOLIDAYS_TEMPLATE_LOCATION);


        int nextParasha = RegularHebrewDate.getNextParashaNumOnShabat(mailGeneratorProperties.getDate());
        int nextHoliday = RegularHebrewDate.getNextHolidayNum(mailGeneratorProperties.getDate(), 7);


        if (nextParasha == -1) {
            //mailGeneratorProperties.setParasha(false);
            System.out.println("חג שמח....");
        }

        long parashaLine = FileUtils.getLongFromProperties(MailSenderCons.PARASHA_LINE + nextParasha);
        long holidayLine = FileUtils.getLongFromProperties(MailSenderCons.HOLIDAY_LINE + nextHoliday);
        long musarLineNum = FileUtils.getLongFromProperties(MailSenderCons.MUSAR_LINE);
        long isThursdayLastDay = FileUtils.getLongFromProperties(MailSenderCons.IS_THURSDAY_LAST_DAY);


        return new GeneratorParameters(halachaFile,
                musarFile,
                parashaFile,
                holidaysFile,
                nextParasha,
                nextHoliday,
                musarLineNum,
                parashaLine,
                holidayLine,
                isThursdayLastDay != 0);
    }

    public static String setPage(MailGeneratorProperties mailGeneratorProperties) throws Exception {
        RegularHebrewDate regularHebrewDate = new RegularHebrewDate(mailGeneratorProperties.getDate());

        mailGeneratorProperties.updateKotarot();

        String fileName = MailSenderCons.properties.getProperty(MailSenderCons.BASE_DIR) + '\\' + MailSenderCons.properties.getProperty(MailSenderCons.MAIL_TEMPLATE_LOCATION);
        String page = FileUtils.fileToString(fileName);

        String holiday = regularHebrewDate.getHoliday();
        if (!"".equals(holiday)) {
            holiday = "<span style=\"color:RED\">" + holiday + "</span>, ";
        }

        String hodaot = mailGeneratorProperties.getHodaot();

        CalendarDate hebCalDate = CalendarUtils.gregorian2Jewish(mailGeneratorProperties.getDate());
        if ((hebCalDate.getDay() == 1) || (hebCalDate.getDay() == 30)) {
            hodaot = "חודש טוב ומבורך. ";
        }


        if (mailGeneratorProperties.getMusarTextToSend().length() < 100 &&
                mailGeneratorProperties.getParashaTextToSend().length() < 100 &&
                mailGeneratorProperties.getHolidayTextToSend().length() < 100 &&
                mailGeneratorProperties.getHalachaTextToSend().length() < 100) {
            System.out.println("אין מה לשלוח");
            System.out.println("holiday: " + mailGeneratorProperties.getHolidayTextToSend());
            System.out.println("halacha: " + mailGeneratorProperties.getHalachaTextToSend());
            System.out.println("parasha: " + mailGeneratorProperties.getParashaTextToSend());
            System.out.println("musar: " + mailGeneratorProperties.getMusarTextToSend());
        }

        if (mailGeneratorProperties.getDate().get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
                || mailGeneratorProperties.getDate().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            if (hodaot != null && hodaot.equals(mailGeneratorProperties.getHodaot())) {
                hodaot = "";
            }
            hodaot += " " + RegularHebrewDate.getCandleAndHavdala();
        }


        int parashaBaa = RegularHebrewDate.getNextParashaNumOnShabat(mailGeneratorProperties.getDate());

        String hebDate = CalendarUtils.parseHebDate(hebCalDate);
        if (parashaBaa != -1) {
            hebDate += ", " + holiday + RegularHebrewDate.getParashaAsString(parashaBaa);
        }
        if (mailGeneratorProperties.getKoteretMusar().isEmpty() && mailGeneratorProperties.getKoteretParasha().isEmpty()) {
            if (mailGeneratorProperties.getHolidayTextToSend().length() < 100) {
                //handle halacha only mail
                if (mailGeneratorProperties.getHalachaTextToSend().length() < 200) {
                    System.out.println("אין מה לשלוח");
                    System.out.println("halacha: " + mailGeneratorProperties.getHalachaTextToSend());
                }

                hodaot = "<span style=\"font-size:24px\">" + hebDate + "</span>";
                hebDate = "";
                mailGeneratorProperties.setKoteretParasha(mailGeneratorProperties.getKoteretHalachaYomit());
                mailGeneratorProperties.setKoteretHalachaYomit("");
                mailGeneratorProperties.setParashaTextToSend(mailGeneratorProperties.getHalachaTextToSend());
                mailGeneratorProperties.setHalachaTextToSend("ההלכות מתוך הספר \"הלכה יומית - שנה ב'\" בעריכתו של הרב ליאור בר-דע שליט\"א." +
                        "<BR/>" +
                        "את הספר הלכה יומית ניתן להשיג בטלפונים 02-9973696 או 03-6748786");
            }
        } else {
            hebDate = "<a href=\"http://www.yeshiva.org.il/calendar/\" style=\"text-decoration:none;color:#999\">" + hebDate + "</a>";
        }

        int templateLength = page.length();

        String completePage = page.replace("_KOTERET_PARASHA_", mailGeneratorProperties.getKoteretParasha())
                .replace("_KOTERET_HALACHA_", mailGeneratorProperties.getKoteretHalachaYomit())
                .replace("_KOTERET_MUSAR_", mailGeneratorProperties.getKoteretMusar())
                .replace("_HALACHA_", mailGeneratorProperties.getHalachaTextToSend())
                .replace("_MUSAR_", mailGeneratorProperties.getMusarTextToSend())
                .replace("_PARASHA_", mailGeneratorProperties.getParashaTextToSend() + mailGeneratorProperties.getHolidayTextToSend())
                .replace("_SITE_ADDRESS_", mailGeneratorProperties.getVetenLink())
                .replace("_HEBDATE_", hebDate)
                .replace("_HODAOT_", hodaot);

        if (completePage.length() < templateLength + 200) {
            System.out.println("חלה תקלה בשליחת המייל אנא בדוק שנית.");
            System.out.println("completePage = " + completePage);
        }

        return completePage;
    }

    public String generateMailSubject(MailGeneratorProperties mailGeneratorProperties) throws IOException {
        RegularHebrewDate regularHebrewDate = new RegularHebrewDate(mailGeneratorProperties.getDate());
        String subject = AYELET_HASHACHAR + " - ";//MailSenderCons.properties.getProperty(MailSenderCons.NOSE)
        subject = "";
        int parashaBaa = RegularHebrewDate.getNextParashaNumOnShabat(mailGeneratorProperties.getDate());
        int nextHolidayNum = RegularHebrewDate.getNextHolidayNum(mailGeneratorProperties.getDate(), 7);

        String holiday = regularHebrewDate.getHoliday();
        boolean isHoliday = false;
        if (!holiday.isEmpty()) {
            subject += holiday;
            isHoliday = true;
        }

        if (parashaBaa != -1) {
            if (isHoliday) {
                subject += " ו";
            }

            subject += RegularHebrewDate.getParashaAsString(parashaBaa) + " - " + "יום " + RegularHebrewDate.getDayInWord(mailGeneratorProperties.getDate());
        } else if (nextHolidayNum != -1 && !isHoliday) {
            subject += RegularHebrewDate.getImportantHolidayByNum(nextHolidayNum);
        } else if (!isHoliday) {
            subject = AYELET_HASHACHAR;
        }
/*
        //CalendarDate hebCalDate = CalendarUtils.gregorian2Jewish(mailGeneratorProperties.getDate());
        if ((hebCalDate.getDay() == 1) || (hebCalDate.getDay() == 30)) {
             subject +=  "ראש חודש";
        }
*/
        return subject;
    }

    public String generateAllTexts(MailGeneratorProperties mailGeneratorProperties) throws IOException {
        RegularHebrewDate regularHebrewDate = new RegularHebrewDate(mailGeneratorProperties.getDate());

        if (regularHebrewDate.isShabatonHoliday()) {
            throw new IllegalStateException("חג שמח, אין לשלוח הודעות");
        }

        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            throw new IllegalStateException("שבת שלום, אין לשלוח הודעות");
        }

        boolean isThursdayLastDay = false;
        try {
            isThursdayLastDay = FileUtils.getLongFromProperties(MailSenderCons.IS_THURSDAY_LAST_DAY) != 0;
        } catch (Exception ignored) {
        }

        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && isThursdayLastDay) {
            throw new IllegalStateException("מוגדר לא לשלוח הודעות בימי שישי");
        }


        for (TextGenerator t : textGenerators) {
            if (t.isToCheck(mailGeneratorProperties)) {
                t.generateText(createGeneratorParameters(mailGeneratorProperties), mailGeneratorProperties);
            }
        }

        //save the properties that changed
        FileUtils.savePropertiesFile(MailSenderCons.dynamic_properties);

        String allText = "";
        try { //update all mail properties
            allText = setPage(mailGeneratorProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allText;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clone is not allowed.");
    }
}
