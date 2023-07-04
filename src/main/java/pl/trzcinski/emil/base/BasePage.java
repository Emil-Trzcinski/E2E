package pl.trzcinski.emil.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import pl.trzcinski.emil.engine.WebDriverFactoryInfrastructure;

@Slf4j
public class BasePage {

    private static String url = "https://www.gmail.com/";

    public BasePage() {
        Property.loadProperties();
    }

    public static WebDriver getDriver() {
        return WebDriverFactoryInfrastructure.preparedDriver();
    }

    public static String getUrl() {
        if (url.isEmpty()) {
            log.error("Url is null or empty");
        }

        log.info("Url is: " + url);
        return url;
    }

    public void popUpAlert() {
        if (isPopUpAlertPresent()) {
            closePopUpAlert();
        }
    }

    public boolean isPopUpAlertPresent() {
        try {
            getDriver().switchTo().alert();
            log.info("Pop up alert is present");
            return true;
        } catch (Exception e) {
            log.info("Pop up alert is not present");
            return false;
        }
    }

    public void closePopUpAlert() {
        getDriver().switchTo().alert().accept();
        log.info("Pop up alert closed");
    }
}
