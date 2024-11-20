import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import constants.CreateOrderButton;

//главная страница
public class HomePage {
    WebDriver driver;
    //Тело сайта
    private final By homeHeader = By.className("Home_HomePage__ZXKIX");
    //Куки
    private final By cookie = By.className("App_CookieButton__3cvqF");
    //Кнопка заказать вверху страницы
    private final By upOrderButton = By.className("Button_Button__ra12g");
    //Кнопка заказать внизу страницы
    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Кнопка статус заказа
    private final By orderState = By.xpath(".//button[text()='Статус заказа']");
    //Поле ввода номера заказа
    private final By numberOrder = By.xpath(".//input[@placeholder='Введите номер заказа']");
    //Кнопка поиска заказа
    private final By buttonGo = By.xpath(".//button[text()='Go!']");
    //Вопросы
    private final By questionsHeader = By.className("Home_FourPart__1uthg");
    // Лого яндекс
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']");
    //Лого самокат
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']");

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }
    //метод ожидания загрузки главной страницы
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }
    //метод принять куки
    public HomePage clickCookie() {
        driver.findElement(cookie).click();
        return this;
    }
    //метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }
    //метод прокрутки к блоку вопросов
    public HomePage scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }
    //метод прокрутки книжней кнопке заказать
    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }
    //метод нажатия на верхней кнопки заказать
    public HomePage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }
    //метод нажатия нижней кнопки заказать
    public HomePage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }
    //выбора кнопки заказа
    public void clickCreateOrderButton(Enum button) {
        if (button.equals(CreateOrderButton.UP_BUTTON)) {
            clickUpOrderButton();
        } else if (button.equals(CreateOrderButton.DOWN_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }
    //метод нажатия на вопрос
    public HomePage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }
    //метод нажатия на статус заказа
    public HomePage clickOrderState() {
        driver.findElement(orderState).click();
        return this;
    }
    //метод ввода номера заказа
    public HomePage inputOrderNumber(String number) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(numberOrder))
                .sendKeys(number);
        return this;
    }
    //метод нажатия на кнопку поиска заказа
    public HomePage clickGo() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonGo))
                .click();
        return this;
    }
    //нажатие на логотип Яндекса
    public void clickYandexButton() {
        driver.findElement(yandexButton).click();
    }
    //нажатие на логотип Самокат
    public void clickScooterButton() {
        driver.findElement(scooterButton).click();
    }
}