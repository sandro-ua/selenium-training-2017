package Task9;

import Task9.TestBase.TestBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class Task9 extends TestBase {

    @Test
    public void Task9() {
        for (int i = 0; i < 3; i++) {
            app.addToCartRandromProduct();
        }
        app.openCart();
        app.removeAllProductsFromCart();

        Assert.assertTrue("Cart is not empty", app.isCartIsEmptyMessageDisplayed());
    }

    @After
    public void finish() {
        app.closeApp();
    }
}
