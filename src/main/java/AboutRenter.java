import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
//страница для кого самокат
public class AboutRenter {
    WebDriver driver;
    //форма заказа
    private final By orderHeader = By.className("Order_Content__bmtHS");
    //поле имя
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    //поле фамилия
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    //поле адрес
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле метро
    private final By stateMetro = By.className("select-search__input");
    //выбор станции метро
    private final String nameStateMetro = ".//button[@value='%s']";
    //поле телефон
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка далее
    private final By buttonNext  = By.xpath(".//button[text()='Далее']");


    public AboutRenter(WebDriver driver) {

        this.driver = driver;
    }
    //метод ожидания загрузки страницы заказа
    public AboutRenter waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }
    //метод ввода имени
    public AboutRenter inputName(String newName) {
        driver.findElement(name).sendKeys(newName);
        return this;
    }
    //метод ввода фамилии
    public AboutRenter inputSurname(String newSurname) {
        driver.findElement(surname).sendKeys(newSurname);
        return this;
    }
    // метод ввода фамилии
    public AboutRenter inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }
    //метод выбора станции метро
    public AboutRenter changeStateMetro(int stateNumber) {
        driver.findElement(stateMetro).click();
        By newStateMetro = By.xpath(String.format(nameStateMetro, stateNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newStateMetro));
        driver.findElement(newStateMetro).click();
        return this;
    }
    //метод ввода телефона
    public AboutRenter inputTelephone(String newTelephone) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(telephone));
        driver.findElement(telephone).sendKeys(newTelephone);
        return this;
    }
    //метод нажатия на кнопку далее
    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }
}