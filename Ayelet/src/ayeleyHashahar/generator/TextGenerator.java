package ayeleyHashahar.generator;

import ayeleyHashahar.parameters.GeneratorParameters;
import ayeleyHashahar.parameters.MailGeneratorProperties;

import java.io.IOException;

/**
 * @author ahiel
 * @date 06/11/12 23:33
 */
public interface TextGenerator {
    boolean isToCheck(MailGeneratorProperties mailGeneratorProperties);

    void generateText(GeneratorParameters params, MailGeneratorProperties mailGeneratorProperties) throws IOException;
}
