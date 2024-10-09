package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.logging.Level;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    @Test
    public void testCase01() throws InterruptedException{
        System.out.println("Start test case: testCase01");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        WebElement nameInputBox=  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@jscontroller='oCiKKc']//input[@jsname='YPqjbf']")));
        nameInputBox.sendKeys("Crio Learner");
      

        long currentTimestamp = System.currentTimeMillis();
        String answer = "I want to be the best QA Engineer!"+currentTimestamp;
        

        WebElement automationInputBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@jscontroller='oCiKKc']//textarea[@jsname='YPqjbf']")));
        automationInputBox.sendKeys(answer);
        
        WebElement expRadioBtn=driver.findElement(By.xpath("//div[@data-value='6 - 10']"));
        expRadioBtn.click();

        WebElement checkBoxJava = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-answer-value='Java']")));
        checkBoxJava.click();

        WebElement checkBoxSelenium = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-answer-value='Selenium']")));
        checkBoxSelenium.click();

        WebElement checkBoxTestNG =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-answer-value='TestNG']")));
        checkBoxTestNG.click();

       Thread.sleep(2000);
        WebElement salutationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@jscontroller='liFoG']//div[@class='ry3kXd']")));
        salutationDropdown.click();

       
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']//span[@class='vRMGwf oJeWuf']")));
            for (WebElement option : options) {
                if (option.getText().equals("Mrs")) {
                    option.click();
                    break;
                }
        }

        Thread.sleep(2000);
        LocalDate currentDate = LocalDate.now();
        LocalDate dateMinusSevenDays = currentDate.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateMinusSevenDays.format(formatter);

        WebElement dateInput = driver.findElement(By.xpath("//input[@type='date']"));
        dateInput.sendKeys(formattedDate);

        Thread.sleep(2000);
        WebElement hr= driver.findElement(By.xpath("//input[@type='text' and @aria-label='Hour']"));
        hr.sendKeys("7");

        WebElement mins = driver.findElement(By.xpath("//input[@type='text' and @aria-label='Minute']"));
        mins.sendKeys("30");


        Thread.sleep(2000);
        WebElement submitBtn = driver.findElement(By.xpath("//span[contains(text(),'Submit')]"));
        submitBtn.click();

        WebElement successMessage =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vHW8K']")));
        if(successMessage.getText().equals("Thanks for your response, Automation Wizard!"))
        System.out.println(successMessage.getText());

        System.out.println("End test case: TestCase01");
    }


    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }

    
}