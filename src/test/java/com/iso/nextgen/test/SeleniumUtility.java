package com.iso.nextgen.test;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import com.iso.nextgen.test.ui.common.driver.BrowserDriver;

public class SeleniumUtility {
	private static final Logger LOG = Logger.getLogger(SeleniumUtility.class);
	private static final WebDriver driver = BrowserDriver.getDriver();
		
	public void wait (WebDriver driver,By element) throws InterruptedException // Wait function to wait for element 
	{ 
		
		for (int second = 0; ; second++) 
		{ 
			if (second >= 60) Assert.fail("timeout"); 
			try 
			{ if (isElementPresent(element)) break; 
			} catch (Exception e) { } 
			Thread.sleep(1000); 
			}
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickBtn(By locator)
	{
	WebElement element =driver.findElement(locator);
		  
		if (driver instanceof InternetExplorerDriver)
		         {
		             element.sendKeys(Keys.ENTER);
		         }
		         else
		         {
		             element.click();
		           
		         } 
	}
	
	public boolean waitForElement(By by)
	{

		LOG.debug("waitForElement" );

		Wait<WebDriver> wait = new WebDriverWait(driver, 15);

		WebElement element = wait.until(visibilityOfElementLocated(by));

		if(element != null){

		return element.isDisplayed();

		}

		else

		{

		return false;

		}

		}

		 

		public ExpectedCondition<WebElement> visibilityOfElementLocated(final By by){

		return new ExpectedCondition<WebElement>() {

		public WebElement apply(WebDriver d) {

		WebElement element = d.findElement(by);

		if(element.isDisplayed()){

		return element;

		}

		return null;

		}

		};

		}
		
		public void takeAScreenShotOfTheApp() throws AWTException, IOException 
		 {		
			    Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
			    Rectangle screenBounds = new Rectangle(0, 0, screenDim.width, screenDim.height);

			    Robot robot = new Robot();
			    BufferedImage image =  robot.createScreenCapture(screenBounds);

			    File screenshotFile = new File("image" + System.currentTimeMillis() + ".png");
			    ImageIO.write(image, "png", screenshotFile);
			}

		
		
		/*
		  * Wait until Alert Present
		  */
				

				public Alert waitForAlert( int seconds) {
					FluentWait<WebDriver> wait = new WebDriverWait(driver, seconds).ignoring(NullPointerException.class);
					Alert A = wait.until(waitforAlertPresent(driver));
					
					if (A!=null){
						LOG.debug("Alert is present");
						return A;
					}
					else
					{
						LOG.debug("Alert is not present");
						return null;
					}
					
					}
					
					
					public ExpectedCondition<Alert> waitforAlertPresent(final WebDriver driver){
						
						LOG.debug("Waiting... forAlertPresent");
						return new ExpectedCondition<Alert>() {

							public Alert apply(WebDriver d) {

								Alert alert = driver.switchTo().alert();
								alert.getText();
								return alert;

							}
						};
					}
}
