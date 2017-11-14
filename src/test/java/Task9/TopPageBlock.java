package Task9;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopPageBlock {

    WebDriver drv;

    public TopPageBlock (WebDriver drv) {
        this.drv = drv;
    }

    @FindBy (css = "span.quantity")
    private WebElement quantity;

    @FindBy (css = "div#cart a")
    private WebElement cartLink;

    public Integer getCurrentQuantity () {
        return Integer.valueOf(quantity.getText());
    }

    public void openCart () {
        cartLink.click();
    }


}
