import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class Task7 {

    public static WebDriver drv = new ChromeDriver();

    @Before
    public void Initialization() {
        ChromeDriverManager.getInstance().setup();

        // Login to admin panel
        drv.get(Constants.ADMIN_PAGE_LINK);
        drv.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
        drv.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        drv.findElement(By.cssSelector("button[name=login]")).click();
    }

    @Test
    public void Task7() {

        // Open Countries (http://localhost/litecart/admin/?app=countries&doc=countries )
        drv.findElement(By.xpath("//span[contains(text(), 'Countries')]")).click();
        drv.findElement(By.xpath("//a[@class='button'][contains(@href, 'edit_country')]")).click();
        WebDriverWait wait = new WebDriverWait(drv, 5);

        // Open on Edit any country or click “add new country”
        List<WebElement> newWindowElements = drv.findElements(By.cssSelector("i[class='fa fa-external-link']"));
        String parentWindowHandle = drv.getWindowHandle();

        for (int i = 0; i < newWindowElements.size(); i++) {

            /*
            alternative way
            newWindowElements.get(i).click();
            ExpectedCondition<Boolean> windowCondition = driver -> drv.getWindowHandles().size() == parentWindowHandle.size() + 1;
            WebDriverWait waitForWindow = new WebDriverWait(drv, 5);
            waitForWindow.until(windowCondition);
            String popUpWindowHandle = drv.getWindowHandles().toArray()[1].toString();
            drv.switchTo().window(popUpWindowHandle);
            */

            Set<String> existWs = drv.getWindowHandles();
            newWindowElements.get(i).click();
            String newW = wait.until (anyWindowOtherThen (existWs));

            drv.switchTo().window(newW);
            drv.close();
            drv.switchTo().window(parentWindowHandle.toString());
        }
    }

    @After
    public void finish() {
        drv.quit();
    }


    public ExpectedCondition<String> anyWindowOtherThen (Set<String> windows) {
        return new ExpectedCondition<String>() {
            @Nullable
            @Override
            public String apply(@Nullable WebDriver input) {
                Set<String> handles = drv.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}
