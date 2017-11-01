import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class Task1 extends TestBase {

    WebDriver driver = TestBase.getDriver();

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
        this.driver = TestBase.getDriver();
    }

    @Test
    public void googleTest() {
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.cssSelector("input[name=q]"));
        element.sendKeys("driver");
        element.sendKeys(Keys.ENTER);
    }

    @After
    public void finish() {
        TestBase.Quit();
    }
}