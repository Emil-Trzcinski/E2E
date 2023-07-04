package pl.trzcinski.emil.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
@Getter
public class Property {
    private static Properties properties;

    public Property() {
    }
    public static Properties loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                String path = Paths.get("src", "main", "resources", "config.properties").toAbsolutePath().toString();

                FileInputStream data = new FileInputStream(path);
                properties.load(data);
                log.info("Properties for base page loaded");
                return properties;
            } catch (IOException e) {
                log.error("Error while loading properties: " + e.getMessage());
            }
        }

        log.info("Properties for base page already loaded");
        return properties;
    }
}
