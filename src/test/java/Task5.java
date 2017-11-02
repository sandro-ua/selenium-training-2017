import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

        //Open Catalog (Left menu)
        drv.findElement(By.xpath("//li[@id = 'app-']/a/span[contains(text(), 'Catalog')]")).click();

        //Click “Add New Product” (right top)
        drv.findElement(By.xpath("//a[@class='button'][contains(text(), 'Add New Product')]")).click();

        //Fill all fields on General, Information and Prices tabs (all other tabs as well as Campaigns on Prices may not be filled)
        drv.findElement(By.cssSelector("input[name='name[en]']")).sendKeys("product_" + uniqueID);
        drv.findElement(By.cssSelector("input[name='code']")).sendKeys("01");
        drv.findElement(By.cssSelector("input[name='product_groups[]'][value='1-3']")).click();
        drv.findElement(By.cssSelector("input[name='quantity']")).sendKeys("3");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("src\\resources\\images").getFile());
        drv.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(file.getAbsolutePath());

        drv.findElement(By.cssSelector("button[name='save']")).click();



//Note: You have to put “Product picture” file in project (resource) folder and use relative path instead of absolute:
// ClassLoader classLoader = getClass().getClassLoader();
// File file = new File(classLoader.getResource("image.png").getFile());
// fileField.sendKeys(file.getAbsolutePath());
//
//Save the Product
//Make sure that new product appeared in catalog (on admin page, client page verification is not necessary)



    }

    @After
    public void finish() {
        drv.quit();
    }
}