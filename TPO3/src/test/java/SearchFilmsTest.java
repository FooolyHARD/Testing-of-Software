import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchFilmsTest {

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
    public void successSearchFilm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        driver.get("http://www.ivi.ru/");
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div/div/div/div[1]/div[3]/div[2]/button/div"));
        btn.click();
        WebElement searchFilm = driver.findElement(By.xpath("//*[@id=\"search-root\"]/div/div[2]/div/div/section/div/div/form/div/div/input"));
        searchFilm.sendKeys("Слон");
        Thread.sleep(2000);
        WebElement header = driver.findElement(By.xpath("//*[@id=\"search-root\"]/div/div[2]/div/div/div/section[1]/div/div/h1"));
        assertEquals("Результаты поиска", header.getText());
    }

    @Test
    public void failedSearchFilm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        driver.get("http://www.ivi.ru/");
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div/div/div/div[1]/div[3]/div[2]/button/div"));
        btn.click();
        WebElement searchFilm = driver.findElement(By.xpath("//*[@id=\"search-root\"]/div/div[2]/div/div/section/div/div/form/div/div/input"));
        searchFilm.sendKeys("абобус");
        Thread.sleep(2000);
        WebElement header = driver.findElement(By.xpath("//*[@id=\"search-root\"]/div/div[2]/div/div/div/section/div/div/h1"));
        assertEquals("Ничего не нашлось. Возможно, тебя заинтересует", header.getText());
    }
}