import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.*;

import java.util.*;  

import io.github.bonigarcia.wdm.WebDriverManager;


public class DemoqaSeleniumTest {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void testLogin() {
    
        LoginPage loginPage = new LoginPage(this.driver);        
        ProfilePage profilePage = loginPage.login("abc", "bookStore1Pass!");

        assertEquals("abc", profilePage.getUserName());
    }

    @Test
    public void testLogout() {

        LoginPage loginPage = new LoginPage(this.driver);
        ProfilePage profilePage = loginPage.login("abc", "bookStore1Pass!");
        LoginPage loginPageAfterLogout = profilePage.logout();

        String bodyText = loginPageAfterLogout.getBodyText();
        assertTrue("User should be logged out and back to login page.", bodyText.contains("Login"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}