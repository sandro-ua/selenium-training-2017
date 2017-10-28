import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Task4 extends TestBase {

    @Before
    public void Initialization () {
        ChromeDriverManager.getInstance().setup();
        drv.get(Constants.MAIN_PAGE);
    }


    @Test
    public void Task4 () {
        // not finished
        WebElement regularPriceMainPage = drv.findElement(By.cssSelector("#box-campaigns s.regular-price"));
        String discountPriceMainPage = drv.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getText();


        String productNameMainPage = drv.findElement(By.cssSelector("#box-campaigns div.name")).getText();
        String regularPriceValueMainPage = regularPriceMainPage.getText();



        drv.findElement(By.cssSelector("#box-campaigns > div > ul > li > a.link")).click();

        String productNameItemPage = drv.findElement(By.cssSelector("h1.title")).getText();
        String regularPriceItemPage = drv.findElement(By.cssSelector("#box-product s.regular-price")).getText();
        String discountPriceItemPage = drv.findElement(By.cssSelector("#box-product strong.campaign-price")).getText();

        //task 4.4a
        Assert.assertEquals("Product Name is not equal on Main page and Item Page", productNameMainPage, productNameItemPage);

        //task 4.4b
        Assert.assertEquals("Regular price is not equal on Main page and Item Page", regularPriceValueMainPage, regularPriceItemPage);
        Assert.assertEquals("Discount price is not equal on Main page and Item Page", discountPriceMainPage, discountPriceItemPage);

        //task 4.4.c
        WebElement regularPriceItemPageEl = drv.findElement(By.cssSelector("#box-product s.regular-price"));
        String regularPriceColorItemPage = regularPriceItemPageEl.getCssValue("color");
        String regularPriceDecorationItemPage = regularPriceItemPageEl.getCssValue("text-decoration-line");
        String regularPriceColorMainPage = regularPriceItemPageEl.getCssValue("color");



    }

    @After
    public void TearDown() {
        drv.quit();
    }

}
