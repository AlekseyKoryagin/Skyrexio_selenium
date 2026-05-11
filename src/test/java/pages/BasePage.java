package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.io.File;
import java.time.Duration;

public class BasePage {
    public static final String BASE_URL = PropertyReader.getProperty("Skyrexio_selenium.baseUrl");
    public static final String DOWNLOAD_DIR_PATH = System.getProperty("user.dir") + File.separator + "target" + File.separator + "download";

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    public NavigationPanel navigationPanel;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        navigationPanel = new NavigationPanel(driver);
        actions = new Actions(driver);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}
