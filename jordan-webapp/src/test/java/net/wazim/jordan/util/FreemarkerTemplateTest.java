package net.wazim.jordan.util;

import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class FreemarkerTemplateTest {

    @Test
    public void correctlyLoadsTheTemplate() throws IOException, TemplateException {
        FreemarkerTemplate template = new FreemarkerTemplate("index.ftl");
        String processedTemplate = template.with("numOfBluRays", "10").processTemplate();

        System.out.println("processedTemplate = " + processedTemplate);
        assertThat(processedTemplate, containsString("We currently have <span class=\"librarySize\">10</span> Blu Rays in our library."));
    }



}