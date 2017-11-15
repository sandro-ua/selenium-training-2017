package Task9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ProductPage extends Page {

    @FindBy(how = How.CSS, using = "select[name='options[Size]']")
    private List<WebElement> productSizeDropdowns;
    @FindBy(how = How.CSS, using = "select[name='options[Size]']")
    private WebElement productSizeDropdown;
    @FindBy(css = "button[name='add_cart_product']")
    private WebElement addToCartButton;

    public ProductPage(WebDriver drv) {
        super(drv);
        PageFactory.initElements(drv, this);
    }

    public boolean isProductSizeDropdownAvailable() {
        return (productSizeDropdowns.size() > 0);
    }

    public ProductPage selectProductSize(String size) {
        Select select = new Select(productSizeDropdown);
        select.selectByValue(size);
        return this;
    }

    public ProductPage clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    public ProductPage selectSize(String size) {
        if (isProductSizeDropdownAvailable()) {
            selectProductSize(size);
        }
        return this;
    }
}

