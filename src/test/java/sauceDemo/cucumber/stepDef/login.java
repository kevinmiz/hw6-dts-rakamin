package sauceDemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";


    @Given("Halaman login sauce Demo")
    public void halaman_login_sauce_demo() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout for web driver on waiting element
        driver.get(baseUrl);

        //Assertion
        boolean loginPageAssert = driver.findElement(By.name("login-button")).isDisplayed();
        Assert.assertTrue(true);
    }

    @When("input valid username")
    public void input_valid_username() {
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys(username);
    }

    @And("input valid password")
    public void input_valid_password() {
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys(password);
    }

    @And("click login button")
    public void click_login_button() {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @Then("User in on home page")
    public void user_in_on_home_page() {
        String verify_success = driver.findElement(By.cssSelector(".app_logo")).getText();
        Assert.assertEquals("Swag Labs",verify_success);
        boolean verify_home = driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).isDisplayed();
        Assert.assertTrue(verify_home);
        driver.close();
    }


    @And("input Invalid password")
    public void input_invalid_password() {
        driver.findElement(By.id("Password")).sendKeys("pass123!");
    }

    @Then("User get error message")
    public void user_get_error_message() {
        String ErrorLogin = driver.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error > h3")).getText();
        Assert.assertEquals(ErrorLogin, "Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }

}

