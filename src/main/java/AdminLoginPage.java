import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage extends TestBase {

    public static void SiteLogin (WebDriver drv, String login, String password) {
        drv.get(Constants.ADMIN_PAGE_LINK);
        drv.findElement(By.cssSelector("input[name='username']")).sendKeys(login);
        drv.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        drv.findElement(By.cssSelector("button[name=login]")).click();
    }

}
