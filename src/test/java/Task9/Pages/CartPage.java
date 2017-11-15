package Task9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page {

    public CartPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul.shortcuts > li")
    private List<WebElement> productShortcuts;

    @FindBy(css = "ul.shortcuts > li")
    private WebElement productShortcut;

    @FindBy (css = "table[class='dataTable rounded-corners']")
    private WebElement cartTable;

    @FindBy (css = "table[class='dataTable rounded-corners']")
    private List<WebElement> cartTables;

    @FindBy (css = "button[name='remove_cart_item']")
    private WebElement removeCartItemButton;

    @FindBy (xpath = "//em[text() = 'There are no items in your cart.']")
    private List<WebElement> cartIsEmptyMessage;

    public boolean isProductShortcutAvailable () {
        return productShortcuts.size() > 0;
    }

    public CartPage clickFirstProductShortcut () {
        productShortcut.click();
        return this;
    }

    public CartPage removeCartItem () {
        removeCartItemButton.click();
        return this;
    }

    public boolean isCartIsEmptyMessageDisplayed () {
        return cartIsEmptyMessage.size() > 0;
    }

    public boolean isCartTableDisplayed () {
        return cartTables.size() > 0;
    }

    public CartPage removeProductFromCart () {

            if (isProductShortcutAvailable())
                clickFirstProductShortcut();
            removeCartItem();
            wait.until(ExpectedConditions.stalenessOf(cartTable));
            return this;
    }

}
