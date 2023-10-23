package sauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    @Test
    public void success_login_case(){
        WebDriver driver;
        String baseUrl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        //Membuka halaman login
        driver = new FirefoxDriver();
        driver.manage().window().maximize();//
        driver.get(baseUrl);
        String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'hai, kasirAja')]")).getText();
        Assert.assertEquals(loginPageAssert, "hai, kasirAja");

        //input email
        driver.findElement(By.id("email")).sendKeys("rakamin-kevin@gmail.com");
        //input password
        driver.findElement(By.id("password")).sendKeys("pass123$");
        //click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert nama toko di dashboard page
        String namaToko = driver.findElement(By.xpath("//dt[contains(text(),'toko bagus')]")).getText();
        Assert.assertEquals(namaToko, "toko bagus");

        //quit
        driver.close();
    }


   @Test
   public void failed_login_case(){
        WebDriver driver;
        String baseUrl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        //Membuka halaman login
        driver = new FirefoxDriver();
        driver.manage().window().maximize();//
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'hai, kasirAja')]")).getText();
        Assert.assertEquals(loginPageAssert, "hai, kasirAja");

        //input email
        driver.findElement(By.id("email")).sendKeys("rakamin-kevin@gmail.com");
        //input password yang salah
        driver.findElement(By.id("password")).sendKeys("pass123!");
        //click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String ErrorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        Assert.assertEquals(ErrorLogin, "Kredensial yang Anda berikan salah");
    }

}
