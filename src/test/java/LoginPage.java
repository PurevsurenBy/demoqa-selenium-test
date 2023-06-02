import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://demoqa.com/login");
    }

    public ProfilePage login(String username, String password) {
        this.waitAndReturnElement(By.id("userName")).sendKeys(username);
        this.waitAndReturnElement(By.id("password")).sendKeys(password);
        this.waitAndReturnElement(By.id("login")).click();
        return new ProfilePage(this.driver);
    }

    public Boolean isUsernameAndPasswordDisplayed() {
        WebElement usernameField = this.waitAndReturnElement(By.id("userName"));
        WebElement passwordField = this.waitAndReturnElement(By.id("password"));
        return usernameField.isDisplayed() && passwordField.isDisplayed();
    } 

    public String getTitle() {
        return this.driver.getTitle();
    }
}
