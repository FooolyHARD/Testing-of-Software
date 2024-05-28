import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmsTest {

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
    public void seeTrailer() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        driver.get("http://www.ivi.ru/");
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div/div/div/div[1]/div[3]/div[2]/button/div"));
        btn.click();
        WebElement searchFilm = driver.findElement(By.xpath("//*[@id=\"search-root\"]/div/div[2]/div/div/section/div/div/form/div/div/input"));
        searchFilm.sendKeys("Слон");
        btn = driver.findElement(By.xpath("//*[@id=\"search-root\"]/div/div[2]/div/div/div/section[1]/div/div/div/div/div/ul/li[1]"));
        btn.click();
        btn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/section/div/div/div/div/div[3]/div/div[3]/div/div[1]/div[1]/button/div"));
        btn.click();
        Thread.sleep(10000);
        btn = driver.findElement(By.xpath("//*[@id=\"modal-container\"]/div/div[2]/div/div/div/div/div[2]/div/div"));
        btn.click();
        WebElement header = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/section/div/div/div/div/div[3]/div/div[3]/div/div[1]/div[1]/button/div"));
        assertEquals("Трейлер", header.getText());
    }

    @Test
    public void setGenre() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        driver.get("http://www.ivi.ru/");

        WebElement btn = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div/div/div/div[1]/div[2]/div/nav/ul/li[2]/a/div"));
        btn.click();
        btn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/section[3]/div/div/div/div[1]/div[1]/div/div"));
        btn.click();
        btn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/section[3]/div/div/div/div[1]/div[1]/div/div[2]/div/div/div[2]/ul/li[3]/label/div[1]"));
        btn.click();
        //Actions actions = new Actions(driver);
        //actions.moveToElement(btn).perform();
        // Ожидание появления панели и нужного элемента на ней
        ////WebElement genreElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        //WebElement genreElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"headerDropdownBody\"]/div[2]/div/div/div/div[1]/div/div[2]/div[1]/a")));
        WebElement header = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/section[2]/div/div/div[2]/div/div[1]/h1"));
        assertEquals("Боевики", header.getText());


    }
}