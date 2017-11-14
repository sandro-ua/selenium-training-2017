package Task9;


import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class Task9 {

    WebDriver drv;
    TestBase testBase = new TestBase(drv);

    @Before
    public void Initialization() {
        ChromeDriverManager.getInstance().setup();
    }

    @Test
    public void Task9() {

        boolean cartIsNotEmpty;

        for (int i = 0; i < 3; i++) {
            testBase.openApp();
            testBase.mainPage.openRandomProduct();
            testBase.productPage.addToCart("Medium");
        }
        testBase.topPageBlock.openCart();

        do {
            cartIsNotEmpty = testBase.cartPage.removeProductFromCart();
        } while (cartIsNotEmpty == true);

        Assert.assertTrue("Cart is not empty", testBase.cartPage.cartIsEmptyMessageDisplayed());
    }

    @After
    public void finish() {
        testBase.closeApp();
    }
}
