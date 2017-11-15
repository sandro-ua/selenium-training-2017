package Task9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopPageBlock extends Page {

    public TopPageBlock (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "span.quantity")
    private WebElement quantity;

    @FindBy (css = "div#cart a")
    private WebElement cartLink;

    public Integer getCurrentQuantity () {
        return Integer.valueOf(quantity.getText());
    }

    public CartPage openCart () {
        cartLink.click();
        return new CartPage(driver);
    }
}
