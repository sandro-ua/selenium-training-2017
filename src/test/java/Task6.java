import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task6 {

    public static WebDriver drv = new ChromeDriver();

    @Before
    public void Initialization() {
        ChromeDriverManager.getInstance().setup();
    }

    @Test
    public void Task6() {

        boolean cartIsNotEmpty;
        WebDriverWait wait = new WebDriverWait(drv, 10);

        for (int i = 0; i < 3; i++) {
            drv.get(Constants.MAIN_PAGE);
            drv.findElement(By.cssSelector("ul[class='listing-wrapper products'] div.price-wrapper")).click();

            if (drv.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0) {
                WebElement productSize = drv.findElement(By.cssSelector("select[name='options[Size]']"));
                Select select = new Select(productSize);
                select.selectByValue("Medium");
            }

            WebElement quantity = drv.findElement(By.cssSelector("span.quantity"));
            Integer currentQuantity = Integer.valueOf(quantity.getText());
            drv.findElement(By.cssSelector("button[name='add_cart_product']")).click();

            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(currentQuantity + 1)));
        }
        drv.findElement(By.cssSelector("div#cart a")).click();

        do {

            if (drv.findElements(By.cssSelector("ul.shortcuts > li")).size() > 0)
                drv.findElement(By.cssSelector("ul.shortcuts > li")).click();
            WebElement cartTable = drv.findElement(By.cssSelector("table[class='dataTable rounded-corners']"));
            drv.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(cartTable));
            cartIsNotEmpty = drv.findElements(By.cssSelector("table[class='dataTable rounded-corners']")).size() > 0;
        } while (cartIsNotEmpty == true);


        boolean cartIsEmptyMessageDisplayed = drv.findElements(By.xpath("//em[text() = 'There are no items in your cart.']")).size() > 0;
        Assert.assertTrue("Cart is not empty", cartIsEmptyMessageDisplayed);
    }
    @After
    public void finish() {
        drv.quit();
    }
}
