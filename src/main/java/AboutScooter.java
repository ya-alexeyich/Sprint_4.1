import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import constants.ScooterColours;

//страница про аренду
public class AboutScooter {
    WebDriver driver;
    //форма заказа
    private final By rentHeader = By.className("Order_Content__bmtHS");
    //поле на какую дату заказ
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //список срок аренды
    private final By durationRent = By.xpath(".//span[@class='Dropdown-arrow']");
    //цвет самоката
    private final By colourBlack = By.id("black");
    //цвет самоката
    private final By colourGrey = By.id("grey");
    //поле комментарий
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка заказать
    private final By buttonNext  = By.xpath(".//button[text()='Заказать']");
    //
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public AboutScooter(WebDriver driver) {

        this.driver = driver;
    }
    //метод ожидания загрузки страницы
    public AboutScooter waitAboutRentHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(rentHeader).getText() != null
                && !driver.findElement(rentHeader).getText().isEmpty()
        ));
        return this;
    }
    //метод ввода на какую дату заказ
    public AboutScooter inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }
    //метод выбора срока аренды
    public AboutScooter inputDuration(String newDuration) {
        driver.findElement(durationRent).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        return this;
    }
    //метод выбора цвета
    public AboutScooter changeColour(Enum colour) {
        if (colour.equals(ScooterColours.BLACK)) {
            driver.findElement(colourBlack).click();
        } else if (colour.equals(ScooterColours.GREY)) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }
    //метод ввода комментария
    public AboutScooter inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }
    //метод нажатия кнопки создать заказ
    public void clickButtonCreateOrder() {
        driver.findElement(createOrderButton).click();
    }
}