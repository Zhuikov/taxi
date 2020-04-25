package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class OrderTest {

    private Driver driver;
    private Order order;
    private CarsOwner owner;

    @BeforeEach
    private void initFields() {
        driver = new Driver("driver1", new PersonInfo("FirstName",
                "SecondName", "8-888-888-88-88"), new Car("P233AB", owner));
        order = new Order("Address1",
                new TaxiClient("client1",
                        new PersonInfo("ClientName", "ClientSecondName", "9-999-999-99-99")));
    }

    @Test
    public void commonOrderProcessTest() {
        Assertions.assertTrue(driver.setNewOrder(order));
        Assertions.assertNull(driver.getOrder());
        Assertions.assertEquals(driver.getStatus(), DriverStatus.FREE);
        Assertions.assertEquals(order.getStatus(), OrderStatus.WAIT_FOR_DRIVER);

        Assertions.assertTrue(driver.acceptOrder());
        Assertions.assertEquals(driver.getOrder(), order);
        Assertions.assertEquals(driver.getStatus(), DriverStatus.BUSY);
        Assertions.assertEquals(order.getStatus(), OrderStatus.ACCEPTED);

        driver.finishOrder();
        Assertions.assertNull(driver.getOrder());
        Assertions.assertEquals(driver.getStatus(), DriverStatus.FREE);
        Assertions.assertEquals(order.getStatus(), OrderStatus.FINISHED);
    }

    @Test
    public void twoOrdersOneDriverTest() {
        Order order2 = new Order("Address2", new TaxiClient("client2",
                new PersonInfo("Name", "sName", "22222222")));
        Assertions.assertTrue(driver.setNewOrder(order));
        Assertions.assertEquals(driver.getStatus(), DriverStatus.FREE);
        Assertions.assertFalse(driver.setNewOrder(order));
        Assertions.assertFalse(driver.setNewOrder(order2));

        Assertions.assertTrue(driver.acceptOrder());
        Assertions.assertFalse(driver.acceptOrder());
        Assertions.assertEquals(driver.getStatus(), DriverStatus.BUSY);
        Assertions.assertEquals(order.getStatus(), OrderStatus.ACCEPTED);

        Assertions.assertTrue(driver.setNewOrder(order2));
        Assertions.assertEquals(order2.getStatus(), OrderStatus.WAIT_FOR_DRIVER);
        Assertions.assertEquals(driver.getStatus(), DriverStatus.BUSY);
        Assertions.assertFalse(driver.acceptOrder());

        driver.finishOrder();
        Assertions.assertEquals(order.getStatus(), OrderStatus.FINISHED);
        Assertions.assertEquals(driver.getStatus(), DriverStatus.FREE);

        Assertions.assertTrue(driver.acceptOrder());
        Assertions.assertEquals(driver.getStatus(), DriverStatus.BUSY);
        Assertions.assertEquals(order2.getStatus(), OrderStatus.ACCEPTED);

        driver.finishOrder();
        Assertions.assertEquals(order2.getStatus(), OrderStatus.FINISHED);
        Assertions.assertEquals(driver.getStatus(), DriverStatus.FREE);
    }

    @Test
    public void oneOrderTwoDriversTest() {
        Driver driver2 = new Driver("driver2", new PersonInfo("Test", "Test", "TestPhone"),
                new Car("B122OX", owner));
        Assertions.assertTrue(driver.setNewOrder(order));
        Assertions.assertEquals(driver.getStatus(), DriverStatus.FREE);
        Assertions.assertTrue(driver2.setNewOrder(order));
        Assertions.assertEquals(driver2.getStatus(), DriverStatus.FREE);

        Assertions.assertTrue(driver.acceptOrder());
        Assertions.assertEquals(driver.getStatus(), DriverStatus.BUSY);
        Assertions.assertFalse(driver2.acceptOrder());
        Assertions.assertEquals(driver2.getStatus(), DriverStatus.FREE);

        Assertions.assertEquals(order.getStatus(), OrderStatus.ACCEPTED);

        driver.finishOrder();
        Assertions.assertEquals(order.getStatus(), OrderStatus.FINISHED);
        Assertions.assertEquals(driver.getStatus(), DriverStatus.FREE);
        Assertions.assertFalse(driver2.acceptOrder());
        Assertions.assertEquals(driver2.getStatus(), DriverStatus.FREE);
    }

    @Test
    public void rejectOrderTwoDriversTest() {
        Driver driver2 = new Driver("driver2", new PersonInfo("Test", "Test", "TestPhone"),
                new Car("B122OX", owner));
        Assertions.assertTrue(driver.setNewOrder(order));

        driver.rejectOrder();
        Assertions.assertFalse(driver.acceptOrder());
        Assertions.assertEquals(driver.getStatus(), DriverStatus.FREE);

        Assertions.assertTrue(driver.setNewOrder(order));



    }

}