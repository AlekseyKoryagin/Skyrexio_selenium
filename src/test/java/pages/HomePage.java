package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By statisticsBlockName = By.xpath("//h2[text()='Статистика']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяю появление блока Статистика")
    public boolean isStatisticsBlockDisplayed() {
        return driver.findElement(statisticsBlockName).isDisplayed();
    }
}
