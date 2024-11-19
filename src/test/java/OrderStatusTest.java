import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

public class OrderStatusTest {
    WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String numberOrder = "666";

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        driver.get(site);
    }

    @Test
    public void orderStatusWithoutNumber() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCookie()
                .clickOrderState()
                .inputOrderNumber(numberOrder)
                .clickGo();
        new OrderStatus(driver)
                .waitLoadOrderStatusPage();

        assertTrue(driver.findElement(By.xpath(".//*[@alt='Not found']")).isDisplayed());

    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
