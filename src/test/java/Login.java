import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {
    @Test //Tag untuk running script dibawah ini
    public void open_browser(){
        WebDriver driver;
        String baseUrl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        driver = new ChromeDriver();
        driver.manage().window().maximize();//
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println(title);

        driver.close();
    }

    @Test
    public void get_title(){
        WebDriver driver;
        String baseUrl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        driver = new FirefoxDriver();
        driver.manage().window().maximize();//
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println(title);

        WebElement ele1 = driver.findElement(By.id("Username"));
        WebElement ele2 = driver.findElement(By.className("button"));

        //Get Form

        ele1.click();
        ele1.sendKeys("email.com");
        ele1.getText();


        ele2.isDisplayed();
        ele2.click();

        driver.findElement(By.xpath("/*contains")).isDisplayed();
        driver.findElement(By.cssSelector("#button")).isDisplayed();

        driver.close();
    }
}
