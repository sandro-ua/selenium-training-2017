import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class Task10Docker {

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        WebDriver drv = new RemoteWebDriver(new URL(Constants.SELENIUM_GRID), caps);
        WebDriverWait wait = new WebDriverWait(drv, 10);

    public Task10Docker() throws MalformedURLException {
    }


    @Before
    public void Initialization() {
        ChromeDriverManager.getInstance().setup();

        // Login to admin panel
        drv.get(Constants.ADMIN_PAGE_LINK_WORLD);
        drv.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
        drv.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        drv.findElement(By.cssSelector("button[name=login]")).click();
    }

    @Test
    public void Task10() {

        // Open Countries (http://localhost/litecart/admin/?app=countries&doc=countries )
        drv.findElement(By.xpath("//span[contains(text(), 'Countries')]")).click();
        drv.findElement(By.xpath("//a[@class='button'][contains(@href, 'edit_country')]")).click();

        // Open on Edit any country or click “add new country”
        List<WebElement> newWindowElements = drv.findElements(By.cssSelector("i[class='fa fa-external-link']"));
        Set<String> parentWindowHandle = drv.getWindowHandles();

        for (int i = 0; i < newWindowElements.size(); i++) {

            newWindowElements.get(i).click();
            ExpectedCondition<Boolean> windowCondition = driver -> drv.getWindowHandles().size() == parentWindowHandle.size() + 1;
            WebDriverWait waitForWindow = new WebDriverWait(drv, 5);
            waitForWindow.until(windowCondition);
            String popUpWindowHandle = drv.getWindowHandles().toArray()[1].toString();

            drv.switchTo().window(popUpWindowHandle);
            drv.close();
            drv.switchTo().window(parentWindowHandle.toArray()[0].toString());
        }
    }

    @After
    public void finish() {
        drv.quit();
    }


}
