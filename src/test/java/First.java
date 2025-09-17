import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class First {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void newWindowHandling() {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.xpath("//button[@id=\"windowButton\"]")).click();
        String parentHandel=driver.getWindowHandle();

      Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {


                driver.switchTo().window(windowHandle);
            WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement headingElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id=\"sampleHeading\"]")));
               String headingText= headingElement.getText();
            Assert.assertEquals(headingText,"This is a sample page","Heading is matched");
            driver.close();
        }
    driver.switchTo().window(parentHandel);
        System.out.println("Back to main window"+driver.getTitle());


    }
@Test
        public void  getScreenShot() throws IOException {
            driver.get("https://www.daraz.pk/");
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // ✅ Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // ✅ Save screenshot to a custom location
            File destFile = new File("C:\\Users\\TanBits\\Documents\\ScreenShot"+timestamp + ".png");
            Files.copy(srcFile.toPath(), destFile.toPath());

            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());

            driver.quit();
        }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
