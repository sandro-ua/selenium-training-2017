package Task9;

import org.openqa.selenium.WebDriver;

public class Application {

    WebDriver drv;
    public MainPage mainPage;
    public CartPage cartPage;
    public ProductPage productPage;


    public Application (WebDriver driver) {
        this.drv = driver;

        mainPage = new MainPage(drv);
        cartPage = new CartPage(drv);
        productPage = new ProductPage(drv);
    }

    public void openApp() {
        drv.get(Constants.MAIN_PAGE);
    }

    public void closeApp() {
        drv.quit();
    }





}
