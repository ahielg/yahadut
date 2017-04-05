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
 * @author ahiel
 * @date 7/11/12 23:32
 */
public class MusarMitzvotGenerator implements TextGenerator {
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
        StringBuilder fullString = new StringBuilder();

        FileUtils.gotoLineInFile(in, lineNum);
        //for (int i = 0; i < lineNum; i++, in.readLine()) ;

        while ((line = in.readLine()) != null) {
            lineNum++;
            if (line.trim().equals("<!--$~-->")) {
                break;
            } else {
                fullString.append(line);
            }


        }
        System.out.println("line: " + lineNum);
        MailSenderCons.dynamic_properties.setProperty(MailSenderCons.MUSAR_LINE, String.valueOf(lineNum));

        mailGeneratorProperties.setMusarTextToSend(fullString.toString());
    }

}
