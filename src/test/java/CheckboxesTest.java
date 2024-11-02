import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.List;

public class CheckboxesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkCheckboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkbox = driver.findElements(By.cssSelector("[type=checkbox]"));

        Assert.assertFalse(checkbox.get(0).isSelected());
        checkbox.get(0).click();
        Assert.assertTrue(checkbox.get(0).isSelected());
        Assert.assertTrue(checkbox.get(1).isSelected());
        checkbox.get(1).click();
        Assert.assertFalse(checkbox.get(1).isSelected());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}