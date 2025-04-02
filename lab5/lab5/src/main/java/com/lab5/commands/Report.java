package com.lab5.commands;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.lab5.repository.Repository;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class Report implements Command {
    @Override
    @SuppressWarnings("UseSpecificCatch")
    public void execute(Repository repository, String... args) {
        try {
            Configuration config = new Configuration(new Version(2, 3, 32));
            config.setClassLoaderForTemplateLoading(
                    getClass().getClassLoader(), "templates");
            config.setDefaultEncoding("UTF-8");
            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            Map<String, Object> model = new HashMap<>();
            model.put("images", repository.getAll());
            model.put("currentDate",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            Template template = config.getTemplate("report.ftl");

            File outputFile = new File("repository.html");
            try (Writer writer = new FileWriter(outputFile)) {
                template.process(model, writer);
            } catch (Exception e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(outputFile);
            }

        } catch (Exception e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    @Override
    public String info() {
        return "report - create a html report of the repository";
    }
}