package ayeleyHashahar.generator.generators;

import ayeleyHashahar.cons.MailSenderCons;
import ayeleyHashahar.generator.TextGenerator;
import ayeleyHashahar.parameters.GeneratorParameters;
import ayeleyHashahar.parameters.MailGeneratorProperties;
import ayeleyHashahar.util.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * User: ahiel
 * Date: 07/11/12
 * Time: 00:06
 */
public class MusarLashonGenerator implements TextGenerator {
    @Override
    public boolean isToCheck(MailGeneratorProperties mailGeneratorProperties) {
        return mailGeneratorProperties.isMusar();
    }

    @Override
    public void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException {
        File inputFile = new File(params.getMusarFilename());
        long lineNum = params.getMusarLineNum();

        BufferedReader in = new BufferedReader(new FileReader(inputFile));
        String line;
        int counter = 0;
        StringBuilder fullString = new StringBuilder();

        boolean emtzaInaan = true;
        //for (int i = 0; i < lineNum; i++, in.readLine()) ;
        FileUtils.gotoLineInFile(in, lineNum);

        while ((line = in.readLine()) != null) {
            lineNum++;
            if (line.trim().length() == 0) {
                fullString.append("<br/>");
                if (counter > 1 && !emtzaInaan && fullString.length() > 600) {
                    break;
                }
            } else {
                //noinspection DynamicRegexReplaceableByCompiledPattern
                line = line.replace("AA", "<i>").replace("CC", "<i>").replace("BB", "</i>").replace("DD", "</i>").replace("[[", "<b>").replace("]]", "</b>");
                if ((line.charAt(0) == '#') || (line.charAt(0) == '@')) {
                    fullString.append("<b>").append(line.substring(1)).append("</b>");
                } else {
                    counter++;
                    fullString.append(line);
                    emtzaInaan = (line.length() < 100);
                }
                fullString.append("<br/>");
            }

        }
        System.out.println("line: " + lineNum);
        MailSenderCons.dynamic_properties.setProperty(MailSenderCons.MUSAR_LINE, String.valueOf(lineNum));

        mailGeneratorProperties.setMusarTextToSend(fullString.toString());
    }
}
