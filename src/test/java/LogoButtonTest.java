import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LogoButtonTest {
    WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        driver.get(site);
    }

    @Test
    public void clickScooterFromHomePage(){
        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadHomePage()
                .clickScooterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    @Test
    public void clickYandexFromHomePage(){
        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadHomePage()
                .clickYandexButton();

        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleContains("Дзен"));

        assertEquals("https://dzen.ru/?yredirect=true",driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}