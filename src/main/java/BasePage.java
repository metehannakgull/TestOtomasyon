import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage
{
    protected WebDriver webDriver;
    public BasePage(WebDriver driver)
    {
        this.webDriver=driver;
    }

    public WebElement find(By locator)
    {
        return webDriver.findElement(locator);
    }

}
