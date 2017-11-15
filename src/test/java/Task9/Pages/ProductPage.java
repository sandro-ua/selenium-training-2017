package Task9.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ProductPage extends Page {

    public ProductPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (how = How.CSS, using = "select[name='options[Size]']")
    private List<WebElement> productSizeDropdowns;

    @FindBy (how = How.CSS, using = "select[name='options[Size]']")
    private WebElement productSizeDropdown;

    @FindBy (css = "button[name='add_cart_product']")
    private WebElement addToCartButton;

    public boolean isProductSizeDropdownAvailable () {
    return (productSizeDropdowns.size() > 0);
    }

    public ProductPage selectProductSize (String size) {
        Select select = new Select(productSizeDropdown);
        select.selectByValue(size);
        return this;
    }

    public ProductPage clickAddToCart () {
        addToCartButton.click();
        return this;
    }

    public ProductPage selectSize (String size) {
        if (isProductSizeDropdownAvailable()) {
            selectProductSize(size);
        }
        return this;
    }
}

