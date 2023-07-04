package pl.trzcinski.emil.engine;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import pl.trzcinski.emil.base.Property;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
public class WebDriverFactoryInfrastructure {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver preparedDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());

        }
        return driver.get();
    }

    public static WebDriver createDriver() {
        WebDriver webDriver = null;

        Properties properties = Property.loadProperties();

        if (properties.getProperty("browser").equals("chrome")) {
            try {
                webDriver = WebDriverManager.chromedriver().create();
                log.info("Chrome driver initialized");
            } catch (Exception e) {
                log.error("Error while initialized properties: " + e.getMessage());
            }
        } else {
            log.error("Browser not supported");
        }

        try {
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().wait(10);
        } catch (Exception e) {
            log.error("Error while manage : " + e.getMessage());
        }

        return webDriver;
    }

    public static void cleanupDriver() {
        driver.get().close();
        driver.get().quit();
        driver.remove();
        log.info("Driver closed");
    }
}
