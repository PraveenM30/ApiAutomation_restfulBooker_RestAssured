package Rest_Assured;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;

public class TestBase {

    // global Variables
    public static WebDriver driver;
    public static String screenshootFilePath = "screenshoot/";
    public static String screenshootOfProductListScreen = "Screenshot Of Product List Screen.png";
    public static String jsonFilePath = "books.json";
    public static String apkFilePath = "com-amazon-mshop-android-shopping-1241274011-67963971-a700fa57e27ad3614943bdf40327d5b9.apk";
    public static String excelPath = "Test Data for Automation.xlsx";
    public static String testConfigSheetName = "Test Config";
    public static String TestDataSheetName = "Test Data for the Test Scripts";
    public static String uniqueDataTestConfig = "Test";
    public static String uniqueDataTS001 = "TS001";
    public static String uniqueDataTS002 = "TS002";
    public static LinkedHashMap<String, String> testConfigKeyValue = new LinkedHashMap<String, String>();
    public static LinkedHashMap<String, String> TS001KeyValue = new LinkedHashMap<String, String>();
    public static LinkedHashMap<String, String> TS002KeyValue = new LinkedHashMap<String, String>();
    public static boolean isTableVertical = true;
    public static boolean isTableHorizontal = false;

//**************************************************************************************************************************************

    // User Defined Method To Invoking Browsers
    public WebDriver initializeDriver(String browserName) throws IOException {

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }

        else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }

        else {
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
        }

        return driver;
    }


    //**************************************************************************************************************************************
    // User Defined Method For Taking Screnshot On Failed Steps
    public void getScreenShot(String result) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(screenshootFilePath + result + " screenshot.png"));
    }

    //**************************************************************************************************************************************
    // User Defined Method Get Data FromExcel
    public static LinkedHashMap<String, String> getDataFromExcel(String excelPath, String sheetName, String uniqueData,
                                                                 boolean isTableVertical) throws IOException {
        FileInputStream fisExcel = new FileInputStream(excelPath);
        Workbook workbook = WorkbookFactory.create(fisExcel);
        DataFormatter df = new DataFormatter();
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        Sheet sheet = workbook.getSheet(sheetName);
        int lastRowNumber = sheet.getLastRowNum(); // return index ==> index

        String value = "";
        String actualTestCaseName = "";
        String actualKey = "";

        // For Horizontal Data Featching In Key Value Pair
        if (isTableVertical == false) {
            for (int i = 0; i <= lastRowNumber; i++) {
                String actualTestcase = df.formatCellValue(sheet.getRow(i).getCell(0));
                if (actualTestcase.equals(uniqueData)) {
                    short lastcellNumber = sheet.getRow(i).getLastCellNum(); // return count/size ==> count-1
                    for (int j = 1; j < lastcellNumber - 1; j++) {
                        actualKey = df.formatCellValue(sheet.getRow(i - 1).getCell(j));
                        value = df.formatCellValue(sheet.getRow(i).getCell(j));
                        map.put(actualKey, value);
                    }
                    break;
                }
            }
        }

        // For Vertical Data Featching In Key Value Pair
        else if (isTableVertical == true) {
            for (int i = 1; i <= sheet.getRow(i).getLastCellNum(); i++) {

                try {
                    actualTestCaseName = df.formatCellValue(sheet.getRow(0).getCell(i));

                } catch (Exception e) {
                }
                if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
                    for (int j = 0; j <= sheet.getLastRowNum(); j++) {

                        try {
                            actualKey = df.formatCellValue(sheet.getRow(j).getCell(i - 1));
                            try {
                                value = df.formatCellValue(sheet.getRow(j).getCell(i));
                            } catch (Exception e) {
                            }

                            if ((actualKey.isEmpty() && value.isEmpty()) || actualKey.isEmpty()) {
                            } else {
                                map.put(actualKey, value);
                            }
                        } catch (Exception e) {
                        }
                    }
                    break;
                }
            }
        }
        workbook.close();
        fisExcel.close();
        return map;
    }

    //**************************************************************************************************************************************
    // User Defined Method Get Data Map
    public static String fetchDatFromMap(LinkedHashMap<String, String> testConfigKeyValue, String value) {
        return value = testConfigKeyValue.get(value);
    }

    //**************************************************************************************************************************************
    // User Defined Method write Data FromExcel
    public static void WriteDataInToExcel(String excelPath, String sheetName, String uniqueData, String header,
                                          String data, boolean isTableVertical) throws EncryptedDocumentException, IOException {
        FileInputStream excelFile = new FileInputStream(new File(excelPath));
        Workbook workbook = WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheet(sheetName);
        DataFormatter df = new DataFormatter();
        boolean flag = false;
        String actualTestCaseName = "";
        String actualKey = "";

        // For Horizontal Data Write Into Excel
        if (isTableVertical == false) {
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                try {
                    actualTestCaseName = df.formatCellValue(sheet.getRow(i).getCell(0));
                } catch (Exception e) {
                }
                if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
                    for (int j = 1; j <= sheet.getRow(i).getLastCellNum(); j++) {
                        try {
                            actualKey = df.formatCellValue(sheet.getRow(i - 1).getCell(j));
                        } catch (Exception e) {
                        }
                        if (actualKey.equalsIgnoreCase(header)) {
                            try {
                                sheet.getRow(i).createCell(j).setCellValue(data);
                            } catch (Exception e) {
                            }
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag == true) {
                    break;
                }
            }
        }
        // For Vertical Data Write Into Excel
        else if (isTableVertical == true) {
            for (int i = 1; i <= sheet.getRow(i).getLastCellNum(); i++) {

                try {
                    actualTestCaseName = df.formatCellValue(sheet.getRow(0).getCell(i));

                } catch (Exception e) {
                }
                if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
                    for (int j = 0; j <= sheet.getLastRowNum(); j++) {

                        try {
                            actualKey = df.formatCellValue(sheet.getRow(j).getCell(i - 1));
                        } catch (Exception e) {
                        }
                        if (actualKey.equalsIgnoreCase(header)) {
                            try {
                                sheet.getRow(j).createCell(i).setCellValue(data);
                            } catch (Exception e) {
                            }
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag == true) {
                    break;
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(excelPath);
        workbook.write(fos);
        fos.close();
        workbook.close();
        excelFile.close();
    }
    //**************************************************************************************************************************************
    // User Defined Method To Explicit Wait
    public void WaitUntilvisibilityOfElement(WebElement Element) throws IOException {
        testConfigKeyValue = getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
        String explicitWait = fetchDatFromMap(testConfigKeyValue, "Explicit_Wait_Time");
        Integer explicitWaitTime = Integer.parseInt(explicitWait);
        // logic gos here
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
        wait.until(ExpectedConditions.visibilityOf(Element));
    }

    //**************************************************************************************************************************************
    // User Defined Method To Implicitly Wait
    public static void WaitImplicitly() throws IOException {
        testConfigKeyValue = getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
        String implicitlyWait = fetchDatFromMap(testConfigKeyValue, "Implicit_Wait _Time");
        Integer implicitlyWaitTime = Integer.parseInt(implicitlyWait);
        // logic gos here
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
    }

    public void quitBrowser(){
        driver.quit();
    }
}
