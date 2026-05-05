package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.TestListener;

import java.time.Duration;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    WebDriver driver;

    @Step("Запуск браузера {browser}")
    @BeforeMethod
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            startChrome();
        } else if (browser.equalsIgnoreCase("firefox")) {
            startFirefox();
        } else if (browser.equalsIgnoreCase("edge")) {
            startEdge();
        }

        context.setAttribute("driver", driver);
    }

    @Step("Закрытие браузера")
    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    private void startChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    private void startFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--guest");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    private void startEdge() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--guest");
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
}
