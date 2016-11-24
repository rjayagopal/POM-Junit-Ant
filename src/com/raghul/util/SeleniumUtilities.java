package com.raghul.util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtilities {

	public static WebElement isElementPresent(WebDriver driver, String locator,
			String value, int time) {

		WebElement element = null;

		loop: for (int i = 0; i < time; i++) {

			try {
				switch (locator) {
				case "id":
					element = driver.findElement(By.id(value));
					break loop;
				case "name":
					element = driver.findElement(By.name(value));
					break loop;
				case "xpath":
					element = driver.findElement(By.xpath(value));
					break loop;
				case "cssSelector":
					element = driver.findElement(By.cssSelector(value));
					break loop;
				case "linkText":
					element = driver.findElement(By.linkText(value));
					break loop;
				case "partialLinkText":
					element = driver.findElement(By.partialLinkText(value));
					break loop;
				case "className":
					element = driver.findElement(By.className(value));
					break loop;
				case "tagName":
					element = driver.findElement(By.tagName(value));
					break loop;
				}

			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					StackTracer.printStackTrace(ex);
				}
			}
		}
		return element;
	}

	public static void findFramesByElement(WebDriver driver, String locatorID,
			String locator) {

		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		for (int i = 0; i < frames.size(); i++) {
			driver.switchTo().frame(i);
			int size = 0;
			size = isElementsPresent(driver, locatorID, locator, 10).size();
			
			System.out.println("Frame No: " + i + " , Matching Elements: "
					+ size);

			driver.switchTo().defaultContent();
		}
	}

	public static int getRandomNumber(int range) {
		Random random = new Random();
		return random.nextInt(range);
	}

	public static List<WebElement> isElementsPresent(WebDriver driver,
			String locator, String value, int time) {

		List<WebElement> elements = null;

		loop: for (int i = 0; i < time; i++) {

			try {
				switch (locator) {
				case "id":
					elements = driver.findElements(By.id(value));
					break loop;
				case "name":
					elements = driver.findElements(By.name(value));
					break loop;
				case "xpath":
					elements = driver.findElements(By.xpath(value));
					break loop;
				case "cssSelector":
					elements = driver.findElements(By.cssSelector(value));
					break loop;
				case "linkText":
					elements = driver.findElements(By.linkText(value));
					break loop;
				case "partialLinkText":
					elements = driver.findElements(By.partialLinkText(value));
					break loop;
				case "className":
					elements = driver.findElements(By.className(value));
					break loop;
				case "tagName":
					elements = driver.findElements(By.tagName(value));
					break loop;
				}

			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					StackTracer.printStackTrace(ex);
				}
			}
		}
		return elements;
	}

	public static void uploadFile(String filePath) {
		try {
			StringSelection stringSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard()
					.setContents(stringSelection, null);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			StackTracer.printStackTrace(e);

		}
	}

	public static String base64Encode(String originalString) {

		byte[] encoded = Base64.encodeBase64(originalString.getBytes());
		String rtnEncodedString = new String(encoded);

		return rtnEncodedString;

	}

	public static String base64Decode(String encodedString) {

		byte[] decoded = Base64.decodeBase64(encodedString.getBytes());
		String rtnDecodedString = new String(decoded);

		return rtnDecodedString;

	}

	public static void cssMenuClick(WebDriver driver, String xpath) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(xpath)));

		WebElement menu = driver.findElement(By.xpath(xpath));

		Actions act = new Actions(driver);
		act.click(menu).build().perform();

	}

	public static void jsRadioClick(WebDriver driver, String jsMethod) {

		((JavascriptExecutor) driver).executeScript(jsMethod);
		// ((JavascriptExecutor)
		// driver).executeScript("document.getElementByID('radtripType').click()");

	}

	public static void selectDateFromPicker(WebDriver driver,
			String dateFormat, String date) {
		try {
			SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
			Date dateToBeSelected = dFormat.parse(date);
			Date currentDate = new Date();

			String monthDisplayed = driver
					.findElement(
							By.xpath("//*[@id='ui-datepicker-div']//span[@class='ui-datepicker-month']"))
					.getText();

			String yearDisplayed = driver
					.findElement(
							By.xpath("//*[@id='ui-datepicker-div']//span[@class='ui-datepicker-year']"))
					.getText();

			String monthYearDisplayed = monthDisplayed + " " + yearDisplayed;

			String month = new SimpleDateFormat("MMMM")
					.format(dateToBeSelected);
			String year = new SimpleDateFormat("yyyy").format(dateToBeSelected);
			String day = new SimpleDateFormat("d").format(dateToBeSelected);
			String monthYearToBeSelected = month + " " + year;

			while (true) {
				if (monthYearToBeSelected.equals(monthYearDisplayed)) {
					// Select the Date
					driver.findElement(
							By.xpath("//*[@id='ui-datepicker-div']//a[text()='"
									+ day + "']")).click();
					System.out.println("Found and Selected");
					break;

				} else { // Navigate to correct month and year

					if (dateToBeSelected.after(currentDate)) {
						driver.findElement(
								By.xpath("//*[@id='ui-datepicker-div']//span[text()='Next']"))
								.click();
					} else {
						driver.findElement(
								By.xpath("//*[@id='ui-datepicker-div']//span[text()='Prev']"))
								.click();
					}

				}

				monthDisplayed = driver
						.findElement(
								By.xpath("//*[@id='ui-datepicker-div']//span[@class='ui-datepicker-month']"))
						.getText();

				yearDisplayed = driver
						.findElement(
								By.xpath("//*[@id='ui-datepicker-div']//span[@class='ui-datepicker-year']"))
						.getText();

				monthYearDisplayed = monthDisplayed + " " + yearDisplayed;

			}

		} catch (Exception ex) {
			StackTracer.printStackTrace(ex);
		}

	}

}
