package lesson14.pages;

import lesson12.pages.ClientPage;
import lesson14.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class AddClientPage extends BasePage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;

    protected AddClientPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
    }
}
