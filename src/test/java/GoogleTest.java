import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {

    @Test
    public void googleTest () {

        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");

        driver.findElement(By.xpath("//*[@id=\"lst-ib\"]")).sendKeys("WebDRiver");
        driver.findElement(By.xpath("//input[@name='btnK']")).click();

        driver.quit();
    }
}
