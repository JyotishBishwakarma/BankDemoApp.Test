package com.iso.nextgen.test.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.iso.nextgen.test.ui.domain.UserLoginData;

public class LoginPage {
	
	public void login(WebDriver driver,List<UserLoginData> userlogindata){
		WebElement userId=driver.findElement(By.name("uid"));
        userId.click();
        userId.sendKeys(userlogindata.get(0).getUsername());
        
        WebElement password=driver.findElement(By.name("password"));
        password.click();
        password.sendKeys(userlogindata.get(0).getPassword());
        
        WebElement login=driver.findElement(By.name("btnLogin"));
        login.click();
        
	}
}
