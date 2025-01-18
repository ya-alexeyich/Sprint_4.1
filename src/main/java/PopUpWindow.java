import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//после оформления заказа появляется окно Заказ оформлен
public class PopUpWindow {
    WebDriver driver;
    //окно Хотите оформить заказ
    private final By buttonYes = By.xpath(".//button[text()='Да']");
    //окно Заказ оформлен
    private final By popUpHeaderAfterCreateOrder = By.xpath(".//div[text()='Заказ оформлен']");

    public PopUpWindow(WebDriver driver) {

        this.driver = driver;
    }
    //метод нажатия кнопки да в окне Хотите оформить заказ
    public void clickButtonYes() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }
    //метод ожидания окна Заказ оформлен
    public String getHeaderAfterCreateOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(popUpHeaderAfterCreateOrder).getText() != null
                && !driver.findElement(popUpHeaderAfterCreateOrder).getText().isEmpty()
        ));
        return driver.findElement(popUpHeaderAfterCreateOrder).getText();
    }
}