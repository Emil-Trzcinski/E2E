import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import pl.trzcinski.emil.engine.WebDriverFactoryInfrastructure;

import javax.annotation.Nonnull;

public class CustomWebDriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return WebDriverFactoryInfrastructure.preparedDriver();
    }
}
