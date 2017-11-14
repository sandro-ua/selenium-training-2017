package Task9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {

    WebDriver drv;

    public CartPage (WebDriver drv) {
        this.drv = drv;
    }

    WebDriverWait wait = new WebDriverWait(drv, 10);

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

    public void clickFirstProductShortcut () {
        productShortcut.click();
    }

    public void removeCartItem () {
        removeCartItemButton.click();
    }

    public boolean cartIsEmptyMessageDisplayed () {
        return cartIsEmptyMessage.size() > 0;
    }

    public boolean isCartTableDisplayed () {
        return cartTables.size() > 0;
    }

    public boolean removeProductFromCart () {

            if (isProductShortcutAvailable())
                clickFirstProductShortcut();
            removeCartItem();
            wait.until(ExpectedConditions.stalenessOf(cartTable));
            return isCartTableDisplayed();
    }

}
