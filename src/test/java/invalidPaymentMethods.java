import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class invalidPaymentMethods {
    WebDriver driver;

    @BeforeTest
    public void preparePayment() throws InterruptedException {
        driver = new EdgeDriver();
        Thread.sleep(2000);
        driver.get("https://travel.agileway.net/login");
        Thread.sleep(3000);

        //Valid Login
        driver.findElement(By.id("username")).sendKeys("agileway");
        driver.findElement(By.id("password")).sendKeys("test$W1se");
        driver.findElement(By.id("remember_me")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("commit")).click();

        /*Valid Select Flight*/
        //Trip type
        driver.findElement(By.xpath("//input[@value='oneway']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='return']")).click();
        Thread.sleep(1000);

        //From
        Select selectfrom = new Select(driver.findElement(By.name("fromPort")));
        selectfrom.selectByVisibleText("New York");

        //To
        Select selectTo = new Select(driver.findElement(By.name("toPort")));
        selectTo.selectByVisibleText("Sydney");

        //Departing date
        Select selectDay = new Select(driver.findElement(By.name("departDay")));
        selectDay.selectByVisibleText("10");
        Select selectMonthYear = new Select(driver.findElement(By.name("departMonth")));
        selectMonthYear.selectByVisibleText("January 2026");

        //Returning date
        Select selectRetDay = new Select(driver.findElement(By.name("returnDay")));
        selectRetDay.selectByVisibleText("01");
        Select selectRetMonthYear = new Select(driver.findElement(By.name("returnMonth")));
        selectRetMonthYear.selectByVisibleText("Feburary 2026");

        //Select Time and flight
        driver.findElement(By.xpath("//tr[td[text()='8:00']]//input[@type='checkbox']")).click();

        //Continue
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Thread.sleep(2000);

        //Valid passenger details
        driver.findElement(By.name("passengerFirstName")).sendKeys("John");
        driver.findElement(By.name("passengerLastName")).sendKeys("Smith");
        driver.findElement(By.xpath("//input[@value='Next']")).click();
    }

    @Test (priority = 0)
    public void invalidFields() throws InterruptedException {
        //Card type
        driver.findElement(By.xpath("//input[@value='visa']")).click();
        Thread.sleep(1000);

        //Card holder name very long and special characters
        driver.findElement(By.name("holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(By.name("holder_name")).sendKeys(Keys.chord(Keys.DELETE));
        driver.findElement(By.name("holder_name")).sendKeys("&^Y@#&(*!^#^!#^!(*#^!(*#^&!(*#^#*(^(*#&^Y@#&(*!^#^!#^!(*#^!(*#^&!(*#^#*(^(*#&^Y@#&(*!^#^!#^!(*#^!(*#^&!(*#^#*(^(*#&^Y@#&(*!^#^!#^!(*#^!(*#^&!(*#^#*(^(*#");

        //Card number longer than 16 numbers with special characters and alphabets
        driver.findElement(By.name("card_number")).sendKeys("bshdgiuyd@(*$&!*()@^$&#!(^$(#!*^#&^#!(*^#*^&@");

        //Expiry
        Select selectExpiryMonth = new Select(driver.findElement(By.name("expiry_month")));
        selectExpiryMonth.selectByVisibleText("01");
        Select selectExpiryYear = new Select(driver.findElement(By.name("expiry_year")));
        selectExpiryYear.selectByVisibleText("2028");

        //Pay now
        driver.findElement(By.xpath("//input[@value='Pay now']")).click();
        Thread.sleep(5000);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    @Test (priority = 1)
    public void invalidNoFieldsFilled() throws InterruptedException {
        //back and forward to clear form
        navigateBack();
        Thread.sleep(2000);
        navigateForward();
        Thread.sleep(2000);
        refresh();
        Thread.sleep(2000);

        //Card holder name deleted to make it empty
        driver.findElement(By.name("holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(By.name("holder_name")).sendKeys(Keys.chord(Keys.DELETE));

        //Pay now
        driver.findElement(By.xpath("//input[@value='Pay now']")).click();
        Thread.sleep(5000);
    }
}
