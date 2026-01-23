import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class invalidSelectFlight {
    WebDriver driver;

    @BeforeTest
    public void prepareSelectFlight() throws InterruptedException {
        driver = new EdgeDriver();
        Thread.sleep(2000);
    }

    @Test(priority = 0)
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
    public void invalidSameFlightLocations() throws InterruptedException {
        driver.findElement(By.xpath("//input[@value='return']")).click();
        Thread.sleep(1000);

        /* Same flight origin and destination */
        //From
        Select selectfrom = new Select(driver.findElement(By.name("fromPort")));
        selectfrom.selectByVisibleText("New York");

        //To
        Select selectTo = new Select(driver.findElement(By.name("toPort")));
        selectTo.selectByVisibleText("New York");

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

    public void navigateBack() {
        driver.navigate().back();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    @Test (priority = 3)
    public void invalidSameDates() throws InterruptedException {
       //back and refresh to clear form
        navigateBack();
        Thread.sleep(2000);
        refresh();
        Thread.sleep(2000);

        /* Valid origin and destination */
        //From
        Select selectfrom = new Select(driver.findElement(By.name("fromPort")));
        selectfrom.selectByVisibleText("New York");

        //To
        Select selectTo = new Select(driver.findElement(By.name("toPort")));
        selectTo.selectByVisibleText("Sydney");

        /* Same dates */
        //Departing date
        Select selectDay = new Select(driver.findElement(By.name("departDay")));
        selectDay.selectByVisibleText("10");
        Select selectMonthYear = new Select(driver.findElement(By.name("departMonth")));
        selectMonthYear.selectByVisibleText("Feburary 2026");

        //Returning date
        Select selectRetDay = new Select(driver.findElement(By.name("returnDay")));
        selectRetDay.selectByVisibleText("10");
        Select selectRetMonthYear = new Select(driver.findElement(By.name("returnMonth")));
        selectRetMonthYear.selectByVisibleText("Feburary 2026");
        Thread.sleep(2000);

        //Select Time and flight
        driver.findElement(By.xpath("//tr[td[text()='8:00']]//input[@type='checkbox']")).click();

        //Continue
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 4)
    public void invalidPastDates() throws InterruptedException {
        //back and refresh to clear form
        navigateBack();
        Thread.sleep(2000);
        refresh();
        Thread.sleep(2000);

        /* Valid origin and destination */
        //From
        Select selectfrom = new Select(driver.findElement(By.name("fromPort")));
        selectfrom.selectByVisibleText("New York");

        //To
        Select selectTo = new Select(driver.findElement(By.name("toPort")));
        selectTo.selectByVisibleText("Sydney");

        /* Older departing date than returning */
        //Departing date
        Select selectDay = new Select(driver.findElement(By.name("departDay")));
        selectDay.selectByVisibleText("10");
        Select selectMonthYear = new Select(driver.findElement(By.name("departMonth")));
        selectMonthYear.selectByVisibleText("March 2026");

        //Returning date
        Select selectRetDay = new Select(driver.findElement(By.name("returnDay")));
        selectRetDay.selectByVisibleText("10");
        Select selectRetMonthYear = new Select(driver.findElement(By.name("returnMonth")));
        selectRetMonthYear.selectByVisibleText("January 2026");

        //Select Time and flight
        driver.findElement(By.xpath("//tr[td[text()='8:00']]//input[@type='checkbox']")).click();

        //Continue
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 5)
    public void invalidMultipleFlightTimes() throws InterruptedException {
        //back and refresh to clear form
        navigateBack();
        Thread.sleep(2000);
        refresh();
        Thread.sleep(2000);

        //Trip type
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
        driver.findElement(By.xpath("//tr[td[text()='8:30']]//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//tr[td[text()='9:00']]//input[@type='checkbox']")).click();

        //Continue
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Thread.sleep(2000);
    }
}
