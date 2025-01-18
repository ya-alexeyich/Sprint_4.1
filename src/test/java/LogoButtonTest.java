import org.junit.Test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LogoButtonTest extends BaseTest {

    @Test
    public void clickScooterFromHomePageTest(){
        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadHomePage()
                .clickScooterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    @Test
    public void clickYandexFromHomePageTest(){
        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadHomePage()
                .clickYandexButton();

        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains("Дзен"));

        assertEquals("https://dzen.ru/?yredirect=true",driver.getCurrentUrl());
    }


}