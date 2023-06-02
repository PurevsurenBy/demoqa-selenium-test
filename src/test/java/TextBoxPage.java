import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBoxPage extends BasePage {

    private JavascriptExecutor js;

    public TextBoxPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://demoqa.com/text-box");
        this.js = (JavascriptExecutor) driver;
    }

    public void fillForm(String fullName, String email) {
        WebElement fullNameField = this.waitAndReturnElement(By.id("userName"));
        WebElement emailField = this.waitAndReturnElement(By.id("userEmail"));
        WebElement submitButton = this.waitAndReturnElement(By.id("submit"));

        js.executeScript("arguments[0].value = arguments[1];", fullNameField, fullName);
        js.executeScript("arguments[0].value = arguments[1];", emailField, email);
        js.executeScript("arguments[0].click();", submitButton);
    }

    public String getFullName() {
        WebElement fullNameP = this.waitAndReturnElement(By.xpath("//*[@id='output']/div/p[1]"));
        String fullNameText =  (String) js.executeScript("return arguments[0].innerText;", fullNameP);
        return fullNameText.split(":")[1].trim();
    }

    public String getEmail() {
        WebElement emailP = this.waitAndReturnElement(By.xpath("//*[@id='output']/div/p[2]"));
        String emailText =  (String) js.executeScript("return arguments[0].innerText;", emailP);
        return emailText.split(":")[1].trim();
    }
}
