import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {

    @Test
    public void googleTest () {

        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.findElement(By.xpath("//*[@id=\"lst-ib\"]")).sendKeys("WebDRiver");
        driver.findElement(By.xpath("//input[@name='btnK']")).click();

        driver.quit();
    }
}
