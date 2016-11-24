package com.raghul.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.raghul.util.SeleniumUtilities;

public class JunitTestCaseExercise {

	@Ignore
	@Test
	public void test1() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.navigate().to("https://login.salesforce.com/");

		WebElement userName = SeleniumUtilities.isElementPresent(driver,
				"xpath", "//input[@id='username']", 30);
		userName.sendKeys("Raghul.Jayagopal85@gmail.com");

		WebElement password = driver.findElement(By
				.xpath("//input[@id='password']"));
		password.sendKeys("SalesSample999");

		WebElement submit = driver
				.findElement(By.xpath("//input[@id='Login']"));
		submit.click();

		driver.quit();

	}

	@Ignore
	@Test
	public void test2() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.navigate().to(
				"http://www.americangolf.co.uk/golf-clubs/fairway-woods");
		List<WebElement> dValues = driver
				.findElements(By
						.xpath("//div[@class='refinement brand']//div//a//span[@class='refinement-count']"));
		int ftotal = 0;
		for (int i = 0; i < dValues.size(); i++) {
			String str1 = dValues.get(i).getText().replaceAll("\\D+", "");
			ftotal = ftotal + Integer.parseInt(str1);
		}

		String str2 = driver.findElement(
				By.xpath("//div//span[@class='results-hits-count']")).getText();
		int atotal = Integer.parseInt(str2);

		Assert.assertEquals(ftotal, atotal);
		System.out.println(ftotal + "," + atotal);

		driver.quit();

	}

	@Ignore
	@Test
	public void test3() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.navigate()
				.to("http://www.espncricinfo.com/australia-v-south-africa-2016-17/engine/match/1000851.html");

		List<WebElement> dValues = SeleniumUtilities
				.isElementsPresent(
						driver,
						"xpath",
						"//div[@id='full-scorecard']//tr//*[@class='th-innings-heading' and contains(text(),'South Africa 1st innings')]//..//..//td[@class='dismissal-info' or @class='extra-details']/following-sibling::td[1][@class='bold']",
						30);

		int ftotal = 0;
		for (int i = 0; i < dValues.size(); i++) {
			String str1 = dValues.get(i).getText();
			ftotal = ftotal + Integer.parseInt(str1);
		}

		String str2 = driver
				.findElement(
						By.xpath("//div[@id='full-scorecard']//tr//*[@class='th-innings-heading' and contains(text(),'South Africa 1st innings')]//..//..//td[@class='total-details']/following-sibling::td[1][@class='bold']/b"))
				.getText();

		int atotal = Integer.parseInt(str2);

		Assert.assertEquals(ftotal, atotal);
		System.out.println(ftotal + "," + atotal);

		driver.quit();
	}

	@Ignore
	@Test
	public void test4() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.navigate().to("http://www.jobserve.com/gb/en/Job-Search/");

		String expText = "Healthcare & Medical";

		WebElement dlist = SeleniumUtilities
				.isElementPresent(
						driver,
						"xpath",
						"//span[@class='ui-dropdownchecklist-text' and contains(text(),'27 Industries Selected')]",
						30);

		dlist.click();

		List<WebElement> dvalues = dlist.findElements(By
				.xpath("//input[starts-with(@id, 'ddcl-selInd-i')]"));

		dvalues.get(0).click();

		for (int i = 0; i < dvalues.size(); i++) {

			String actText = dlist.findElement(
					By.xpath("//label[@for='ddcl-selInd-i" + i + "']"))
					.getText();
			if (actText.equals(expText)) {
				dvalues.get(i).click();
				break;
			}
		}

		dlist.click();

		Assert.assertEquals(dlist.getText(), expText);
		System.out.println(dlist.getText() + "," + expText);

		driver.quit();
	}

	@Ignore
	@Test
	public void test5() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/datepicker/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		SeleniumUtilities.findFramesByElement(driver, "id", "datepicker");

		driver.switchTo().frame(0);
		WebElement datePicker = SeleniumUtilities.isElementPresent(driver,
				"id", "datepicker", 20);
		datePicker.click();

		SeleniumUtilities.selectDateFromPicker(driver, "dd/MM/yyyy",
				"05/10/2019");

		driver.switchTo().defaultContent();

		driver.quit();
	}

	@Ignore
	@Test
	public void test6() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/tooltip/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		SeleniumUtilities.findFramesByElement(driver, "id", "age");

		driver.switchTo().frame(0);
		WebElement age = SeleniumUtilities.isElementPresent(driver, "id",
				"age", 20);

		Actions act = new Actions(driver);
		act.moveToElement(age).build().perform();

		String tooltip = SeleniumUtilities.isElementPresent(driver,
				"className", "ui-tooltip-content", 20).getText();

		System.out.println("Tooltip -> " + tooltip);

		driver.switchTo().defaultContent();

		driver.quit();
	}

	@Ignore
	@Test
	public void test7() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/tabs/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		SeleniumUtilities.findFramesByElement(driver, "xpath",
				"//a[starts-with(@id,'ui-id-')]");

		driver.switchTo().frame(0);
		WebElement tab = SeleniumUtilities
				.isElementPresent(
						driver,
						"xpath",
						"//a[starts-with(@id,'ui-id-') and contains(text(),'Proin dolor')]",
						20);

		Actions act = new Actions(driver);
		act.moveToElement(tab).build().perform();
		act.click();

		System.out.println("TabSelected -> " + tab.getText());

		driver.switchTo().defaultContent();

		driver.quit();

	}

	@Ignore
	@Test
	public void test8() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/spinner/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		SeleniumUtilities.findFramesByElement(driver, "id", "spinner");

		driver.switchTo().frame(0);
		WebElement spinner = SeleniumUtilities.isElementPresent(driver, "id",
				"spinner", 20);

		Actions act = new Actions(driver);
		act.moveToElement(spinner).click().perform();
		spinner.sendKeys(Keys.ARROW_UP);
		System.out.println("Spinner value -> "
				+ spinner.getAttribute("aria-valuenow"));

		spinner.sendKeys(Keys.ARROW_DOWN);
		spinner.sendKeys(Keys.ARROW_DOWN);
		System.out.println("Spinner value -> "
				+ spinner.getAttribute("aria-valuenow"));
		spinner.sendKeys(Keys.ARROW_UP);
		spinner.sendKeys(Keys.PAGE_UP);
		System.out.println("Spinner value -> "
				+ spinner.getAttribute("aria-valuenow"));
		spinner.sendKeys(Keys.PAGE_DOWN);
		spinner.sendKeys(Keys.PAGE_DOWN);
		System.out.println("Spinner value -> "
				+ spinner.getAttribute("aria-valuenow"));

		driver.switchTo().defaultContent();

		driver.quit();
	}

	@Ignore
	@Test
	public void test9() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/slider/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		SeleniumUtilities.findFramesByElement(driver, "xpath",
				"//span[contains(@class,'ui-slider-handle')]");

		driver.switchTo().frame(0);
		WebElement slider = SeleniumUtilities.isElementPresent(driver, "xpath",
				"//span[contains(@class,'ui-slider-handle')]", 20);

		Actions act = new Actions(driver);
		act.dragAndDropBy(slider, 60, 0).perform();

		driver.switchTo().defaultContent();

		driver.quit();
	}

	@Ignore
	@Test
	public void test10() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/selectmenu/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		SeleniumUtilities.findFramesByElement(driver, "tagName", "select");

		driver.switchTo().frame(0);

		WebElement speedButton = SeleniumUtilities.isElementPresent(driver,
				"xpath", "//*[@id='speed-button']", 20);
		Actions act = new Actions(driver);
		act.moveToElement(speedButton).click().perform();
		List<WebElement> speedLists = SeleniumUtilities
				.isElementsPresent(
						driver,
						"xpath",
						"//*[@id='speed-menu']//li[@class='ui-menu-item']//div[starts-with(@id,'ui-id-')]",
						20);
		speedLists.get(SeleniumUtilities.getRandomNumber(speedLists.size()))
				.click();

		WebElement filesButton = SeleniumUtilities.isElementPresent(driver,
				"xpath", "//*[@id='files-button']", 20);
		act = new Actions(driver);
		act.moveToElement(filesButton).click().perform();
		List<WebElement> filesLists = SeleniumUtilities
				.isElementsPresent(
						driver,
						"xpath",
						"//*[@id='files-menu']//li[@class='ui-menu-item']//div[starts-with(@id,'ui-id-')]",
						20);
		filesLists.get(SeleniumUtilities.getRandomNumber(speedLists.size()))
				.click();

		WebElement numberButton = SeleniumUtilities.isElementPresent(driver,
				"xpath", "//*[@id='number-button']", 20);
		act = new Actions(driver);
		act.moveToElement(numberButton).click().perform();
		List<WebElement> numberLists = SeleniumUtilities
				.isElementsPresent(
						driver,
						"xpath",
						"//*[@id='number-menu']//li[@class='ui-menu-item']//div[starts-with(@id,'ui-id-')]",
						20);
		numberLists.get(SeleniumUtilities.getRandomNumber(speedLists.size()))
				.click();

		WebElement salutationButton = SeleniumUtilities.isElementPresent(
				driver, "xpath", "//*[@id='salutation-button']", 20);
		act = new Actions(driver);
		act.moveToElement(salutationButton).click().perform();
		List<WebElement> salutationLists = SeleniumUtilities
				.isElementsPresent(
						driver,
						"xpath",
						"//*[@id='salutation-menu']//li[@class='ui-menu-item']//div[starts-with(@id,'ui-id-')]",
						20);
		salutationLists.get(
				SeleniumUtilities.getRandomNumber(speedLists.size())).click();

		driver.switchTo().defaultContent();

		driver.quit();
	}

	@Ignore
	@Test
	public void test11() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/progressbar/#label");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.switchTo().frame(0);
		SeleniumUtilities.isElementPresent(driver, "xpath",
				"//*[@id='progressbar']/div[contains(text(),'Complete!')]", 20);
		driver.switchTo().defaultContent();

		driver.navigate().to("http://jqueryui.com/progressbar/#download");
		driver.navigate().refresh();
		driver.switchTo().frame(0);
		SeleniumUtilities.isElementPresent(driver, "id", "downloadButton", 50)
				.click();
		SeleniumUtilities
				.isElementPresent(
						driver,
						"xpath",
						"//*[starts-with(@id,'ui-id-') and contains(text(),'File Download')]",
						20);
		SeleniumUtilities
				.isElementPresent(
						driver,
						"xpath",
						"//*[@id='dialog']/div[@class='progress-label' and contains(text(),'Complete!')]",
						20);
		SeleniumUtilities
				.isElementPresent(
						driver,
						"xpath",
						"//div[@id='dialog']//following-sibling::div/div/button[@type='button' and contains(text(),'Close')]",
						30).click();
		driver.switchTo().defaultContent();

		driver.quit();
	}

	@Ignore
	@Test
	public void test12() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions", "--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"src\\com\\raghul\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/menu/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.switchTo().frame(0);
		WebElement menu = SeleniumUtilities
				.isElementPresent(
						driver,
						"xpath",
						"//div[starts-with(@id,'ui-id-') and contains(text(),'Music')]",
						20);

		Actions mouse = new Actions(driver);
		mouse.moveToElement(menu).perform();

		WebElement submenu = SeleniumUtilities
				.isElementPresent(
						driver,
						"xpath",
						"//ul[@aria-expanded='true']//*[starts-with(@id,'ui-id-') and contains(text(),'Jazz')]",
						20);
		submenu.isDisplayed();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(submenu));
		mouse.moveToElement(submenu).perform();

		WebElement childsubmenu = SeleniumUtilities
				.isElementPresent(
						driver,
						"xpath",
						"//ul[contains(@class,'ui-front') and @aria-expanded='true']//*[starts-with(@id,'ui-id-') and contains(text(),'Big Band')]",
						20);
		childsubmenu.isDisplayed();
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(childsubmenu));
		mouse.moveToElement(childsubmenu).perform();

		System.out.println(childsubmenu.getText());

		driver.switchTo().defaultContent();
		driver.quit();
	}

}
