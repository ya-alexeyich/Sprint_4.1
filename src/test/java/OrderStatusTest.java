import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrderStatusTest extends BaseTest {


    private final String numberOrder = "666";


    @Test
    public void orderStatusNonexistentOrderNumberTest() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCookie()
                .clickOrderState()
                .inputOrderNumber(numberOrder)
                .clickGo();
        new OrderStatus(driver)
                .waitLoadOrderStatusPage();

        assertTrue(new OrderStatus(driver).waitLoadOrderStatusPage());

    }

}
