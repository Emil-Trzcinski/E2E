package pl.trzcinski.emil.base;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pl.trzcinski.emil.engine.WebDriverFactoryInfrastructure;

@Slf4j
public class Hooks {

    public Hooks() {
    }

    @BeforeTest
    public void setup() {
        WebDriverFactoryInfrastructure.preparedDriver().get(BasePage.getUrl());
        log.info("Url loaded " + BasePage.getUrl());
    }

    @AfterTest
    public void tearDown() {
        WebDriverFactoryInfrastructure.cleanupDriver();
        log.info("Driver closed");
    }
}
