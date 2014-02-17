package ayeleyHashahar.cons;

import java.util.Properties;

/**
 * User: ahiel
 * Date: 27/07/12
 * Time: 00:30
 */
public class MailSenderCons {

    public static Properties properties = new Properties();
    public static final String HALACHA_REF = "ההלכות מתוך הספר \"הלכה יומית\" בעריכתו של הרב ליאור בר-דע שליט\"א.";
    public static final String ADMIN_SITE = "https://sites.google.com/site/ahielg/system/app/pages/admin/attachments";
    public static final String GROUP_SITE = " https://groups.google.com/d/forum/ahielg?hl=iw";
    public static final String MAIL_ADDRESS = "ahielg@googlegroups.com";
    public static final String LOCATIONS_FILE = "/config/locations.properties";
    public static final String NOSE = "Nose";
    public static final String MAIL_USERNAME = "MailUsername";
    public static final String MAIL_PASSWORD = "MailPassword";
    public static final String MAIL_PROPERTIES_FILE_NAME = "mail.properties";
    public static final String PROP_FILE = "/config/" + MAIL_PROPERTIES_FILE_NAME;
    public static final String MAIL_TEMPLATE_LOCATION = "MailTemplateLocation";
    public static final String MUSAR_TEMPLATE_LOCATION = "MusarFileLocation";
    public static final String HALACHA_TEMPLATE_LOCATION = "HalachaFileLocation";
    public static final String HALACHA_TEMPLATE_LOCATION_ADAR_A = "HalachaFileLocationAdarA";
    public static final String BASE_DIR = "baseDir";
    public static final String PARASHA_TEMPLATE_LOCATION = "ParashaFileLocation";
    public static final String HOLIDAYS_TEMPLATE_LOCATION = "HolidaysFileLocation";
    public static final String PARASHA_TOPIC_NUMBERS = "parashaTopicNumbers";
    public static final String DEFAULT_PROPERTIES_FILE_NAME = "defaultPropertiesFileName";
    public static final String IS_THURSDAY_LAST_DAY = "isThursdayLastDay";
    /////////////////////////////////////////////////////////////////////////////////

    public static Properties dynamic_properties = new Properties();
    public static final String SEND_MAIL_TO = "SendMailTo";
    public static final String SEND_MAIL_TO_HALACHA = "SendMailToHalacha";
    public static final String SEND_MAIL_TO_DEBUG = "SendMailTo_debug";
    public static final String MUSAR_LINE = "musarLine";
    public static final String PARASHA_LINE = "parashaLine";
    public static final String HOLIDAY_LINE = "holidayLine";
    public static final String HALACHA_LINE = "halachaLine";

    ////////////////////////////////////////////
    public static final String PASS = "-p";
    public static final String MSG_PARAM = "-msg";
    public static final String ALL_PARAM = "-all";
    public static final String DEMO_PARAM = "-demo";
    public static final String HALACHA_ONLY_PARAM = "-h";

}
