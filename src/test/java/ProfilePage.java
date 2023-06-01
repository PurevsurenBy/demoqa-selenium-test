import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        return this.waitAndReturnElement(By.id("userName-value")).getText();
    }

    public LoginPage logout() {
        this.waitAndReturnElement(By.id("submit")).click();
        return new LoginPage(this.driver);
    } 

}
