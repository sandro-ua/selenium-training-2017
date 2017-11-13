import com.google.common.io.Files;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Task8 {

    static EventFiringWebDriver drv;

    public class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy (By by, WebElement element, WebDriver driver) {
            System.out.println("Start searching " + by);
        }

        @Override
        public void afterFindBy (By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException (Throwable throwable, WebDriver driver) {
            System.out.println("Exception: " + throwable);

            try {
                File tempFile = ((TakesScreenshot)drv).getScreenshotAs(OutputType.FILE);
                Files.copy(tempFile, new File ("screen.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Before
    public void Initialization () {
        ChromeDriverManager.getInstance().setup();

        drv = new EventFiringWebDriver (new ChromeDriver());
        drv.register(new MyListener());

        System.out.println("LOGS: " + drv.manage().logs().getAvailableLogTypes());

        drv.get(Constants.ADMIN_PAGE_LINK);
        drv.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
        drv.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        drv.findElement(By.cssSelector("button[name=login]")).click();
    }

    @Test
    public void Task8 () {

        List<WebElement> sectionItems = drv.findElements(By.cssSelector("li#app- > a > span.name"));
        for (int i=0; i<sectionItems.size(); i++) {
            drv.findElement(By.cssSelector("li#app-:nth-child(" + String.valueOf(i+1) +")")).click();

            if (drv.findElements(By.cssSelector("ul.docs")).size() == 0) {
                String selectedSection = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                Assert.assertTrue("h1 tag is not present on the page " + selectedSection, drv.findElements(By.tagName("h1")).size() != 0);
            } else

            {
                List<WebElement> subSectionItems = drv.findElements(By.cssSelector("ul.docs > li"));

                for (int j=0; j<subSectionItems.size(); j++) {
                    drv.findElement(By.cssSelector("ul.docs > li:nth-child(" + String.valueOf(j+1) +")")).click();
                    String selectedSubSection = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                    //Assert.assertTrue("h1 tag is not present on the page " + selectedSubSection, drv.findElements(By.tagName("h1")).size() != 0);

                    // Failing test fot task 8
                    drv.findElement(By.cssSelector("li.selected > h3 >")).getText().trim();

                }
            }
        }
    }

    @After
    public void TearDown() {

        for (LogEntry l : drv.manage().logs().get("browser").getAll()) {
            System.out.println("BROWSER LOGS: " + l);
        }
        drv.quit();
    }
}
