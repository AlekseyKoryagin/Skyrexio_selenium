package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import users.User;

import static enums.PagesUrl.LOGIN_PAGE;

public class LoginPage extends BasePage {
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By signInBtn = By.xpath("//*[@id='email']/parent::div/parent::div/parent::div/parent::div/button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываю страницу Login")
    public LoginPage open() {
        driver.get(BASE_URL + LOGIN_PAGE.getLoginPage());

        return this;
    }

    @Step("Заполняю поле Email")
    public LoginPage fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);

        return this;
    }

    @Step("Заполняю поле Password")
    public LoginPage fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);

        return this;
    }

    @Step("Нажимаю кнопку Sign In")
    public void clickSignInBtn() {
        driver.findElement(signInBtn).click();
    }

    @Step("Логинимся")
    public void login(User user) {
        fillEmailField(user.getEmail()).fillPasswordField(user.getPassword()).clickSignInBtn();
    }
}
