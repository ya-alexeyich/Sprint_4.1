import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static constants.CreateOrderButton.*;
import static constants.RentDurationConstants.*;
import static constants.ScooterColours.*;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String surname;
    private final String address;
    private final int stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final Enum colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final Enum button;

    public OrderCreateTest(Enum button, String name, String surname, String address, int stateMetroNumber, String telephoneNumber,
                           String date, String duration, Enum colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_BUTTON, "Ал", "Ал", "Фрунз", 11, "89277447744", "21.11.2024", ONE_DAY, GREY, ""},
                {UP_BUTTON, "Але", "Але", "Фрунзе", 12, "89277447744", "21.11.2024", TWO_DAYS, BLACK, "с"},
                {UP_BUTTON, "Алексей", "Алексеев", "Фрунзенская набережная, 46", 13, "89277447744", "21.11.2024", THREE_DAYS, GREY, "св"},
                {DOWN_BUTTON, "Алексейалексей", "Алексеевалексе", "Фрунзенская набережнаяяяяяяяяяяяяяяяяяяяяяяяя, 46", 14, "89277447744", "21.11.2024", FOUR_DAYS, GREY, "свяжитесьсомной"},
                {DOWN_BUTTON, "Алексейалексейа", "Алексеевалексее", " Комсомольский проспект, 18", 15, "+79277447744", "21.11.2024", FIVE_DAYS, BLACK, "свяжитесьсомнойсвяжитес"},
                {DOWN_BUTTON, "алексей", "алексеев", "3-я Фрунзенская улица, 12", 16, "+79277447744", "21.11.2024", SIX_DAYS, GREY, "свяжитесьсомнойсвяжитесь"},
                {DOWN_BUTTON, "Але сей", "Але сеев", "М. Пироговская, 25", 17, "+79277447744", "21.11.2024", SEVEN_DAYS, BLACK, "Позвоните после 8"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        driver.get(site);
    }

    @Test
    public void testCreateOrderWithUpButton() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCookie()
                .clickCreateOrderButton(button);


        new AboutRenter(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        new AboutScooter(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}