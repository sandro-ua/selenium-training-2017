package Task9;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    WebDriver drv;

    public MainPage (WebDriver drv) {
        this.drv = drv;
    }

    @FindBy (how = How.CSS, using = "ul[class='listing-wrapper products'] div.price-wrapper")
    private WebElement addToCartButton;

    public void openRandomProduct () {
        addToCartButton.click();
    }

}
