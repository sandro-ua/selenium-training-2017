package Task9.TestBase;

import Task9.Data.Constants;
import Task9.Pages.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application extends TestBase{

    private WebDriver drv;

    private CartPage cartPage;
    private MainPage mainPage;
    private ProductPage productPage;
    private TopPageBlock topPageBlock;


    public Application() {
        ChromeDriverManager.getInstance().setup();
        drv = new ChromeDriver();

        mainPage = new MainPage(drv);
        cartPage = new CartPage(drv);
        productPage = new ProductPage(drv);
        topPageBlock = new TopPageBlock(drv);
    }

    public void openMainPage() {
        drv.get(Constants.MAIN_PAGE);
    }

    public void closeApp() {
        drv.quit();
    }

    public ProductPage openRandomProduct () {
        return mainPage.openRandomProduct();
    }

    public void addToCart (String size) {
        productPage.selectSize(size);
        Integer currentQuantity = topPageBlock.getCurrentQuantity();
        productPage.clickAddToCart();
        topPageBlock.waitTillQuantityChanges(currentQuantity);

        //Assert.assertEquals("Product not added to the cart", currentQuantity+1, topPageBlock.getCurrentQuantity().intValue());
    }

    public boolean isProductAddedToCart (int currentQuantity) {
        return (currentQuantity+1 == topPageBlock.getCurrentQuantity().intValue());
    }

    public CartPage openCart () {
        return topPageBlock.openCart();
    }

    public CartPage removeProductFromCart () {
        return cartPage.removeProductFromCart();
    }

    public boolean isCartIsEmptyMessageDisplayed() {
        return cartPage.isCartIsEmptyMessageDisplayed();
    }

    public boolean isProductShortCutAvailable() {
        return cartPage.isProductShortcutAvailable();
    }

    public boolean isCartTableDisplayed() {
        return cartPage.isCartTableDisplayed();
    }

    public void addToCartRandomProduct() {
        openMainPage();
        openRandomProduct();
        addToCart("Medium");
    }

    public void removeAllProductsFromCart() {
        topPageBlock.openCart();
        boolean cartIsNotEmpty;
        do {
            removeProductFromCart();
            cartIsNotEmpty = isCartTableDisplayed();
        } while (cartIsNotEmpty == true);
        //Assert.assertTrue("Not all products were removed from cart", cartPage.isCartIsEmptyMessageDisplayed());
    }
}