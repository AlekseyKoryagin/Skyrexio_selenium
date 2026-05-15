package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.DownloadFolderManager;
import utils.PropertyReader;

import static enums.PagesUrl.HOME_PAGE;
import static enums.PagesUrl.TERMINAL_DIARY_PAGE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.BasePage.BASE_URL;
import static pages.BasePage.DOWNLOAD_DIR_PATH;
import static users.UserFactory.withStandardPermission;

@Owner("Aleksey Ivanov test@test.ru")
@Epic("Проверка страницы Торговый дневник терминала")
public class TerminalsTradingDiaryTest extends BaseTest {
    @Story("Выгрузка файла Список сделок по Терминалу")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Skyrexio_selenium")
    @Test(description = "Проверка загрузки файла ManualTrades.xlsx")
    public void checkExportingTransactionHistoryToAnXlsxFile() {
        DownloadFolderManager downloadFolderManager = new DownloadFolderManager(DOWNLOAD_DIR_PATH);
        downloadFolderManager.createFolderForDownload();
        downloadFolderManager.cleanDownloadDir();

        loginPage
                .open()
                .login(withStandardPermission());
        assertTrue(homePage.isStatisticsBlockDisplayed(), "The statistics block did not appear");
        assertEquals(homePage.getPageUrl(), BASE_URL + HOME_PAGE.getLoginPage(), "The Home page is not open") ;
        terminalDiaryPage.navigationPanel
                .moveToTerminalBtn()
                .clickTerminalDiaryBtn();
        assertTrue(terminalDiaryPage.isTradingDiaryBlockDisplayed(), "The Trading dairy block did not appear");
        assertEquals(terminalDiaryPage.getPageUrl(), BASE_URL + TERMINAL_DIARY_PAGE.getLoginPage(), "The Terminal diary page is not open");
        terminalDiaryPage.clickDownloadListDealsBtn();

        assertTrue(downloadFolderManager.ListDealsDownloaded(PropertyReader.getProperty("Skyrexio_selenium.listDealsName")), "The file is not uploaded or the name is incorrect");
        downloadFolderManager.cleanDownloadDir();
    }
}
