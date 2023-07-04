import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.trzcinski.emil.page.GmailLoginPage;
import pl.trzcinski.emil.page.MailPage;

import java.util.Properties;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pl.trzcinski.emil.base.BasePage.getUrl;

@Slf4j
@Listeners(pl.trzcinski.emil.base.Listeners.class)
public class SelenideGmailTest {

    private Properties properties;
    private final String PASSWORD = TestingData.password();
    private GmailLoginPage gmailPage;
    private MailPage mailPage;

    public SelenideGmailTest() {
        properties = new Properties();
        TestingData.loadingData(properties);
    }

    @BeforeClass
    public void setUp() {
        Configuration.timeout = 10000;
        Configuration.browser = CustomWebDriverProvider.class.getName();
        gmailPage = new GmailLoginPage();
        mailPage = new MailPage();
    }

    @Test
    public void selenideGmailTest() {
        open(getUrl());

        // Arrange
        $(gmailPage.getEmailField()).shouldBe(visible);
        // Act
        $(gmailPage.getEmailField()).sendKeys(properties.getProperty("email"));

        // Assert
        String test = $(gmailPage.getEmailField()).getAttribute("value");
        Assert.assertEquals(test, properties.getProperty("email"));


        // Arrange
        $(gmailPage.getNextButton()).shouldBe(visible);
        // Act
        $(gmailPage.getNextButton()).click();
        // Assert
        $(gmailPage.getPasswordButton()).shouldBe(visible);

        // Arrange
        // Act
        $(gmailPage.getPasswordField()).sendKeys(PASSWORD);
        $(gmailPage.getPasswordButton()).click();
        // Assert
        $(mailPage.getSearchField()).shouldBe(visible);


        // Arrange
        // Act
        $(mailPage.getSearchField()).setValue(TestingData.EMAIL_TITLE).pressEnter();
        // Assert
        $(mailPage.getMailField()).shouldBe(visible);


        // Arrange
        // Act
        $(mailPage.getMailField()).click();
        // Assert
        $(mailPage.getSenderField()).shouldBe(visible);
        $(mailPage.getSenderField()).shouldBe(Condition.text(TestingData.SENDER));
        $(mailPage.getSenderEmailField()).shouldBe(Condition.text(TestingData.SENDER_EMAIL));
        $(mailPage.getSubjectField()).shouldBe(Condition.text(TestingData.EMAIL_TITLE));
        $(mailPage.getContentField()).shouldBe(Condition.text(TestingData.EMAIL_CONTENT));


        // Arrange
        $(mailPage.getUserButtonXPath()).shouldBe(visible);
        // Act
        $(mailPage.getUserButtonXPath()).click();
        // Assert
        $(mailPage.getFrame()).should(Condition.exist);


        // Arrange
        // Act
        switchTo().frame($(mailPage.getFrame()));
        // Assert
        $(mailPage.getLogOutButton()).shouldBe(visible);


        // Arrange
        // Act
        $(mailPage.getLogOutButton()).click();

    }
}
