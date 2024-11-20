import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//страница Статус заказа
public class OrderStatus {

    WebDriver driver;
    private final By notFound = By.xpath(".//*[@alt='Not found']");

    public OrderStatus(WebDriver driver) {

        this.driver = driver;
    }
    //метод ожидания страницы Такого заказа нет
    public boolean waitLoadOrderStatusPage() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(notFound)).isDisplayed();
    }

}