import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceTest {

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
    public void checkPrice() throws InterruptedException {
        driver.get("http://www.ivi.ru/");

        WebElement btn = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div/div/div/div[1]/div[5]/a/button"));
        btn.click();
        btn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/section/div/div/div[1]/div[2]/button[1]/div"));
        btn.click();
        WebElement login = driver.findElement(By.xpath("//*[@id=\"modal-container\"]/div/div[2]/div/div/div[2]/div/div/div/div/form/div[1]/div/div/div/div/input"));
        login.sendKeys("k.arseny03@gmail.com");
        btn = driver.findElement(By.xpath("//*[@id=\"modal-container\"]/div/div[2]/div/div/div[2]/div/div/div/div/form/div[2]/div/div[1]/button/div"));
        btn.click();
        WebElement password = driver.findElement(By.xpath("//*[@id=\"modal-container\"]/div/div[2]/div/div/div[2]/div/div/div/div/div/form/div[2]/div/div/div/div/input"));
        password.sendKeys("Bober228");
        Thread.sleep(1000);
        btn = driver.findElement(By.xpath("//*[@id=\"modal-container\"]/div/div[2]/div/div/div[2]/div/div/div/div/div/form/div[3]/div/div/button"));
        btn.click();
        Thread.sleep(3000);
        btn = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div/div/div/div[1]/div[3]/div[1]/div/div"));
        btn.click();
        btn = driver.findElement(By.xpath("//*[@id=\"modal-container\"]/div/div[2]/div/div/div/section[1]/div[2]/div/div[1]/div/div"));
        btn.click();
        WebElement header = driver.findElement(By.xpath("//*[@id=\"modal-container\"]/div[2]/div[2]/div/div/div[1]/div[3]"));
            assertEquals("Иви на 30 дней за 0 ₽", header.getText());
    }

    @Test
    public void buySubscriptionNotAuth() throws InterruptedException {
        driver.get("http://www.ivi.ru/");

        WebElement btn = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div/div/div/div[1]/div[3]/div[1]/div/div"));
        btn.click();
        WebElement header = driver.findElement(By.xpath("//*[@id=\"modal-container\"]/div/div[2]/div/div/div[1]/div[2]"));
        assertEquals("Вход или регистрация", header.getText());
    }
}
