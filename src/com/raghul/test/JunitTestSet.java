package com.raghul.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.*;
import org.junit.*;

import com.google.common.base.Function;

public class JunitTestSet {

	@Test
	public void TestCase4() {

		WebElement isPresent = null;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.facebook.com");
		driver.manage().window().maximize();

		driver.findElement(By.id("email")).sendKeys(
				"Raghul.Jayagopal@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Face@226333");
		driver.findElement(By.id("loginbutton")).click();

		// ImplicitlyWait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		isPresent = driver.findElement(By
				.xpath("//div/a[.='Raghul Sriramaneni']"));
		Assert.assertEquals(true, isPresent.isDisplayed());
		System.out.println("Facebook Login Successful");

		driver.findElement(By.id("userNavigationLabel")).click();

		// Explicitwait
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.partialLinkText("Log Out")));

		driver.findElement(By.partialLinkText("Log Out")).click();

		isPresent = driver.findElement(By.id("email"));
		Assert.assertEquals(true, isPresent.isDisplayed());
		System.out.println("Facebook Logout Successful");

		driver.quit();

	}

	@Test
	public void TestCase5() {

		WebElement isPresent = null;

		// Set path of IEDriverServer.exe
		System.setProperty("webdriver.ie.driver",
				"src\\com\\raghul\\resources\\IEDriverServer.exe");

		// Set desired capabilities to Ignore IEDriver zoom level settings,
		// disable native events, Ignore all zones browser protected mode
		// and Initial Browser URL settings
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		caps.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.facebook.com");
	
		
		// Initialize InternetExplorerDriver Instance using new capability.
		WebDriver driver = new InternetExplorerDriver(caps);

		// Press CTRL + 0 keys of keyboard to set IEDriver Instance zoom level
		// to 100%.
		/* driver.findElement(By.tagName("html")).sendKeys(
				Keys.chord(Keys.CONTROL, "0")); */

		// FluentWait Command
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		fwait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("email"));
			}
		});
		
		driver.findElement(By.id("email")).sendKeys(
				"Raghul.Jayagopal@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Face@226333");
		driver.findElement(By.id("loginbutton")).click();

		// ImplicitlyWait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		isPresent = driver.findElement(By
				.xpath("//div/a[.='Raghul Sriramaneni']"));
		Assert.assertEquals(true, isPresent.isDisplayed());
		System.out.println("Facebook Login Successful");

		driver.findElement(By.id("userNavigationLabel")).click();

		// Explicitwait
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.partialLinkText("Log Out")));

		driver.findElement(By.partialLinkText("Log Out")).click();

		isPresent = driver.findElement(By.id("email"));
		Assert.assertEquals(true, isPresent.isDisplayed());
		System.out.println("Facebook Logout Successful");

		driver.quit();

	}
	
	@Test
	public void TestCase6() {

		WebElement isPresent = null;
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://www.facebook.com");
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		// Explicitwait
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.id("email")));

		driver.findElement(By.id("email")).sendKeys(
				"Raghul.Jayagopal@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Face@226333");
		driver.findElement(By.id("loginbutton")).click();

		// ImplicitlyWait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		isPresent = driver.findElement(By
				.xpath("//div/a[.='Raghul Sriramaneni']"));
		Assert.assertEquals(true, isPresent.isDisplayed());
		System.out.println("Facebook Login Successful");

		driver.findElement(By.id("userNavigationLabel")).click();

		// Explicitwait
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.partialLinkText("Log Out")));

		driver.findElement(By.partialLinkText("Log Out")).click();

		isPresent = driver.findElement(By.id("email"));
		Assert.assertEquals(true, isPresent.isDisplayed());
		System.out.println("Facebook Logout Successful");

		driver.quit();

	}
}