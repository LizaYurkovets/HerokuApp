import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class TyposTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void typos() {
        driver.get("https://the-internet.herokuapp.com/typos");

        for (int i = 0; i < 10; i++) {
            driver.navigate().refresh();

            List<WebElement> elements = driver.findElements(By.tagName("p"));
            SoftAssert softAssert = new SoftAssert();

            String text = "";
            for (WebElement element : elements) {
                text = element.getText();
            }

            softAssert.assertEquals(text, "Sometimes you'll see a typo, other times you won't.",
                    "There's a mistake in won,t");
            softAssert.assertAll();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
