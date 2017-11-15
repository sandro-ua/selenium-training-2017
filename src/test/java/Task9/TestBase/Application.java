package Task9.TestBase;

import Task9.Data.Constants;
import Task9.Pages.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application extends TestBase{

    private WebDriver drv;

    private CartPage cartPage;
    private MainPage mainPage;
    private ProductPage productPage;
    private TopPageBlock topPageBlock;

    WebDriverWait wait = new WebDriverWait(drv, 10);

    public Application() {
        ChromeDriverManager.getInstance().setup();
        drv = new ChromeDriver();

        mainPage = new MainPage(drv);
        cartPage = new CartPage(drv);
        productPage = new ProductPage(drv);
        topPageBlock = new TopPageBlock(drv);
    }

    public void openApp() {
        drv.get(Constants.MAIN_PAGE);
    }

   /* public  void closeApp() {
        drv.quit();
    }*/

    public ProductPage openRandomProduct () {
        return mainPage.openRandomProduct();
    }

    public void addToCart (String size) {
        productPage.selectSize(size);
        Integer currentQuantity = topPageBlock.getCurrentQuantity();
        productPage.clickAddToCart();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(currentQuantity + 1)));
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
}
