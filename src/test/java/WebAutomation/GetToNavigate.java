package WebAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class GetToNavigate {

    public static void navigate() {
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.google.co.in/");
        driver.navigate().to("https://codebeautify.org/jsonviewer");
        driver.navigate().back();
        driver.navigate().refresh();
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
        Assert.assertNotEquals(driver.getCurrentUrl(),"https://www.google.co.in/");
        driver.quit();
    }
}
