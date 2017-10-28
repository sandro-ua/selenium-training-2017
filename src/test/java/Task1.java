import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task1 extends TestBase {

    @Test
    public void googleTest() {
        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https:\\www.google.com");
        driver.quit();
    }
}
