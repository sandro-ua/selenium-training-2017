import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdminPageSectionsTest extends TestBase {

    public final String ADMIN_PAGE_LINK = "http://localhost/litecart/admin/login.php";

    @Before
    public void Initialization () {
        ChromeDriverManager.getInstance().setup();

    }

    @Test
    public void AdminSectionsTitles (){
        drv.get(ADMIN_PAGE_LINK);
        drv.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
        drv.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        drv.findElement(By.cssSelector("input[name='login']")).click();


    }

    @After
    public void TearDown() {
        drv.quit();
    }



}
