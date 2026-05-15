package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationPanel {
    private final By terminalBtn = By.xpath("(//li/button)[1]");
    private final By terminalDiaryBtn = By.xpath("//div[text()='Торговый дневник']");

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public NavigationPanel(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    @Step("Навожу курсор на кнопку Терминалы")
    public NavigationPanel moveToTerminalBtn() {
        actions.moveToElement(driver.findElement(terminalBtn)).perform();

        return this;
    }

    @Step("Нажимаю на кнопку Торговый Дневник")
    public void clickTerminalDiaryBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(terminalDiaryBtn)).click();
    }
}
