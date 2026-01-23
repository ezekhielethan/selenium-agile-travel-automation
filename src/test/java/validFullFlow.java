import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class validFullFlow {
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
    public void validLogin() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("agileway");
        driver.findElement(By.id("password")).sendKeys("test$W1se");
        driver.findElement(By.id("remember_me")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("commit")).click();
    }

    @Test (priority = 2)
    public void validSelectFlight() throws InterruptedException {
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
    }

    @Test (priority = 3)
    public void validPassengerDetails(){
        driver.findElement(By.name("passengerFirstName")).sendKeys("John");
        driver.findElement(By.name("passengerLastName")).sendKeys("Smith");
        driver.findElement(By.xpath("//input[@value='Next']")).click();
    }

    @Test (priority = 4)
    public void validPaymentMethod() throws InterruptedException {
        //Card type
        driver.findElement(By.xpath("//input[@value='visa']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='master']")).click();
        Thread.sleep(1000);

        //Card holder name
        driver.findElement(By.name("holder_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(By.name("holder_name")).sendKeys(Keys.chord(Keys.DELETE));
        driver.findElement(By.name("holder_name")).sendKeys("Michael Johnson");

        //Card number
        driver.findElement(By.name("card_number")).sendKeys("0123456789123456");

        //Expiry
        Select selectExpiryMonth = new Select(driver.findElement(By.name("expiry_month")));
        selectExpiryMonth.selectByVisibleText("01");
        Select selectExpiryYear = new Select(driver.findElement(By.name("expiry_year")));
        selectExpiryYear.selectByVisibleText("2028");

        //Pay now
        driver.findElement(By.xpath("//input[@value='Pay now']")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 5)
    public void validSignOff() throws InterruptedException {
        driver.findElement(By.linkText("Sign off (agileway)")).click();
        Thread.sleep(2000);
    }

    @AfterTest
    public void quitbrowser(){
        driver.quit();
    }
}
