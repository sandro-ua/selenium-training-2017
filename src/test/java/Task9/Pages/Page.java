package Task9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver drv;
    protected WebDriverWait wait;

    public Page(WebDriver drv) {
        this.drv = drv;
        wait = new WebDriverWait(drv, 10);
    }
}


