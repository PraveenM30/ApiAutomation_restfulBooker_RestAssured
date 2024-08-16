package WebAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static Rest_Assured.TestBase.driver;
import static WebAutomation.excel_Utilities.getValue;

public class SignIn extends excel_Utilities {
    public static void main(String[] args) throws InterruptedException, IOException {
        driver.get("https://app.fireflink.com/");
        driver.manage().window().maximize();

        //Synchronization
        //implicit wait--it applies for entire web page.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Explicit wait it applies for particular element
        WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitt.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name=\"password\"]"))));

        Thread.sleep(20000);
        String userName=getValue("C:\\Users\\user\\intelij Workspace\\RestAssured\\Data Driven.xlsx","Sheet1",1,0);
        driver.findElement(By.xpath("//input[@id=\"emailId\"]")).sendKeys(userName);

        String password=getValue("C:\\Users\\user\\intelij Workspace\\RestAssured\\Data Driven.xlsx","Sheet1",1,1);

        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//div/.//button[@type=\"submit\"]")).click();
        driver.findElement(By.xpath("//img[@alt='fireFlinkPlatform']")).click();
        Thread.sleep(15000);
        Set<String> windowHandles= driver.getWindowHandles();
        for (String windowHandle:windowHandles){
            driver.switchTo().window(windowHandle);
            if (driver.getCurrentUrl().contains("https://app.fireflink.com/projectmenu")){
                break;
            }
        }
        System.out.println(driver.getCurrentUrl());
        waitt.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//label[text()='Projects List']"))));
        try {
            driver.findElement(By.xpath("//label[text()='Projects List']")).isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println("Elementv is not displayed !!");
        }

  // driver.quit();
    }
}
