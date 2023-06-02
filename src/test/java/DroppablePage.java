import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DroppablePage extends BasePage {

    public DroppablePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://demoqa.com/droppable");
    }

    public void dragAndDrop() {
        WebElement sourceElement = this.waitAndReturnElement(By.id("draggable"));
        WebElement targetElement = this.waitAndReturnElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    public String getDroppableBoxText() {
        return this.waitAndReturnElement(By.id("droppable")).getText();
    }
}
