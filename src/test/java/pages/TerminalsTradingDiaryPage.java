package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.DownloadFolderManager.waitingForFileUpload;

public class TerminalsTradingDiaryPage extends BasePage {
    private final By downloadListDealsBtn = By.xpath("//*[text()='Список сделок']/parent::div/parent::div//button");
    private final By tradingDiaryBlockName = By.xpath("//h2[text()='Торговый дневник']");

    public TerminalsTradingDiaryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаю на кнопку Скачать список сделок")
    public void clickDownloadListDealsBtn() {
        driver.findElement(downloadListDealsBtn).click();
        waitingForFileUpload();
    }

    @Step("Проверяю появление блока Торговый дневник")
    public boolean isTradingDiaryBlockDisplayed() {
        return driver.findElement(tradingDiaryBlockName).isDisplayed();
    }
}
