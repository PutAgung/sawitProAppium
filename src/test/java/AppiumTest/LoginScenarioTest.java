package AppiumTest;


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
import java.time.Duration;

public class LoginScenarioTest {

    private AppiumDriver driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
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
        driver.findElement(By.id("chat21.android.demo:id/email")).sendKeys("putrakatalon@gmail.com");
        driver.findElement(By.id("chat21.android.demo:id/password")).sendKeys("Kucing123!");
        driver.findElement(By.id("chat21.android.demo:id/login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/toolbar")));
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/toolbar")).isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='PROFILE']")));
        driver.findElement(By.xpath("//android.widget.TextView[@text='PROFILE']")).click();
        driver.findElement(By.id("chat21.android.demo:id/logout")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/login")));
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/login")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/signup")).isDisplayed());
        System.out.println("--- Test 1 Done ---");
    }

    @Test(priority = 2)
    public void emptyValue(){
        driver.findElement(By.id("chat21.android.demo:id/email")).sendKeys("");
        driver.findElement(By.id("chat21.android.demo:id/password")).sendKeys("");
        driver.findElement(By.id("chat21.android.demo:id/login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/toolbar")));
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/toolbar")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/signup")).isDisplayed());
        System.out.println("--- Test 2 Done ---");
    }

    @Test(dependsOnMethods ="emptyValue")
    public void testWrongEmail(){
        driver.findElement(By.id("chat21.android.demo:id/email")).sendKeys("email");
        driver.findElement(By.id("chat21.android.demo:id/password")).sendKeys("Kucing123!");
        driver.findElement(By.id("chat21.android.demo:id/login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chat21.android.demo:id/toolbar")));
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/toolbar")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("chat21.android.demo:id/signup")).isDisplayed());
        System.out.println("--- Test 3 Done ---");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("--- All Done ---");
        driver.quit();
    }
}