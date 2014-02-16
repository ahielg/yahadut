package ayeleyHashahar.generator;

import ayeleyHashahar.parameters.GeneratorParameters;
import ayeleyHashahar.parameters.MailGeneratorProperties;

import java.io.IOException;

/**
 * User: ahiel
 * Date: 06/11/12
 * Time: 23:33
 */
public interface TextGenerator {
    public boolean isToCheck(MailGeneratorProperties mailGeneratorProperties);

    public void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException;
}
