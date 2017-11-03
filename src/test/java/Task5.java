import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.UUID;

public class Task5 {

    public static WebDriver drv = new ChromeDriver();

    @Before
    public void Initialization() {
        ChromeDriverManager.getInstance().setup();
        drv.get(Constants.ADMIN_PAGE_LINK);
        drv.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
        drv.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        drv.findElement(By.cssSelector("button[name=login]")).click();
    }

    @Test
    public void Test5() {

        String uniqueID = UUID.randomUUID().toString();

        // Open Catalog (Left menu)
        drv.findElement(By.xpath("//li[@id = 'app-']/a/span[contains(text(), 'Catalog')]")).click();

        // Click “Add New Product” (right top)
        drv.findElement(By.xpath("//a[@class='button'][contains(text(), 'Add New Product')]")).click();

        // Filling all fields
        // Tab General
        drv.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        drv.findElement(By.cssSelector("input[name='name[en]']")).sendKeys("product_" + uniqueID);
        drv.findElement(By.cssSelector("input[name='code']")).sendKeys("01");
        drv.findElement(By.cssSelector("input[name='product_groups[]'][value='1-3']")).click();
        typeIntoCustomInput (drv.findElement(By.cssSelector("input[name='quantity']")), "3");
        ((JavascriptExecutor) drv).executeScript("document.getElementsByName('date_valid_from')[0].setAttribute('value','2017-11-03')");
        ((JavascriptExecutor) drv).executeScript("document.getElementsByName('date_valid_to')[0].setAttribute('value','2018-11-03')");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("duckinglasses.jpg").getFile());
        drv.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(file.getAbsolutePath());

        // Tab Information
        drv.findElement(By.cssSelector("a[href='#tab-information']")).click();
        selectDropdownByValue (drv.findElement(By.cssSelector("select[name='manufacturer_id'")), "1");
        drv.findElement(By.cssSelector("input[name='keywords']")).sendKeys("duck, toy");
        drv.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("duck in glasses");
        drv.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("duck in glasses - very cool toy");
        drv.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("Duck in Glasses");
        drv.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("Duck in Glasses");

        // Prices tab
        drv.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        typeIntoCustomInput(drv.findElement(By.cssSelector("input[name='purchase_price']")), "3");
        selectDropdownByValue(drv.findElement(By.cssSelector("select[name='purchase_price_currency_code'")), "EUR");
        typeIntoCustomInput(drv.findElement(By.cssSelector("input[name='prices[USD]']")), "25");
        typeIntoCustomInput(drv.findElement(By.cssSelector("input[name='prices[EUR]']")), "21");

        // Save
        drv.findElement(By.cssSelector("button[name='save']")).click();

        //Make sure that new product appeared in catalog
        drv.findElement(By.xpath("//span[contains(text(), 'Catalog')]"));
        boolean isProductPresentInCatalog = drv.findElements(By.xpath(String.format("//a[contains(text(), '%s')]", uniqueID))).size() > 0;
        Assert.assertTrue("Product wasn't created", isProductPresentInCatalog);
    }

    @After
    public void finish() {
        drv.quit();
    }

    public void typeIntoCustomInput (WebElement el, String value) {
        el.clear();
        el.sendKeys(value);
    }

    public void selectDropdownByValue (WebElement el, String value) {
        Select select = new Select(el);
        select.selectByValue(value);
    }
}