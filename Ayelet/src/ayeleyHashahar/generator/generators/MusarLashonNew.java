package ayeleyHashahar.generator.generators;

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
 * User: ahiel
 * Date: 07/11/12
 * Time: 00:06
 */
public class MusarLashonNew implements TextGenerator {
    private static String parseHalachaDate(String dateToParse) {
        String[] todayArray = dateToParse.split(" ");

        dateToParse = todayArray[0] + ' ' + todayArray[1];
        if (todayArray.length > 3) {
            dateToParse = dateToParse + ' ' + todayArray[2];
        }
        return dateToParse;
    }

    @Override
    public boolean isToCheck(MailGeneratorProperties mailGeneratorProperties) {
        return mailGeneratorProperties.isMusar();
    }

    @Override
    public void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException {
        Calendar calendar = (Calendar) mailGeneratorProperties.getDate().clone();
        Calendar currDate = (Calendar) mailGeneratorProperties.getDate().clone();

        HebrewDate hebDate = new HebrewDate(calendar);
        String today = hebDate.getHebrewDateAsHebStringWithoutYear2();
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        RegularHebrewDate hebrewDate = new RegularHebrewDate();
        hebrewDate.setDate(calendar);

        while ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || hebrewDate.isShabatonHoliday()
                || (params.isThursdayLastDay() && (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY))) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            hebrewDate.setDate(calendar);
        }

        String nextDay = hebrewDate.getHebrewDateAsHebStringWithoutYear2();

        File inputFile = new File(params.getHalachaFilename());
        BufferedReader in = new BufferedReader(new FileReader(inputFile));
        String line;

        StringBuilder fullString = new StringBuilder();
        while ((line = in.readLine()) != null && (!(line.trim().contains(today)))) {
        }

        line = FileUtils.goToNextNotEmptyLine(in);
        //halacha topic
        fullString.append("<span style=\"font-size:18px;font-weight: bold\">").append(line).append("</span><br/>\n");
        fullString.append("<span style=\"font-size:12px;font-style: italic\">").append(in.readLine().trim()).append("</span><br/>\n");
//        boolean endDay = false;
        currDate.add(Calendar.DAY_OF_MONTH, 1);
        String searchMe = parseHalachaDate(CalendarUtils.parseHebDate(CalendarUtils.gregorian2Jewish(currDate)));
        while ((line = in.readLine()) != null && (!(line.trim().contains(nextDay)))) {

            if (line.trim().contains(searchMe)) { // new day - when we send multiple days
//                endDay = false;
                fullString.append("<BR/><BR/><BR/>\n\n");
                currDate.add(Calendar.DAY_OF_MONTH, 1);
                searchMe = parseHalachaDate(CalendarUtils.parseHebDate(CalendarUtils.gregorian2Jewish(currDate)));
            }

            //if (!endDay) {
                    fullString.append(line.trim()).append("<BR/>\n");
            //}
        }
        mailGeneratorProperties.setHalachaTextToSend(fullString.toString());
    }

}
