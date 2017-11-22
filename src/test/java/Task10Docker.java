import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task10Docker {

    static WebDriver drv;

    @Before
    public void Initialization() throws MalformedURLException {

        /*
        ChromeDriverManager.getInstance().setup();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("enableVNC", true);
        caps.setBrowserName("chrome");
        caps.setVersion("62.0");
        drv = new RemoteWebDriver(new URL(Constants.SELENIUM_GRID), caps);
        */

        FirefoxDriverManager.getInstance().setup();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("57.0");
        capabilities.setCapability("enableVNC", true);

        drv = new RemoteWebDriver(URI.create(Constants.SELENIUM_GRID).toURL(), capabilities);

        drv.get(Constants.ADMIN_PAGE_LINK_WORLD);
        drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        drv.findElement(By.cssSelector("input[name='username']")).clear();
        drv.findElement(By.cssSelector("input[name='username']")).sendKeys("demo");
        drv.findElement(By.cssSelector("input[name='password']")).clear();
        drv.findElement(By.cssSelector("input[name='password']")).sendKeys("demo");
        drv.findElement(By.cssSelector("button[name='login']")).click();
    }

    @Test
    public void Task3Docker1 () {

        List<WebElement> sectionItems = drv.findElements(By.cssSelector("li#app- > a > span.name"));
        for (int i=0; i<sectionItems.size(); i++) {
            drv.findElement(By.cssSelector("li#app-:nth-child(" + String.valueOf(i+1) +")")).click();

            if (drv.findElements(By.cssSelector("ul.docs")).size() == 0) {
                drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                String selectedSection = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                Assert.assertTrue("h1 tag is not present on the page " + selectedSection, drv.findElements(By.tagName("h1")).size() != 0);
            } else

            {
                List<WebElement> subSectionItems = drv.findElements(By.cssSelector("ul.docs > li"));

                for (int j=0; j<subSectionItems.size(); j++) {
                    drv.findElement(By.cssSelector("ul.docs > li:nth-child(" + String.valueOf(j+1) +")")).click();
                    String selectedSubSection = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                    Assert.assertTrue("h1 tag is not present on the page " + selectedSubSection, drv.findElements(By.tagName("h1")).size() != 0);
                }
            }
        }
    }

    @Test
    public void Task3Docker2 () {

        List<WebElement> sectionItems = drv.findElements(By.cssSelector("li#app- > a > span.name"));
        for (int i=0; i<sectionItems.size(); i++) {
            drv.findElement(By.cssSelector("li#app-:nth-child(" + String.valueOf(i+1) +")")).click();

            if (drv.findElements(By.cssSelector("ul.docs")).size() == 0) {
                drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                String selectedSection = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                Assert.assertTrue("h1 tag is not present on the page " + selectedSection, drv.findElements(By.tagName("h1")).size() != 0);
            } else

            {
                List<WebElement> subSectionItems = drv.findElements(By.cssSelector("ul.docs > li"));

                for (int j=0; j<subSectionItems.size(); j++) {
                    drv.findElement(By.cssSelector("ul.docs > li:nth-child(" + String.valueOf(j+1) +")")).click();
                    String selectedSubSection = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                    Assert.assertTrue("h1 tag is not present on the page " + selectedSubSection, drv.findElements(By.tagName("h1")).size() != 0);
                }
            }
        }
    }


    @Test
    public void Task3Docker3 () {

        List<WebElement> sectionItems = drv.findElements(By.cssSelector("li#app- > a > span.name"));
        for (int i=0; i<sectionItems.size(); i++) {
            drv.findElement(By.cssSelector("li#app-:nth-child(" + String.valueOf(i+1) +")")).click();

            if (drv.findElements(By.cssSelector("ul.docs")).size() == 0) {
                drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                String selectedSection = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                Assert.assertTrue("h1 tag is not present on the page " + selectedSection, drv.findElements(By.tagName("h1")).size() != 0);
            } else

            {
                List<WebElement> subSectionItems = drv.findElements(By.cssSelector("ul.docs > li"));

                for (int j=0; j<subSectionItems.size(); j++) {
                    drv.findElement(By.cssSelector("ul.docs > li:nth-child(" + String.valueOf(j+1) +")")).click();
                    String selectedSubSection = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                    Assert.assertTrue("h1 tag is not present on the page " + selectedSubSection, drv.findElements(By.tagName("h1")).size() != 0);
                }
            }
        }
    }

    @After
    public void finish() {
        drv.quit();
    }


}
