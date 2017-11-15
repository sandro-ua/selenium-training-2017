package Task9;

import Task9.TestBase.TestBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class Task9 extends TestBase {

    @Test
    public void Task9() {

        boolean cartIsNotEmpty;

        for (int i = 0; i < 3; i++) {
            app.openApp();

            app.openRandomProduct();
            app.addToCart("Medium");
        }
        app.openCart();

        do {
            app.removeProductFromCart();
            cartIsNotEmpty = app.isCartIsEmptyMessageDisplayed();
        } while (cartIsNotEmpty == true);

        Assert.assertTrue("Cart is not empty", app.isCartIsEmptyMessageDisplayed());
    }

    @After
    public void finish() {
        //app.closeApp();
    }
}
