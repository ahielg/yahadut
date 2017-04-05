package ayeleyHashahar.util;

import ayeleyHashahar.cons.MailSenderCons;
import ayeleyHashahar.sender.MailSender;

import java.io.*;
import java.util.Properties;

/**
 * @author ahiel
 * @date 06/11/12 23:39
 */
public class FileUtils {

    private static String dynamicPropertiesLocation = null;

    public static void gotoLineInFile(BufferedReader in, long lineNum) throws IOException {
        for (int i = 0; i < lineNum; i++, in.readLine()) {
        }
    }

    public static void loadPropertiesFiles() {
        //Handle properties file
        InputStream resourceAsStream = MailSender.class.getResourceAsStream(MailSenderCons.PROP_FILE);
        //Thread.currentThread().getContextClassLoader().getResourceAsStream(MailSenderCons.MAIL_PROPERTIES_FILE_NAME);
        try {
            MailSenderCons.properties.load(resourceAsStream);
            String baseDir = MailSenderCons.properties.getProperty(MailSenderCons.BASE_DIR);
            String defaultFile = MailSenderCons.properties.getProperty(MailSenderCons.DEFAULT_PROPERTIES_FILE_NAME);
            if (baseDir == null) {
                baseDir = System.getProperty("user.home");
            }
            if (defaultFile == null) {
                defaultFile = "preferences.properties";
            }
            dynamicPropertiesLocation = baseDir + '/' + defaultFile;
            try {

                MailSenderCons.dynamic_properties.load(new FileInputStream(dynamicPropertiesLocation));
            } catch (FileNotFoundException e) {

                File dir = new File(baseDir);
                File propertiesFile = new File(dir, defaultFile);
                FileOutputStream fileOutputStream = new FileOutputStream(propertiesFile);


                fileOutputStream.close();
                MailSenderCons.dynamic_properties.load(new FileInputStream(dynamicPropertiesLocation));
                MailSenderCons.dynamic_properties.setProperty(MailSenderCons.SEND_MAIL_TO, MailSenderCons.properties.getProperty(MailSenderCons.SEND_MAIL_TO));
                MailSenderCons.dynamic_properties.setProperty(MailSenderCons.SEND_MAIL_TO_DEBUG, MailSenderCons.properties.getProperty(MailSenderCons.SEND_MAIL_TO_DEBUG));
                MailSenderCons.dynamic_properties.setProperty(MailSenderCons.IS_THURSDAY_LAST_DAY,
                        MailSenderCons.properties.getProperty(MailSenderCons.IS_THURSDAY_LAST_DAY));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String goToNextNotEmptyLine(BufferedReader in) throws IOException {
        String line;
        while ((line = in.readLine().trim()).isEmpty()) {
        }
        return line;
    }


    /**
     * Description of the Method
     *
     * @param fileName The file to be turned into a String
     * @return The file as String encoded in the platform
     * default encoding
     */
    public static String fileToString(String fileName) throws IOException {
        FileReader in = new FileReader(fileName);
        StringWriter w = new StringWriter();
        char buffer[] = new char[2048];
        int n;
        while ((n = in.read(buffer)) != -1) {
            w.write(buffer, 0, n);
        }
        w.flush();
        in.close();
        return w.toString();
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void toFileContent(String s, File file) throws IOException {
        FileWriter f = new FileWriter(file);
        f.write(s);
        f.flush();
        f.close();
    }

    public static void savePropertiesFile(Properties dynamic_properties) throws IOException {
        dynamic_properties.store(new FileOutputStream(dynamicPropertiesLocation), "AutoSave");
    }

    public static long getLongFromProperties(String lineProp) {
        long musarLineNum = 0;
        try {
            musarLineNum = Long.parseLong(MailSenderCons.dynamic_properties.getProperty(lineProp));
        } catch (Exception ignored) {
        }
        return musarLineNum;
    }
}
