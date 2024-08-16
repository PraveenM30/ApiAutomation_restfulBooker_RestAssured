import Rest_Assured.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class Test1 extends TestBase {
    @BeforeTest
    public void openTheBrowser() throws IOException {
        testConfigKeyValue = getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
        String BrowserName = fetchDatFromMap(testConfigKeyValue, "Browser_Name");

        String browserURL = fetchDatFromMap(testConfigKeyValue, "Web_URL");
       driver= initializeDriver(BrowserName);
       driver.get(browserURL);
       WaitImplicitly();
    }
    @Test
    public void LogIn()
    {
        driver.findElement(By.xpath("//input[@id=\"emailId\"]"));

    }
    @AfterTest
    public void CloseBrowser(){
        quitBrowser();
    }
}
