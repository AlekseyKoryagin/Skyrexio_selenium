package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.TerminalsTradingDiaryPage;
import utils.TestListener;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static pages.BasePage.DOWNLOAD_DIR_PATH;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    TerminalsTradingDiaryPage terminalDiaryPage;

    @Step("Запуск браузера {browser}")
    @BeforeMethod
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            startChrome();
        } else if (browser.equalsIgnoreCase("firefox")) {
            startFirefox();
        }

        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        terminalDiaryPage = new TerminalsTradingDiaryPage(driver);
    }

    @Step("Закрытие браузера")
    @AfterMethod
    public void closeDriver() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("sessionStorage.clear();");
        driver.quit();
    }

    private void startChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--headless");
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", DOWNLOAD_DIR_PATH);
        chromePrefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    private void startFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", DOWNLOAD_DIR_PATH);
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/octet-stream,text/plain");
        options.addPreference("browser.download.panel.shown", false);
        options.addPreference("pdfjs.disabled", true);
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
}
