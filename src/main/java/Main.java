import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:7000");

        /* Login to web page */
        /* might have to wait until ajax call on load is completed */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try{
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("h3"), 0));
        } catch (TimeoutException e){
            System.out.println("error loading page");
        }

        //testing only
        //Thread.sleep(5000);

        WebElement inputUserName = driver.findElement(By.id("username-input"));
        inputUserName.sendKeys("user1");

        WebElement inputPassword = driver.findElement(By.id("password-input"));
        inputPassword.sendKeys("password", Keys.ENTER);

        //testing only
        //Thread.sleep(5000);

        /* not sure if needed */
        List<WebElement> reimbursementsBeforeCreation = new ArrayList<>();
        WebDriverWait waitforRequests = new WebDriverWait(driver, Duration.ofSeconds(5));

        try{
            waitforRequests.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                    By.className("request-container"), 0));
            reimbursementsBeforeCreation = driver.findElements(By.className("request-container"));

        }catch (TimeoutException e){
            System.out.println("error loading employee page");
        }

        /* create reimbursement request */
        WebElement inputAmount = driver.findElement(By.id("amount-input"));
        inputAmount.sendKeys("45.55");
        WebElement inputDesc = driver.findElement(By.id("desc-input"));
        inputDesc.sendKeys("Stay at hotel Marriott");
        WebElement inputType = driver.findElement(By.id("type-input"));
        inputType.sendKeys("1", Keys.ENTER);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.className("request-container"), reimbursementsBeforeCreation.size()));

        driver.quit();
    }
}
