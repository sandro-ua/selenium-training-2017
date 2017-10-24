import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

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
        drv.findElement(By.cssSelector("button[name=login]")).click();
        String h1Title ;
        String h1SubTitle ;
        String selectedSubTitle;


        List<WebElement> sectionItems = drv.findElements(By.cssSelector("li#app- > a > span.name"));
        for (int i=0; i<sectionItems.size(); i++) {
            sectionItems.get(i).click();

            if (drv.findElements(By.cssSelector("ul.docs")).size() == 0) {

                h1Title = drv.findElement(By.cssSelector("td#content > h1")).getText().trim();
                Assert.assertEquals("The title doesn't match its section name", sectionItems.get(i).getText().trim(), h1Title);

            } else

            {
                List<WebElement> subSectionItems = drv.findElements(By.cssSelector("ul.docs > li"));

                for (int j=0; j<subSectionItems.size(); j++) {
                    h1SubTitle = drv.findElement(By.cssSelector("td#content > h1")).getText().trim();
                    selectedSubTitle = drv.findElement(By.cssSelector("ul.docs > li.selected > a > span")).getText().trim();
                    Assert.assertEquals("The title doesn't match its section name", selectedSubTitle, h1SubTitle);
                }
            }
        }
    }

    @After
    public void TearDown() {
        drv.quit();
    }
}
