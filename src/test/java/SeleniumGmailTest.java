import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.trzcinski.emil.base.BasePage;
import pl.trzcinski.emil.base.Hooks;
import pl.trzcinski.emil.page.GmailLoginPage;
import pl.trzcinski.emil.page.MailPage;

import java.time.Duration;
import java.util.Properties;

@Slf4j
@Listeners(pl.trzcinski.emil.base.Listeners.class)
public class SeleniumGmailTest extends Hooks {

    private Duration timeout;
    private Properties properties;
    private GmailLoginPage gmailPage;
    private MailPage mailPage;
    private WebDriverWait gmailPageWebDriverWait;
    private WebDriverWait mailPageWebDriverWait;

    private final String PASSWORD = TestingData.password();

    public SeleniumGmailTest() {
        super();
        properties = new Properties();
        TestingData.loadingData(properties);
    }

    @BeforeClass
    public void setUp() {
        timeout = Duration.ofSeconds(10);
        gmailPage = new GmailLoginPage();
        mailPage = new MailPage();
        gmailPageWebDriverWait = new WebDriverWait(gmailPage.driver, timeout);
        mailPageWebDriverWait = new WebDriverWait(mailPage.driver, timeout);
    }

    @Test
    public void seleniumGmailTest() {

        // Arrange
        gmailPageWebDriverWait.until(webDriver -> gmailPage.getSeleniumEmailField().isDisplayed());
        // Act
        gmailPage.getSeleniumEmailField().sendKeys(properties.getProperty("email"));
        // Assert
        gmailPageWebDriverWait.until(webDriver -> gmailPage.getSeleniumEmailField().getAttribute("value").equals(properties.getProperty("email")));


        // Arrange
        gmailPageWebDriverWait.until(webDriver -> gmailPage.getSeleniumNextButton().isDisplayed());
        // Act
        gmailPage.getSeleniumNextButton().click();
        // Assert
        gmailPageWebDriverWait.until(webDriver -> gmailPage.getSeleniumPasswordField().isDisplayed());


        // Arrange
        // Act
        gmailPage.getSeleniumPasswordField().sendKeys(PASSWORD);
        gmailPage.getSeleniumPasswordButtom().click();
        // Assert
        mailPageWebDriverWait.until(webDriver -> mailPage.getSeleniumSearchField().isDisplayed());


        // Arrange
        // Act
        mailPage.getSeleniumSearchField().sendKeys(TestingData.EMAIL_TITLE, Keys.ENTER);
        // Assert
        mailPageWebDriverWait.until(webDriver -> mailPage.getSeleniumMailField().isDisplayed());


        // Arrange
        // Act
        mailPage.getSeleniumMailField().click();
        // Assert
        mailPageWebDriverWait.until(webDriver -> mailPage.getSeleniumSenderEmailField().isDisplayed());
        mailPageWebDriverWait.until(webDriver -> mailPage.getSeleniumSenderField().getText().equals(TestingData.SENDER));
        String email = mailPage.getSeleniumSenderEmailField().getText();
        int length = email.length();
        String preparedEmail = email.substring(1, length - 1);
        Assert.assertEquals(preparedEmail, TestingData.SENDER_EMAIL);
        Assert.assertEquals(mailPage.getSeleniumSubjectField().getText(), TestingData.EMAIL_TITLE);
        Assert.assertEquals(mailPage.getSeleniumContentField().getText(), TestingData.EMAIL_CONTENT);


        // Arrange
        mailPageWebDriverWait.until(webDriver -> mailPage.getSeleniumUserButton().isDisplayed());
        // Act
        mailPage.getSeleniumUserButton().click();
        // Assert
        mailPageWebDriverWait.until(webDriver -> mailPage.getSeleniumFrame().isDisplayed());

        // Arrange
        // Act
        BasePage.getDriver().switchTo().frame(mailPage.getSeleniumFrame());
        // Assert
        mailPageWebDriverWait.until(webDriver -> mailPage.getSeleniumLogoutButton().isDisplayed());


        // Act
        mailPage.getSeleniumLogoutButton().click();
        gmailPage.popUpAlert();
    }
}
