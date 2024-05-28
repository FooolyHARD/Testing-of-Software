import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void clickDemo() throws InterruptedException {
        driver.get("http://www.ivi.ru/");
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div/div/div/div[1]/div[4]/a/div/div/div"));
        btn.click();
        Thread.sleep(5000);
        WebElement header = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/section[1]/div/div/h1"));
        assertEquals("Уведомления и акции", header.getText());
    }
}