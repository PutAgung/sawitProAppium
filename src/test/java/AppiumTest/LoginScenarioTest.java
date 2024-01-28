package AppiumTest;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class LoginScenarioTest {

    private AppiumDriver driver;

    ExtentSparkReporter htmlReporter;
    ExtentReports extent;
    ExtentTest testLoginLogout,emptyValue,testWrongEmail;
    @BeforeSuite


    public void setUp() throws MalformedURLException {
        String reportPath = Paths.get("target", "extent-report.html").toString();
        htmlReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setIgnoreHiddenApiPolicyError(true)
                .setFullReset(true)
                .setApp("C:\\Users\\ASUS R565\\IdeaProjects\\APKFiles\\Chat21.apk")
                .setPlatformName("Android")
                .setDeviceName("bffa85a2")
                .setAutomationName("uiautomator2")
                .setAppPackage("chat21.android.demo")
                .setAppActivity("chat21.android.demo.SplashActivity")
                .setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), options);
        System.out.println("--- Successfully Go inside the apps ---");
        driver.findElement(By.id("android:id/button1")).click();

    }

    @Test(priority = 1)
    public void testLoginLogout() {
        //---------------Report-----------//
        testLoginLogout = extent.createTest("Testing Login and Logout"
                , "Validate whether the user has successfully logged into the application with the correct account");
        testLoginLogout.log(Status.INFO, "Starting Positive test case");


        try {
        testLoginLogout.log(Status.INFO, "Entered email and password");
        driver.findElement(By.id("chat21.android.demo:id/email")).sendKeys("putrakatalon@gmail.com");
        driver.findElement(By.id("chat21.android.demo:id/password")).sendKeys("Kucing123!");
        testLoginLogout.log(Status.INFO, "Clicked on Login button");
        driver.findElement(By.id("chat21.android.demo:id/login")).click();
        testLoginLogout.log(Status.INFO, "User should succeed to login and redirect to homepage");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/toolbar")));
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/toolbar")).isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='PROFILE']")));
        driver.findElement(By.xpath("//android.widget.TextView[@text='PROFILE']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/logout")));
        driver.findElement(By.id("chat21.android.demo:id/logout")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/login")));
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/login")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/signup")).isDisplayed());
        testLoginLogout.log(Status.INFO, "User succeed to logout");

        } catch (AssertionError e) {
            testLoginLogout.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            testLoginLogout.log(Status.FAIL, "Test failed: Exception occurred - " + e.getMessage());
        }
        System.out.println("--- Test 1 Done ---");
    }

    @Test(priority = 2)
    public void emptyValue(){
        //---------------Report-----------//
        emptyValue = extent.createTest("Testing Login page with empty values"
                , "Validate the Login page with an empty Email and Password");
        emptyValue.log(Status.INFO, "Starting Negative test case");

        try {
        emptyValue.log(Status.INFO, "Fill Login page with null");
        driver.findElement(By.id("chat21.android.demo:id/email")).sendKeys("");
        driver.findElement(By.id("chat21.android.demo:id/password")).sendKeys("");
        driver.findElement(By.id("chat21.android.demo:id/login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/toolbar")));
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/toolbar")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/signup")).isDisplayed());
        emptyValue.log(Status.INFO, "Failed to login");

    } catch (AssertionError e) {
        emptyValue.log(Status.FAIL, "Test failed: " + e.getMessage());
        throw e;
    } catch (Exception e) {
        emptyValue.log(Status.FAIL, "Test failed: Exception occurred - " + e.getMessage());
    }
        System.out.println("--- Test 2 Done ---");
    }

    @Test(dependsOnMethods ="emptyValue")
    public void testWrongEmail(){
        //---------------Report-----------//
        testWrongEmail = extent.createTest("Test with Wrong Email", "Validate the Login page with incorrect Email format");
        testWrongEmail.log(Status.INFO, "Starting Negative test case");

        try {
        testWrongEmail.log(Status.INFO, "Fill the email with wrong format");
        driver.findElement(By.id("chat21.android.demo:id/email")).sendKeys("email");
        driver.findElement(By.id("chat21.android.demo:id/password")).sendKeys("Kucing123!");
        driver.findElement(By.id("chat21.android.demo:id/login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/toolbar")));
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/toolbar")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/signup")).isDisplayed());
        testWrongEmail.log(Status.INFO, "Failed to login");

    } catch (AssertionError e) {
            testWrongEmail.log(Status.FAIL, "Test failed: " + e.getMessage());
        throw e;
    } catch (Exception e) {
            testWrongEmail.log(Status.FAIL, "Test failed: Exception occurred - " + e.getMessage());
    }
        System.out.println("--- Test 3 Done ---");
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}