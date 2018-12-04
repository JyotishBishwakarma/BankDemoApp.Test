package com.iso.nextgen.test.pageobjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteCustomerPage {
	
	public void deleteCustomer(WebDriver driver,List<String> CusotmerIds){
		WebElement DeleteCustomer=driver.findElement(By.cssSelector(".menusubnav li:nth-child(4)"));
		DeleteCustomer.click();
		
		for(String CustomerID:CusotmerIds){
        	System.out.println("Deleting customer :"+CustomerID);
        	WebElement CustomerIdToBeDeleted=driver.findElement(By.name("cusid"));
    		CustomerIdToBeDeleted.sendKeys(CustomerID);
    		WebElement Submit=driver.findElement(By.name("AccSubmit"));
    		Submit.click();
    		
    		Alert alert=driver.switchTo().alert();
    		alert.accept();
    		
    		alert.accept();
        }
		
		
		
		
	}

}
