package pl.trzcinski.emil.page;

import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.trzcinski.emil.base.BasePage;

@Getter
public class MailPage extends BasePage {

    @Getter(AccessLevel.NONE)
    public WebDriver driver;

    By searchField = By.cssSelector(".gsib_a [aria-label]");
    By searchFieldxPath = By.xpath("input[aria-label='Search in the mail']");

    By userButton = By.cssSelector("a[role='button'] > img");
    By userButtonXPath = By.xpath("/html//header[@id='gb']/div[2]/div[3]/div[@class='gb_Ie']/div[2]/div/a[@role='button']/img");

    By logOutButton = By.cssSelector(".vZvJBb > .V5tzAf.h2rKjc.jFfZdd");

    By frame = By.cssSelector("div:nth-of-type(3) > iframe[role='presentation']");
    By mailField = By.cssSelector(".UI.ae4.id.nH.oy8Mbf table[role='grid']  tr[role='row']");

    By subjectField = By.cssSelector(".ha h2");

    By senderField = By.cssSelector(".gD span");
    By senderEmailField = By.cssSelector(".go");

    By contentField = By.cssSelector("div:nth-of-type(3) > .a3s.aiL");

    public MailPage() {
        super();
        this.driver = getDriver();
    }

    public WebElement getSeleniumSearchField() {
        return driver.findElement(searchField);
    }

    public WebElement getSeleniumUserButton() {
        return driver.findElement(userButton);
    }

    public WebElement getSeleniumLogoutButton() {
        return driver.findElement(logOutButton);
    }

    public WebElement getSeleniumFrame() {
        return driver.findElement(frame);
    }

    public WebElement getSeleniumMailField() {
        return driver.findElement(mailField);
    }

    public WebElement getSeleniumSubjectField() {
        return driver.findElement(subjectField);
    }

    public WebElement getSeleniumSenderField() {
        return driver.findElement(senderField);
    }

    public WebElement getSeleniumSenderEmailField() {
        return driver.findElement(senderEmailField);
    }

    public WebElement getSeleniumContentField() {
        return driver.findElement(contentField);
    }
}
