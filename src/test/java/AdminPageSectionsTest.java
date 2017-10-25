import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AdminPageSectionsTest extends TestBase {

    @Before
    public void Initialization () {
        ChromeDriverManager.getInstance().setup();
    }

    @Test
    public void AdminSectionsTitles (){
        drv.get(Constants.ADMIN_PAGE_LINK);
        drv.findElement(By.cssSelector("input[name='username']")).sendKeys("admin");
        drv.findElement(By.cssSelector("input[name='password']")).sendKeys("admin");
        drv.findElement(By.cssSelector("button[name=login]")).click();
        String h1Title ;
        String h1SubTitle ;
        String selectedSubTitle;
        String selectedTitle;

        List<WebElement> sectionItems = drv.findElements(By.cssSelector("li#app- > a > span.name"));
        for (int i=0; i<sectionItems.size(); i++) {
            drv.findElement(By.cssSelector("li#app-:nth-child(" + String.valueOf(i+1) +")")).click();

            if (drv.findElements(By.cssSelector("ul.docs")).size() == 0) {

                h1Title = drv.findElement(By.cssSelector("td#content > h1")).getText().trim();
                selectedTitle = drv.findElement(By.cssSelector("li.selected > a > span:nth-child(2)")).getText().trim();
                Assert.assertEquals("The title doesn't match its section name", selectedTitle, h1Title);
            } else

            {
                List<WebElement> subSectionItems = drv.findElements(By.cssSelector("ul.docs > li"));

                for (int j=0; j<subSectionItems.size(); j++) {
                    drv.findElement(By.cssSelector("ul.docs > li:nth-child(" + String.valueOf(j+1) +")")).click();
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
