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
    private Config config;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        this.config = new Config();
    }

    @Test
    public void testMultipleStaticPages() {
        String[] urls = config.getUrls();
        for(String url: urls) {
            driver.get(url);
            WebElement body = driver.findElement(By.tagName("body"));
            
            assertTrue(body.isDisplayed());
        }
    }

    @Test
    public void testLoginLogout() {
        LoginPage loginPage = new LoginPage(this.driver);
        ProfilePage profilePage = loginPage.login(config.getProperty("username"), config.getProperty("password"));

        assertEquals(config.getProperty("username"), profilePage.getUserName());

        LoginPage loginPageAfterLogout = profilePage.logout();
        String bodyText = loginPageAfterLogout.getBodyText();

        assertTrue("User should be logged out and back to login page.", bodyText.contains("Login"));
    }

    @Test
    public void testFormSubmission() {
        FormPage formPage = new FormPage(this.driver);
        formPage.fillForm(config.getProperty("firstName"), config.getProperty("lastName"), config.getProperty("mobNumber"));
        formPage.submitForm();
        String confirmationMessage = formPage.getConfirmationMessage();

        assertEquals("Thanks for submitting the form", confirmationMessage);
    }

    @Test
    public void testDragAndDrop() {
        DroppablePage droppablePage = new DroppablePage(this.driver);
        droppablePage.dragAndDrop();

        String actualText = droppablePage.getDroppableBoxText();
        assertEquals(config.getProperty("expectedMessageDrop"), actualText);
    }

    @Test
    public void testTextBoxPage() {
        TextBoxPage textBoxPage = new TextBoxPage(this.driver);
        String fullName = config.getProperty("firstName") + " " + config.getProperty("lastName");
        String email = config.getProperty("email");
        textBoxPage.fillForm(fullName, email);

        assertEquals(fullName, textBoxPage.getFullName());
        assertEquals(email, textBoxPage.getEmail());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}