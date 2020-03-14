package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class OrderTest {

    private Driver driver;
    private Order order;

    @BeforeEach
    private void initFields() {
        driver = new Driver("FirstName", "SecondName", "8-888-888-88-88");
        order = new Order("Address1",
                new TaxiClient("ClientName", "ClientSecondName", "9-999-999-99-99"));
    }

    @Test
    public void commonOrderProcessTest() {
        Assertions.assertTrue(driver.sendOrder(order));
        Assertions.assertNull(driver.getOrder());
        Assertions.assertEquals(order.getStatus(), OrderStatus.WAIT_FOR_DRIVER);

        Assertions.assertTrue(driver.acceptOrder());
        Assertions.assertEquals(driver.getOrder(), order);
        Assertions.assertEquals(order.getStatus(), OrderStatus.ACCEPTED);

        driver.finishOrder();
        Assertions.assertNull(driver.getOrder());
        Assertions.assertEquals(order.getStatus(), OrderStatus.FINISHED);
    }

    @Test
    public void twoOrdersOneDriverTest() {
        Order order2 = new Order("Address2", new TaxiClient("Name", "sName", "22222222"));
        Assertions.assertTrue(driver.sendOrder(order));
        Assertions.assertFalse(driver.sendOrder(order));
        Assertions.assertFalse(driver.sendOrder(order2));

        Assertions.assertTrue(driver.acceptOrder());
        Assertions.assertFalse(driver.acceptOrder());
        Assertions.assertEquals(order.getStatus(), OrderStatus.ACCEPTED);

        Assertions.assertTrue(driver.sendOrder(order2));
        Assertions.assertEquals(order2.getStatus(), OrderStatus.WAIT_FOR_DRIVER);
        Assertions.assertFalse(driver.acceptOrder());

        driver.finishOrder();
        Assertions.assertEquals(order.getStatus(), OrderStatus.FINISHED);

        Assertions.assertTrue(driver.acceptOrder());
        Assertions.assertEquals(order2.getStatus(), OrderStatus.ACCEPTED);

        driver.finishOrder();
        Assertions.assertEquals(order2.getStatus(), OrderStatus.FINISHED);
    }

    @Test
    public void oneOrderTwoDriversTest() {
        Driver driver2 = new Driver("Test", "Test", "TestPhone");
        Assertions.assertTrue(driver.sendOrder(order));
        Assertions.assertTrue(driver2.sendOrder(order));

        Assertions.assertTrue(driver.acceptOrder());
        Assertions.assertFalse(driver2.acceptOrder());

        Assertions.assertEquals(order.getStatus(), OrderStatus.ACCEPTED);

        driver.finishOrder();
        Assertions.assertEquals(order.getStatus(), OrderStatus.FINISHED);
        Assertions.assertFalse(driver2.acceptOrder());
    }

}