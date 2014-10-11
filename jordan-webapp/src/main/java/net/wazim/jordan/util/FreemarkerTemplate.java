package net.wazim.jordan.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.wazim.jordan.JordanServer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class FreemarkerTemplate {

    private Template template;
    private HashMap<String, String> root = new HashMap<String, String>();

    public FreemarkerTemplate(String templateName) {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");

        cfg.setClassForTemplateLoading(JordanServer.class, "/templates");


        try {
            template = cfg.getTemplate(templateName);
        } catch (IOException e) {
            System.out.println("Can't find the specified template");
            e.printStackTrace();
        }
    }

    public FreemarkerTemplate with(String key, String value) {
        root.put(key, value);
        return this;
    }

    public String processTemplate() {
        StringWriter writer = new StringWriter();

        try {
            template.process(root, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer.toString();
    }

}
