package com.iso.nextgen.test.pageobjects;


import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import com.iso.nextgen.test.ui.domain.NewCustomerData;

//import com.thoughtworks.selenium.Selenium;

public class NewCustomerPage {
	
	public String createNewCustomer(WebDriver driver,List<NewCustomerData> newcustomerdata, int j){
		WebElement newCustomer=driver.findElement(By.cssSelector(".menusubnav li:nth-child(2)"));
		newCustomer.click();
		
		JavascriptExecutor selenium=(JavascriptExecutor)driver;
		
		WebElement customerName=driver.findElement(By.name("name"));
		//customerName.click();
		customerName.sendKeys(newcustomerdata.get(j).getName());
		
		//selenium.executeScript("arguments[0].value='Akash';", customerName);
		WebElement GenderMale=driver.findElement(By.cssSelector("input[value='m']"));
		WebElement GenderFemale=driver.findElement(By.cssSelector("input[value='f']"));
		
		String gen=newcustomerdata.get(j).getGender();
		
		if (gen.equals("Male")){
			GenderMale.click();
		}else{
			GenderFemale.click();
		}
		
		
		WebElement DOB=driver.findElement(By.name("dob"));
		String db=newcustomerdata.get(j).getDob();
		
		//DOB.click();
		//String abc=DOB.getAttribute("value");
		selenium.executeScript("arguments[0].value='1992-03-01';", DOB);
		DOB.sendKeys(Keys.TAB);
		
		WebElement Address=driver.findElement(By.name("addr"));
		Address.sendKeys(newcustomerdata.get(j).getAddress());
		
		WebElement city=driver.findElement(By.name("city"));
		city.sendKeys(newcustomerdata.get(j).getCity());
		
		WebElement state=driver.findElement(By.name("state"));
		state.sendKeys(newcustomerdata.get(j).getState());
		
		WebElement pin=driver.findElement(By.name("pinno"));
		pin.sendKeys(newcustomerdata.get(j).getPin());
		
		WebElement mobileno=driver.findElement(By.name("telephoneno"));
		mobileno.sendKeys(newcustomerdata.get(j).getMobno());
		
		WebElement email=driver.findElement(By.name("emailid"));
		email.sendKeys(newcustomerdata.get(j).getEmail());
		
		WebElement CustomerPassword=driver.findElement(By.name("password"));
		CustomerPassword.sendKeys(newcustomerdata.get(j).getPassword());
		
		WebElement Submit=driver.findElement(By.name("sub"));
		Submit.click();
		
		//int index=3;
		WebElement baseTable=driver.findElement(By.id("customer"));
		List<WebElement> tableRows=baseTable.findElements(By.tagName("td"));
		
		ListIterator<WebElement> li= null;
		
		li=tableRows.listIterator();
		
		
		
		String[] arr = new String[100];
		int i=0;
		while(li.hasNext()){
			arr[i]=li.next().getText();
			i++;
		}
		
		String CustomerId=arr[4];
		
		
		
		
		return CustomerId;
		
		
	
		
	}
	
	

}
