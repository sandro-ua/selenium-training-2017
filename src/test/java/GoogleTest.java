import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {

    @Test
    public void googleTest () {

        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https:\\www.google.com");
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"lst-ib\"]"));
        searchBox.sendKeys("WebDriver");
        searchBox.sendKeys(Keys.RETURN);

        driver.quit();
    }
}
