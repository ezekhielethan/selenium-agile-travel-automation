import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class invalidLogin {
    WebDriver driver;

    @BeforeTest
    public void prepare() throws InterruptedException {
        driver = new EdgeDriver();
        Thread.sleep(2000);
    }

    @Test (priority = 0)
    public void openAgileTravel() throws InterruptedException {
        driver.get("https://travel.agileway.net/login");
        Thread.sleep(3000);
    }

    @Test (priority = 1)
    public void invalidLogin() throws InterruptedException {
        //Invalid username
        driver.findElement(By.id("username")).sendKeys("wayagile");
        driver.findElement(By.id("password")).sendKeys("test$W1se");
        Thread.sleep(2000);
        driver.findElement(By.name("commit")).click();

        //Invalid password
        driver.findElement(By.id("username")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(By.id("username")).sendKeys(Keys.chord(Keys.DELETE));
        driver.findElement(By.id("username")).sendKeys("agileway");
        driver.findElement(By.id("password")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(By.id("password")).sendKeys(Keys.chord(Keys.DELETE));
        Thread.sleep(2000);
        driver.findElement(By.name("commit")).click();
    }
}
