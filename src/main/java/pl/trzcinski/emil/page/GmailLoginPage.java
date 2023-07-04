package pl.trzcinski.emil.page;

import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.trzcinski.emil.base.BasePage;

@Getter
public class GmailLoginPage extends BasePage {

    @Getter(AccessLevel.NONE)
    public WebDriver driver;

    public GmailLoginPage() {
        super();
        this.driver = getDriver();
    }

    By emailField = By.cssSelector("input#identifierId");
    By passwordField = By.cssSelector("input[name='Passwd']");
    By nextButton = By.cssSelector("#identifierNext [jscontroller]");
    By passwordButton = By.cssSelector("#passwordNext [jscontroller]");
    By cookie = By.cssSelector(".close-cookie-warning > span");

    public WebElement getSeleniumEmailField() {
        return driver.findElement(emailField);
    }

    public WebElement getSeleniumPasswordField() {

        return driver.findElement(passwordField);
    }

    public WebElement getSeleniumNextButton() {
        return driver.findElement(nextButton);
    }

    public WebElement getSeleniumPasswordButtom() {
        return driver.findElement(passwordButton);
    }

    public WebElement getSeleniumCookie() {
        return driver.findElement(cookie);
    }

}
