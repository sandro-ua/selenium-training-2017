import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class Task1 {

    private static WebDriver driver;
    WebDriverWait wait = new WebDriverWait (driver, 10);

    @Before
    public void start() {

    }

    @Test
    public void googleTestChrome() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com.ua");
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys("driver");
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys(Keys.ENTER);

        driver.quit();
    }

    @Test
    public void googleTestIE() {
        InternetExplorerDriverManager.getInstance().arch32().setup();
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com.ua");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector("#lst-ib")).sendKeys("driver");
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys(Keys.ENTER);
        driver.quit();
    }

    @Test
    public void googleTestFirefox() {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com.ua");
        WebElement search = wait.until((WebDriver d) -> d.findElement(By.cssSelector("#lst-ib")));
        search.sendKeys("driver");
        search.sendKeys(Keys.ENTER);
        driver.quit();
    }

    @After
    public void finish() {
        driver.quit();
    }
}