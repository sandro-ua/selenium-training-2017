package Task9.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

    public MainPage (WebDriver drv) {
        super(drv);
        PageFactory.initElements(drv, this);
    }

    @FindBy (how = How.CSS, using = "ul[class='listing-wrapper products'] div.price-wrapper")
    private WebElement addToCartButton;

    public ProductPage openRandomProduct () {
        addToCartButton.click();
        return new ProductPage(drv);
    }
}
