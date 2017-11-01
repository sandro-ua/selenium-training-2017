import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class Task4 {

    WebDriver drv = TestBase.getDriver();

    @Parameterized.Parameters
    public static Collection data () {
        Object[][] data = {{"Firefox"}, {"Chrome"}, {"InternetExplorer"}};
        return Arrays.asList(data);
    }

    @Parameterized.Parameter
    public String browserName;

    @Before
    public void start() {
        TestBase.Initialize(browserName);
        this.drv = TestBase.getDriver();
        drv.get(Constants.MAIN_PAGE);
    }

    @Test
    public void Task4 () {
        // regular price main page
        WebElement regularPriceMainPageEl = drv.findElement(By.cssSelector("#box-campaigns s.regular-price"));
        String regularPriceValueMainPage = regularPriceMainPageEl.getText();
        String regularPriceColorMainPage = regularPriceMainPageEl.getCssValue("color");
        String regularPriceDecorationMainPage = regularPriceMainPageEl.getCssValue("text-decoration-line");

        // discount price main page
        WebElement discountPriceMainPageEl = drv.findElement(By.cssSelector("#box-campaigns strong.campaign-price"));
        String discountPriceMainPage = discountPriceMainPageEl.getText();
        String discountPriceColorMainPage = discountPriceMainPageEl.getCssValue("color");
        String discountPriceDecorationMainPage = discountPriceMainPageEl.getCssValue("font-weight");


        // product name main page
        String productNameMainPage = drv.findElement(By.cssSelector("#box-campaigns div.name")).getText();

        drv.findElement(By.cssSelector("#box-campaigns a.link")).click();

        // product name item page
        String productNameItemPage = drv.findElement(By.cssSelector("h1.title")).getText();

        // regular price item page
        WebElement regularPriceItemPageEl = drv.findElement(By.cssSelector("#box-product s.regular-price"));
        String regularPriceItemPage = regularPriceItemPageEl.getText();
        String regularPriceColorItemPage = regularPriceItemPageEl.getCssValue("color");
        String regularPriceDecorationItemPage = regularPriceItemPageEl.getCssValue("text-decoration-line");

        // discount price item page
        WebElement discountPriceItemPageEl = drv.findElement(By.cssSelector("#box-product strong.campaign-price"));
        String discountPriceItemPage = discountPriceItemPageEl.getText();
        String discountPriceColorItemPage = discountPriceItemPageEl.getCssValue("color");
        String discountPriceDecorationItemPage = discountPriceItemPageEl.getCssValue("font-weight");

        // Verifications
        //task 4.4a
        Assert.assertEquals("Product Name is not equal on Main page and Item Page", productNameMainPage, productNameItemPage);

        //task 4.4b
        Assert.assertEquals("Regular price is not equal on Main page and Item Page", regularPriceValueMainPage, regularPriceItemPage);
        Assert.assertEquals("Discount price is not equal on Main page and Item Page", discountPriceMainPage, discountPriceItemPage);

        //task 4.4.c
        if (browserName == "Chrome" || browserName == "InternetExplorer" ) {
        Assert.assertEquals("Regular price is not gray on Main page", "rgba(119, 119, 119, 1)", regularPriceColorMainPage);
        Assert.assertEquals("Regular price is not gray on Item page ", "rgba(102, 102, 102, 1)", regularPriceColorItemPage);
        }
        else {
            Assert.assertEquals("Regular price is not gray on Main page", "rgb(119, 119, 119)", regularPriceColorMainPage);
            Assert.assertEquals("Regular price is not gray on Item page ", "rgb(102, 102, 102)", regularPriceColorItemPage);
        }

        if (browserName == "InternetExplorer") {
            Assert.assertEquals("Regular price is not strike on Main page ", "", regularPriceDecorationMainPage);
            Assert.assertEquals("Regular price is not strike on Item page ", "", regularPriceDecorationItemPage);
        }
            else {
            Assert.assertEquals("Regular price is not strike on Main page ", "line-through", regularPriceDecorationMainPage);
            Assert.assertEquals("Regular price is not strike on Item page ", "line-through", regularPriceDecorationItemPage);
        }

        // task 4.4.d
        if (browserName == "Chrome" || browserName == "InternetExplorer" ) {
            Assert.assertEquals("Discount price is not red on Main page", "rgba(204, 0, 0, 1)", discountPriceColorMainPage);
            Assert.assertEquals("Discount price is not red on Item page ", "rgba(204, 0, 0, 1)", discountPriceColorItemPage);
        }
        else {
            Assert.assertEquals("Discount price is not red on Main page", "rgb(119, 119, 119)", discountPriceColorMainPage);
            Assert.assertEquals("Discount price is not red on Item page ", "rgb(102, 102, 102)", discountPriceColorItemPage);
        }

        if (browserName == "InternetExplorer") {
            Assert.assertEquals("Discount price is not bold on Main page ", "", discountPriceDecorationMainPage);
            Assert.assertEquals("Discount price is not bold on Item page ", "", discountPriceDecorationItemPage);
        }
        else {
            Assert.assertEquals("Discount price is not bold on Main page ", "bold", discountPriceDecorationMainPage);
            Assert.assertEquals("Discount price is not bold on Item page ", "bold", discountPriceDecorationItemPage);
        }
    }

    @AfterClass
    public static void TearDown() {
        TestBase.Quit();
    }

}
