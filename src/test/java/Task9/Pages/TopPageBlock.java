package Task9.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TopPageBlock extends Page {

    @FindBy(css = "span.quantity")
    private WebElement quantity;

    @FindBy(css = "div#cart a")
    private WebElement cartLink;

    public TopPageBlock(WebDriver drv) {
        super(drv);
        PageFactory.initElements(drv, this);
    }

    public Integer getCurrentQuantity() {
        return Integer.valueOf(quantity.getText());
    }

    public CartPage openCart() {
        cartLink.click();
        return new CartPage(drv);
    }

    public TopPageBlock waitTillQuantityChanges(Integer currentQuantity) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(currentQuantity + 1)));
        return this;
    }
}
