import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPage extends BasePage {

    public FormPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://demoqa.com/automation-practice-form");
    }

    public FormPage fillForm(String firstName, String lastName, String mobNumber) {
        this.waitAndReturnElement(By.xpath("//*[@id='firstName']")).sendKeys(firstName);
        this.waitAndReturnElement(By.xpath("//*[@id='lastName']")).sendKeys(lastName);
        this.waitAndReturnElement(By.xpath("//*[@id='userNumber']")).sendKeys(mobNumber);
        this.waitAndReturnElement(By.xpath("//*[@id='genterWrapper']/div[2]/div[1]")).click();

        return this;
    }

    public FormPage submitForm() {
        driver.findElement(By.xpath("//*[@id='submit']")).submit();
        return this;
    }

    public String getConfirmationMessage() {
        return this.waitAndReturnElement(By.xpath("//*[@id='example-modal-sizes-title-lg']")).getText();
    }
}