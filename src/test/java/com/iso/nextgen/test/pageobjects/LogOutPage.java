package com.iso.nextgen.test.pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogOutPage {
	
	public void logout(WebDriver driver){
		WebElement newCustomer=driver.findElement(By.cssSelector(".menusubnav li:nth-child(15)"));
		newCustomer.click();
		
		Alert alert=driver.switchTo().alert();
		alert.accept();
	}

}
