package Task9;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    WebDriver drv;
    public MainPage mainPage;
    public CartPage cartPage;
    public ProductPage productPage;
    public TopPageBlock topPageBlock;



    public TestBase (WebDriver driver) {
        this.drv = driver;

        mainPage = new MainPage(drv);
        cartPage = new CartPage(drv);
        productPage = new ProductPage(drv);
        topPageBlock = new TopPageBlock(drv);


    }

    WebDriverWait wait = new WebDriverWait(drv, 10);

    public void openApp() {
        drv.get(Constants.MAIN_PAGE);
    }

    public void closeApp() {
        drv.quit();
    }





}
