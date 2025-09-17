import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Practice {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void getText(){

        driver.get("https://staging.healthdesk.ai/login/previous_request_path=%2F");
        String text=driver.findElement(By.xpath("//button[text()='Submit']")).getText();
        System.out.println(text);
        String idValue=driver.findElement(By.xpath("//label[text()='Phone number or email']")).getAttribute("for");
        System.out.println(idValue);
    }

    @Test
    public void getT(){

        driver.get("https://staging.healthdesk.ai/login/previous_request_path=%2F");
        String text=driver.findElement(By.xpath("//button[text()='Submit']")).getText();
        System.out.println(text);
        String idValue=driver.findElement(By.xpath("//label[text()='Phone number or email']")).getAttribute("for");
        System.out.println(idValue);
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
