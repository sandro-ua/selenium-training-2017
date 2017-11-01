import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected static WebDriver driver;


    public static WebDriver
    getDriver()
    {
        return driver;
    }

    public static void Initialize()
    {
        driver = getFirefoxDriver();
    }
    public static void Quit()
    {
        getDriver().quit();
    }

    public static void Initialize(String browser)
    {
        if (browser.equalsIgnoreCase("Chrome"))
        {
            ChromeDriverManager.getInstance().setup();
            driver = getChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return;
        }

        if (browser.equalsIgnoreCase("InternetExplorer"))
        {
            InternetExplorerDriverManager.getInstance().arch32().setup();
            driver = getInternetExplorerDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return;
        }

        if (browser.equalsIgnoreCase("Firefox"))
        {
            FirefoxDriverManager.getInstance().setup();
            driver = getFirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return;
        }
    }

    private static WebDriver getChromeDriver()
    {
        return  new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver()
    {
        return new FirefoxDriver();
    }

    private static WebDriver getInternetExplorerDriver()
    {
        return  new InternetExplorerDriver();
    }
}