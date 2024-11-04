import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class InputsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void inputs() {
        //проверяем ArrowUp
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.findElement(By.tagName("input")).sendKeys("20");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        String result = driver.findElement(By.tagName("input")).getAttribute("value");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(result, "21", "Value is incorrect");

        //проверяем ArrowDown
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        String resultDown = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(resultDown, "20", "Value is incorrect");

        //проверяем ввод букв
        driver.findElement(By.tagName("input")).clear();
        driver.findElement(By.tagName("input")).sendKeys("tst");
        String resultLetters = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(resultLetters, "");
        softAssert.assertAll();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
