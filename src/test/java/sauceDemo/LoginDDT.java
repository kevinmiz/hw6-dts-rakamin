package sauceDemo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {
   //Login menggunakan fitur Data Driven Test (DDT)
    @Test
    public void login_ddt(){
        WebDriver driver;
        String baseUrl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();
        //ChromeOptions opt = new ChromeOptions();

        String csvDir = System.getProperty("user.dir")+"/src/test/data/test-data.csv";

        try(CSVReader reader = new CSVReader(new FileReader(csvDir))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null){
                String email = nextLine[0]; // read column 1 for email
                String password = nextLine[1]; // read column 2 for password
                String status = nextLine[2]; // read column 3 for expected login status

                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout for web driver on waiting element
                driver.manage().window().maximize(); //maximize windows
                driver.get(baseUrl);

                //Pengisian form
                driver.findElement(By.id("email")).sendKeys(email);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//button[@type='submit']")).click();

                //Assertion
                if(status.equals("success")) { //Jika success
                    driver.findElement(By.xpath("//div[contains(text(),'dashboard')]"));
                    String username = driver.findElement(By.xpath("//dd[contains(text(),'hai')]/preceding-sibling::dt")).getText();
                    Assert.assertEquals(username, "tdd-selenium");
                } else { //Jika failed
                    String ErrorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
                    Assert.assertEquals(ErrorLogin, "Kredensial yang Anda berikan salah");
                }
                driver.close();
            }
        } catch(CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }
    }
}
