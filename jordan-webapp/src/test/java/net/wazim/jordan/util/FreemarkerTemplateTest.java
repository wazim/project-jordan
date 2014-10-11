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
        String processedTemplate = template.processTemplate();

        System.out.println("processedTemplate = " + processedTemplate);
        assertThat(processedTemplate, containsString("We currently have"));
    }

    @Test
    public void correctLoadsTemplateAndAddsElementsToTemplate() {
        FreemarkerTemplate template = new FreemarkerTemplate("test.ftl").with("name", "Karl");
        String processedTemplate = template.processTemplate();

        System.out.println("processedTemplate = " + processedTemplate);
        assertThat(processedTemplate, containsString("Name: Karl"));
    }

}