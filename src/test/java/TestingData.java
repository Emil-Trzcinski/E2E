import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

@Slf4j
public class TestingData {
    public static final String SENDER = "Emil Trzciński";
    public static final String SENDER_EMAIL = "trzcina1986@gmail.com";
    public static final String EMAIL_TITLE = "Selenium test";
    public static final String EMAIL_CONTENT = "Hello,\n\n" +
            "This e-mail is for selenium tests :)\n\n" +
            "Best Regards,\nEmil";

    public static void loadingData(Properties properties) {
        try {
            String path = Paths.get("src", "main", "resources", "config.properties").toAbsolutePath().toString();
            FileInputStream data = new FileInputStream(path);
            properties.load(data);
            log.info("Login properties loaded");
        } catch (IOException e) {
            log.error("Error while loading login properties: " + e.getMessage());
        }
    }

    public static String password() {
        String password = null;

        try {
            password = System.getenv("PASSWORD");
            log.info("Password properties loaded");
        } catch (Exception e) {
            log.error("Error while loading config properties: " + e.getMessage());
        }
        return password;
    }
}
