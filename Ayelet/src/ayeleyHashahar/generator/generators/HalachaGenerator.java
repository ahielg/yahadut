package ayeleyHashahar.generator.generators;

import ayeleyHashahar.cons.MailSenderCons;
import ayeleyHashahar.generator.TextGenerator;
import ayeleyHashahar.parameters.GeneratorParameters;
import ayeleyHashahar.parameters.MailGeneratorProperties;
import ayeleyHashahar.util.FileUtils;
import hebDate.HebrewDate;
import hebDate.RegularHebrewDate;
import hebrewDate.CalendarUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author ahiel
 * @date 7/11/12 00:06
 */
public class HalachaGenerator implements TextGenerator {
    private static String parseHalachaDate(String dateToParse) {
        String[] todayArray = dateToParse.split(" ");

        dateToParse = todayArray[0] + " ב" + todayArray[1];
        if (todayArray.length > 3) {
            dateToParse = dateToParse + ' ' + todayArray[2];
        }
        dateToParse = dateToParse.replace("כסליו", "כסלו");
        return dateToParse;
    }

    @Override
    public boolean isToCheck(MailGeneratorProperties mailGeneratorProperties) {
        return mailGeneratorProperties.isHalacha();
    }

    private static boolean isSelectedDay(String today, String line) {
        return (line.trim().contains(today) && (line.trim().contains("הלכה יומית") || line.trim().contains("ראש חודש")))
                || (line.trim().endsWith(today));
    }

    @Override
    public void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException {
        Calendar calendar = (Calendar) mailGeneratorProperties.getDate().clone();
        Calendar currDate = (Calendar) mailGeneratorProperties.getDate().clone();

        HebrewDate hebDate = new HebrewDate(calendar);
        String today = hebDate.getHebrewDateAsHebStringWithoutYear().replace("כסליו", "כסלו");
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        RegularHebrewDate hebrewDate = new RegularHebrewDate();
        hebrewDate.setDate(calendar);

        while ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || hebrewDate.isShabatonHoliday()
                || (params.isThursdayLastDay() && (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY))) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            hebrewDate.setDate(calendar);
        }

        String nextDay = hebrewDate.getHebrewDateAsHebStringWithoutYear().replace("כסליו", "כסלו");

        File inputFile = new File(params.getHalachaFilename());
        BufferedReader in = new BufferedReader(new FileReader(inputFile));
        String line;

        StringBuilder fullString = new StringBuilder(2000);

        while ((line = in.readLine()) != null && (!isSelectedDay(today, line))) {
            System.out.println(line);
        }

        line = FileUtils.goToNextNotEmptyLine(in);
        //halacha topic
        fullString.append("<span style=\"font-size:18px;font-weight: bold\">").append(line).append("</span><br/>\n");
        fullString.append("<span style=\"font-size:12px;font-style: italic\">").append(in.readLine().trim()).append("</span><br/>\n");
        boolean endDay = false;
        currDate.add(Calendar.DAY_OF_MONTH, 1);
        String searchMe = parseHalachaDate(CalendarUtils.parseHebDate(CalendarUtils.gregorian2Jewish(currDate)));
        while ((line = in.readLine()) != null && (!(line.trim().contains(nextDay)))) {

            if (line.trim().contains(searchMe)) { // new day - when we send multiple days
                endDay = false;
                fullString.append("<BR/><BR/><BR/>\n\n");
                currDate.add(Calendar.DAY_OF_MONTH, 1);
                searchMe = parseHalachaDate(CalendarUtils.parseHebDate(CalendarUtils.gregorian2Jewish(currDate)));
            }

            if (!endDay) {
                if (line.contains("------") || line.contains("תרי\"ג היום")) {
                    endDay = true;
                } else {
                    fullString.append(line.trim()).append("<BR/>\n");
                }
            }
        }

        if (mailGeneratorProperties.isParasha()) {
            fullString.append("<span style=\"font-size:12px;font-style: italic\"><br/><br/>").append(MailSenderCons.HALACHA_REF).append("<br/></span>\n");
        }

        mailGeneratorProperties.setHalachaTextToSend(fullString.toString());
    }

}
