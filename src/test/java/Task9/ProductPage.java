package Task9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage {

    WebDriver drv;

    TestBase testBase = new TestBase(drv);

    public ProductPage (WebDriver drv) {
        this.drv = drv;
    }

    WebDriverWait wait = new WebDriverWait(drv, 10);

    @FindBy (how = How.CSS, using = "select[name='options[Size]']")
    private List<WebElement> productSizeDropdowns;

    @FindBy (how = How.CSS, using = "select[name='options[Size]']")
    private WebElement productSizeDropdown;

    @FindBy (css = "button[name='add_cart_product']")
    private WebElement addToCartButton;

    public boolean isProductSizeDropdownAvailable () {
    return (productSizeDropdowns.size() > 0);
    }


    public void selectProductSize (String size) {
        Select select = new Select(productSizeDropdown);
        select.selectByValue(size);
    }

    public void clickAddToCart () {
        addToCartButton.click();
    }

    public void addToCart (String size) {
        if (isProductSizeDropdownAvailable()) {
            selectProductSize(size);
        }

        Integer currentQuantity = testBase.topPageBlock.getCurrentQuantity();
        clickAddToCart();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(currentQuantity + 1)));
    }

}
