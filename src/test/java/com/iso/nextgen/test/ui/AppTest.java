package com.iso.nextgen.test.ui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iso.nextgen.test.ui.common.driver.BrowserDriver;
import com.iso.nextgen.test.ui.domain.NewCustomerData;
import com.iso.nextgen.test.ui.dao.TestBedDao;
import com.iso.nextgen.test.ui.domain.*;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iso.nextgen.test.pageobjects.DeleteCustomerPage;
import com.iso.nextgen.test.pageobjects.LogOutPage;
import com.iso.nextgen.test.pageobjects.LoginPage;
import com.iso.nextgen.test.pageobjects.NewCustomerPage;
import com.iso.nextgen.test.ui.domain.NewCustomerData;
import com.iso.nextgen.test.ui.mapper.NewCustomerDataMapper;
import com.iso.nextgen.test.ui.service.TestBedService;
import com.iso.nextgen.test.ui.service.impl.TestBedServiceImpl;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:definition/main/application-context.xml" })
@TestExecutionListeners
public class AppTest extends BaseTestCase
{
    /**
     * Rigorous Test :-)
     * @throws MalformedURLException 
     * @throws InterruptedException 
     */
	@Autowired
	private TestBedService service;
	
	/*private WebDriver driver = BrowserDriver.getDriver();*/
	public void setTestBedService(TestBedService service) {
		this.service = service;
	}
	
    @Test    
    public void testDemoBank() throws Exception
    {
    	//System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver.exe");
    	WebDriver driver = BrowserDriver.getDriver();
    	
    	
        System.out.println("JyotishTest started :");                   
        List<NewCustomerData> newcustomerdata=service.findTestsByName();        
       //System.out.println(newcustomerdata);
        
        List<UserLoginData> userlogindata=service.findTestsByUserName();        
       
        System.out.println("username :"+userlogindata.get(0).getUsername());
        System.out.println("username :"+userlogindata.get(0).getPassword());
        
        //DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        //RemoteWebDriver driver = new RemoteWebDriver(new URL("http://IPVAPW7D-A606.iso.com:4444/wd/hub"), desiredCapabilities);
        
        BrowserDriver.getDriver().manage().window().maximize();
        
        driver.get("http://www.demo.guru99.com/V4/");
        List<String> CusotmerIds=new ArrayList<String>();
        
        LoginPage loginPage=new LoginPage();
        loginPage.login(driver,userlogindata);
        
        try{
        for (int j = getMin(); j < getMax(newcustomerdata.size()); j++) {
        	
        NewCustomerPage newCustomerPage=new NewCustomerPage();
        String CustomerId=newCustomerPage.createNewCustomer(driver,newcustomerdata,j);
        
        Thread.sleep(2000);
        
        CusotmerIds.add(CustomerId);
        
        }
        }catch(Exception E){
        	E.printStackTrace();
        }
        
        
        
         for(String s:CusotmerIds){
        	System.out.println("CustomerID :"+s);
        }
         
         DeleteCustomerPage deletecustomerPage=new DeleteCustomerPage();
         deletecustomerPage.deleteCustomer(driver, CusotmerIds);
         
       //LogOutPage logout=new LogOutPage();
         //logout.logout(driver);
         
         driver.close();
   }
    
}
