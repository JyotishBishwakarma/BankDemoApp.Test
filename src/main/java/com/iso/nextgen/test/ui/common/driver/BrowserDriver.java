package com.iso.nextgen.test.ui.common.driver;
 //test 

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.iso.nextgen.test.ui.common.enums.BrowserType;


/**
 * This class drives the browser.
 */
public final class BrowserDriver 
{
	public static String BROWSER_NAME;
	
	/**
	 * <code>BROWSER_KEY</code>
	 */
	public static final String BROWSER_KEY = "selenium.browser";
	
	/**
	 * <code>IMPLICIT_WAIT_TIME_KEY</code>
	 */
	public static final String IMPLICIT_WAIT_TIME_KEY = "selenium.implicitWaitTime";
	
	/**
	 * <code>PAGE_LOAD_TIME_KEY</code>
	 */
	public static final String PAGE_LOAD_TIME_KEY = "selenium.pageLoadWaitTime";
	
	/**
	 * <code>AJAX_LOAD_TIME_KEY</code>
	 */
	public static final String AJAX_LOAD_TIME_KEY = "selenium.ajaxLoadWaitTime";
	
	private static final String REMOTE_URL = System.getProperty("RemoteUrl");
	
	/**
	 * <code>implicitWaitDefaultTime</code>
	 */
	private static int implicitWaitDefaultTime = 30;
	
	/**
	 * <code>pageLoadWaitDefaultTime</code>
	 */
	private static int pageLoadWaitDefaultTime = 60;
	
	/**
	 * <code>ajaxWaitDefaultTime</code>
	 */
	private static int ajaxWaitDefaultTime = 60;
	
	/**
	 * <code>webdriver</code>
	 */
	private static WebDriver webdriver;
	
	/**
	 * <code>waitPageLoad</code>
	 */
	private static WebDriverWait waitPageLoad;
	
	/**
	 * <code>waitAjaxLoad</code>
	 */
	private static WebDriverWait waitAjaxLoad;
	
	/**
	 * <code>javaScriptExecutor</code>
	 */
	private static JavascriptExecutor javaScriptExecutor;
	
	/**
	 * This is the default constructor.
	 */
	private BrowserDriver()
	{
		
	}
	
	/**
	 * This method retrieves the current window handle.
	 * 
	 * @return Returns the string.
	 */
	public static String getCurrentWindowHandle()
	{
		return webdriver.getWindowHandle();
	}
	
	/**
	 * This method retrieves the page source.
	 * 
	 * @return Returns the string.
	 */
	public static String getPageSource()
	{
		return webdriver.getPageSource();
	}
	
	/**
	 * This method retrieves the title.
	 * 
	 * @return Returns the string.
	 */
	public static String getTitle()
	{
		return webdriver.getTitle();
	}
	
	/**
	 * This method returns the url.
	 * 
	 * @return Returns the string.
	 */
	public static String getUrl()
	{
		return webdriver.getCurrentUrl();
	}
	
	/**
	 * This method retrieves all window handles.
	 * 
	 * @return Returns the set.
	 */
	public static Set<String> getWindowHandles()
	{
		return webdriver.getWindowHandles();
	}
	
	/**
	 * This method launches the driver.
	 * 
	 * @return Returns the webdriver.
	 */
	private static WebDriver launchDriver()
	{
		String OS = System.getProperty("os.name");
		switch (getBrowserType())
		{
			case InternetExplorer:
			{
				webdriver = launchInternetExplorer(OS);
				webdriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
				BROWSER_NAME = "InternetExplorer";
				break;
			}
			case Firefox:
			{
				webdriver = launchFirefox(OS);
				BROWSER_NAME = "FireFox";
				break;
			}
			
			default:
			{
				webdriver = launchChrome(OS);
				BROWSER_NAME = "Chrome";
				break;	
			}
		}
		
		webdriver.manage().timeouts().implicitlyWait(getImplicitWaitTime(), TimeUnit.SECONDS);
		webdriver.manage().deleteAllCookies();
		waitPageLoad = new WebDriverWait(webdriver, getPageLoadWaitTime());
		waitAjaxLoad = new WebDriverWait(webdriver, getAjaxLoadWaitTime());
		javaScriptExecutor = (JavascriptExecutor) webdriver;
		
		return webdriver;
	}
	
	/**
	 * This method launches Firefox.
	 * 
	 * @return Returns the webdriver.
	 */
	private static WebDriver launchFirefox(String OS)
	{
		if (REMOTE_URL != null && !REMOTE_URL.equals("")) {
			
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			try {

				return new RemoteWebDriver(new URL(System.getProperty("RemoteUrl")), capabilities);
			}


			catch (MalformedURLException e) {

				e.printStackTrace();
				return null;
			}
		}

		else
		{
			URL FFDriverurl = BrowserDriver.class.getResource("/drivers/geckodriver.exe");
			File file = new File(FFDriverurl.getFile()); 
			System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, file.getAbsolutePath());
			
			//System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, file.getAbsolutePath());
			
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);

			/*return new FirefoxDriver((Capabilities) profile);*/
			return new FirefoxDriver();

		}


	}
	
	/**
	 * This method launches Internet Explorer.
	 * 
	 * @return Returns the webdriver.
	 */
	private static WebDriver launchInternetExplorer(String OS)
	{
		if (REMOTE_URL != null && !REMOTE_URL.equals("")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();			
			
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		    capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		    capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		    capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			try {

				return new RemoteWebDriver(new URL(System.getProperty("RemoteUrl")), capabilities);
			}


			catch (MalformedURLException e) {

				e.printStackTrace();
				return null;
			}
		}

		else
		{
		String driverpath;
		if(OS.contains("windows")) {
			driverpath = "/drivers/IEDriverServer.exe";
		}
		URL IEDriverURL = BrowserDriver.class.getResource("driverpath");
		File file = new File(IEDriverURL.getFile()); 
		System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, file.getAbsolutePath());
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
		capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		
		
		return new InternetExplorerDriver(capabilities); 
		
		
		}
	
		
	}
	
	/**
	 * This method launches Chrome.
	 * 
	 * @return returns the webdriver.
	 */
	private static WebDriver launchChrome(String OS)
	{
		if (REMOTE_URL != null && !REMOTE_URL.equals("")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			try {

				return new RemoteWebDriver(new URL(System.getProperty("RemoteUrl")), capabilities);
			}


			catch (MalformedURLException e) {

				e.printStackTrace();
				return null;
			}
		}

		else
		{
		String driverpath ="";
		if(OS.contains("Windows")) {
			driverpath = "/drivers/chromedriver.exe";
		}
		else if(OS.contains("Linux")) {
			driverpath = "/drivers/chromedriver64linux";
			
		}
		URL chromeDriverURL = BrowserDriver.class.getResource(driverpath);
		File file = new File(chromeDriverURL.getFile()); 
		if(OS.contains("Linux")) {
			try {
				setPermission(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, file.getAbsolutePath());
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		
		return new ChromeDriver(options);
        }
	}
	
	public static void setPermission(File file) throws IOException{
	    Set<PosixFilePermission> perms = new HashSet<>();
	    perms.add(PosixFilePermission.OWNER_READ);
	    perms.add(PosixFilePermission.OWNER_WRITE);
	    perms.add(PosixFilePermission.OWNER_EXECUTE);

	    perms.add(PosixFilePermission.OTHERS_READ);
	    perms.add(PosixFilePermission.OTHERS_WRITE);
	    perms.add(PosixFilePermission.OWNER_EXECUTE);

	    perms.add(PosixFilePermission.GROUP_READ);
	    perms.add(PosixFilePermission.GROUP_WRITE);
	    perms.add(PosixFilePermission.GROUP_EXECUTE);

	    Files.setPosixFilePermissions(file.toPath(), perms);
	}
	
	/**
	 * This method launches Chrome.
	 * 
	 * @return returns the webdriver.
	 */
	private static WebDriver launchRemoteDriver(String OS)
	{
		

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		try {
			if (REMOTE_URL != null && !REMOTE_URL.equals("")) {
				return new RemoteWebDriver(new URL(System.getProperty("RemoteUrl")), capabilities);
			}
			else
				return new InternetExplorerDriver();
		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;
		}
		
		
	}
	/**
	 * This method retrieves the browserType from the environment variables.
	 * 
	 * @return Returns the string.
	 */
	public static BrowserType getBrowserType()
	{
		String browserType = System.getProperty(BROWSER_KEY);
		if (browserType == null || browserType.isEmpty())
		{ 
			return BrowserType.Chrome;
		}
		else
		{
			int type = Integer.parseInt(browserType);
			
			if (BrowserType.InternetExplorer.getCode() == type)
			{
				return BrowserType.InternetExplorer;
			}
			else if (BrowserType.Firefox.getCode() == type)
			{
				return BrowserType.Firefox;
			}
			
			else
			{
				return BrowserType.Chrome;
			}
			
		}
	}
	
	/**
	 * This method retrieves the implicit wait time from the environment variables.
	 * 
	 * @return Returns the integer.
	 */
	private static int getImplicitWaitTime()
	{
		String implicitWaitTime = System.getProperty(IMPLICIT_WAIT_TIME_KEY);
		
		if (implicitWaitTime == null || implicitWaitTime.isEmpty())
		{ 
			return implicitWaitDefaultTime; 
		}
		else
		{
			return Integer.parseInt(implicitWaitTime);
		}
	}
	
	/**
	 * This method retrieves the page load wait time from the environment variables.
	 * 
	 * @return Returns the integer.
	 */
	private static int getPageLoadWaitTime()
	{
		String pageLoadWaitTime = System.getProperty(PAGE_LOAD_TIME_KEY);
		
		if (pageLoadWaitTime == null || pageLoadWaitTime.isEmpty())
		{ 
			return pageLoadWaitDefaultTime;
		}
		else
		{
			return Integer.parseInt(pageLoadWaitTime);
		}
	}
	
	/**
	 * This method retrieves the ajax load wait time from the environment variables.
	 * 
	 * @return Returns the integer.
	 */
	private static int getAjaxLoadWaitTime()
	{
		String ajaxLoadWaitTime = System.getProperty(AJAX_LOAD_TIME_KEY);
		
		if (ajaxLoadWaitTime == null || ajaxLoadWaitTime.isEmpty())
		{ 
			return ajaxWaitDefaultTime;
		}
		else
		{
			return Integer.parseInt(ajaxLoadWaitTime);
		}
	}
	
	/**
	 * This method starts the browser.
	 */
	public static void start()
	{
		if (webdriver == null)
		{
			webdriver = launchDriver();
		}
	}
	
	/**
	 * This method open the browser according to specified url.
	 * 
	 * @param url The url to set.
	 */
	public static void open(String url)
	{
		webdriver.navigate().to(url);
	}
	
	/**
	 * This method refreshes the browser.
	 */
	public static void reload()
	{
		webdriver.navigate().refresh();
	}
	
	/**
	 * This method navigates the browser back to the previous page. 
	 */
	public static void back()
	{
		webdriver.navigate().back();
		waitForPageToLoad();
	}
	
	/**
	 * This method navigates the browser forward to the next page.
	 */
	public static void forward()
	{
		webdriver.navigate().forward();
	}
	
	/**
	 * This method retrieves the text in the alert.
	 * 
	 * @return Returns the string.
	 */
	public static String getAlert()
	{
		return webdriver.switchTo().alert().getText();
	}
	
	/**
	 * This method clicks on the OK button of the alert.
	 */
	public static void clickOkOnAlert()
	{
		webdriver.switchTo().alert().accept();
	}
	
	/**
	 * This method clicks on the Cancel button of the alert.
	 */
	public static void clickCancelOnAlert()
	{
		webdriver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method tells you whether the element you are looking for exists or not. 
	 * 
	 * @param select The select to set.
	 * @param selector The selector to set.
	 * @return Returns true/false
	 */
	public static boolean isElementPresent(By selector)
	{
		try
		{
			webdriver.findElement(selector);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	

	/**
	 * This method waits for the page to load.
	 */
	public static void waitForPageToLoad()
	{
		waitPageLoad.until(executeJavaScript("return document.readyState;", "complete"));
	}
	
	
	/**
	 * This method executes javascript.
	 * 
	 * @param javascript The javascript to set.
	 * @param expectedString The expectedString to set.
	 * @return Returns true/false.
	 */
	private static ExpectedCondition<Boolean> executeJavaScript(final String javascript, final String expectedString) 
	{
		return new ExpectedCondition<Boolean>() 
				{
					/**
					 * This method executes the string (javascript).
					 * 
					 * @param driver The driver to set.
					 * @return Returns true/false.
					 */
					public Boolean apply(WebDriver driver) 
					{
						try
						{
							return expectedString.equalsIgnoreCase(javaScriptExecutor.executeScript(javascript).toString());
						}
						catch (Exception e)
						{
							return false;
						}
					}
				};
	}
	
	/**
	 * This method will wait for the element to be available on the screen.
	 * 
	 * @param select The select to set.
	 * @param selector The selector to set.
	 * @param seconds The seconds to set.
	 * @return Returns the webelement
	 */
	public static WebElement waitForElement(WebElement we, int seconds) 
	{
		WebDriverWait wait = new WebDriverWait(webdriver, seconds);

		return wait.until(waitForElementToBeDisplayed(we));
	}

	/**
	 * This method will wait until the element is available.
	 * 
	 * @param select The select to set.
	 * @param selector The selector to set.
	 * @return Returns the webelement
	 */
	private static ExpectedCondition<WebElement> waitForElementToBeDisplayed(final WebElement we) 
	{
		return new ExpectedCondition<WebElement>() 
				{
					/**
					 * This method will retrieve the element.
					 * 
					 * @param driver The driver to set.
					 * @return Returns the webelement.
					 */
					public WebElement apply(WebDriver driver) 
					{
						if(we.isDisplayed()){

							return we;

							}

							return null;

					}
				};
	}
	
	/**
	 * This method with return if element is displayed or not
	 * @param element The webElement to check
	 * @return Returns true if element is displayed
	 */
	public static boolean IsElementDisplayed(By locator) {
		try {
			return webdriver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This method maximizes the browser.
	 */
	public static void maximizeWindow()
	{
		int width = ((Long) javaScriptExecutor.executeScript("return screen.width;")).intValue();
        int height = ((Long) javaScriptExecutor.executeScript("return screen.height;")).intValue();

        switch (getBrowserType())
        {
        	case Firefox:
            {
            	maximizeFirefoxWindow(width, height);
                break;
            }
            
            case InternetExplorer:
            {
            	maximizeInternetExplorerWindow(width, height);
                break;
            }
        }
	}
	
	/**
	 * This method maximizes Firefox.
	 * 
	 * @param screenWidth The screenWidth to set.
	 * @param screenHeight The screenHeight to set.
	 */
	private static void maximizeFirefoxWindow(int screenWidth, int screenHeight)
    {
        webdriver.manage().window().setPosition(new Point(0, 0));
        webdriver.manage().window().setSize(new Dimension(screenWidth, screenHeight));
    }

	/**
	 * This method maximizes Internet Explorer.
	 * 
	 * @param screenWidth The screenWidth to set.
	 * @param screenHeight The screenHeight to set.
	 */
    private static void maximizeInternetExplorerWindow(int screenWidth, int screenHeight)
    {
        javaScriptExecutor.executeScript("return window.moveTo(0, 0)");
        javaScriptExecutor.executeScript("return window.resizeTo(" + Integer.toString(screenWidth) + ", " + Integer.toString(screenHeight) + ")");
    }
    
    /**
     * This method switches to appropriate window.
     * 
     * @param windowName The windowName to set.
     */
	public static void switchWindow(String windowName)
	{
		webdriver.switchTo().window(windowName);
	}
	
	/**
	 * This method closes the appropriate window.
	 * 
	 * @param windowName The windowName to set.
	 */
	public static void closeWindow(String windowName)
	{
		webdriver.switchTo().window(windowName).close();
	}
	
	/**
	 * This method stops the browser.
	 */
	public static void stop()
	{
		if (webdriver != null)
		{
			webdriver.quit();
			
			webdriver = null;
		}
	}
	
	/**
	 * 
	 * @return current WebDriver instance
	 */
	
	public static WebDriver  getDriver()
	{
		return webdriver;
	}
	
	public static Alert waitForAlert(int seconds) {
		FluentWait<WebDriver> wait = new WebDriverWait(webdriver, seconds).ignoring(NullPointerException.class);
		Alert A = wait.until(waitforAlertPresent(webdriver));

		if (A!=null){

			return A;
		}
		else
		{

			return null;
		}

	}
	
	public static Boolean isAlertPresent(){
		try 
	    { 
	        webdriver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}


	public static ExpectedCondition<Alert> waitforAlertPresent(final WebDriver driver){

		return new ExpectedCondition<Alert>() {

			public Alert apply(WebDriver d) {

				Alert alert = driver.switchTo().alert();
				alert.getText();
				return alert;

			}
		};
	}
	
	public static void WaitForElementToBeClickable(WebElement element) {
		boolean clickable;
		do{
			clickable = IsElementClickable(element);
		}while(clickable = false);
	}
	
	public static boolean IsElementClickable(final WebElement element) {
		WebDriverWait wait = new WebDriverWait(webdriver, 5);
	    try{
	    	wait.until(ExpectedConditions.elementToBeClickable(element));
	    	return true;
	    }catch (TimeoutException e){
			return false;
		}
		catch (NoSuchElementException e) {
			return false;
		}catch(StaleElementReferenceException e){
			return false;
		}
	}
	
	
	
	public static void WaitForElementToDisapper(final By locator) {
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(webdriver)
//			       .withTimeout(30, TimeUnit.SECONDS)
//			       .pollingEvery(5, TimeUnit.SECONDS)
//			       .ignoring(NoSuchElementException.class);
//		try{
//            new FluentWait<WebDriver>(webdriver)
//            .withTimeout(30,TimeUnit.SECONDS)
//            .pollingEvery(5,TimeUnit.SECONDS)
//            .ignoring(NoSuchElementException.class)
//            .until(new ExpectedCondition<Boolean>(){
//              public Boolean apply(WebDriver driver){
//                  return (!webdriver.findElement(locator).isDisplayed());                             
//              }
//            }
//          );
//      }catch(TimeoutException e){
//          //System.out.println("Time Out On : "+locator);
//      }
		
//			WebDriverWait wait = new WebDriverWait(webdriver, 15);
//			try{
//				if(isElementPresent(locator)){
//				wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//				}
//			}catch (NoSuchElementException e){
//				//System.out.println("Time Out On : "+locator);
//			}
		try{
			boolean isDisplayed = true;
			do{
				isDisplayed = IsElementDisplayed(locator);
			}while(isDisplayed);
		}catch(NoSuchElementException e){
			
		}catch(StaleElementReferenceException e){
			
		}
			
	  }
	
	
	public static void waitForAngular() {
        final String javaScriptToLoadAngular =
                "var injector = window.angular.element('body').injector();" + 
                "var $http = injector.get('$http');" + 
                "return ($http.pendingRequests.length === 0)";

        ExpectedCondition<Boolean> pendingHttpCallsCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(javaScriptToLoadAngular).equals(true);
            }
        };
        WebDriverWait wait = new WebDriverWait(webdriver, 20);
        try{
        	wait.until(pendingHttpCallsCondition);
        }catch (Exception e){
        	System.out.println(e.getMessage());
        }
        
	}
	
	
	public static void DoubleClick(WebElement element) {
		Actions action = new Actions(webdriver);
		
		action.moveToElement(element).doubleClick().build().perform();
	}
	
	public static void Click(WebElement element){
		try
        {
            //Bridge.LogMessage(ReportPortal.Client.Models.LogLevel.Trace,$"Clicking on Element {element.ToString()}", ReportPortal.Client.Models.LogLevel.Debug);
            if (element != null)
            {
            	ScrollToView(element);
                element.click();
                waitForAngular();


            }
        }
        catch (Exception e)
        {
           // ClickOnElement(element);
           // WaitForAngular();
            //ScrolltoElement(element);
            //new Actions(Webdriver).MoveToElement(element).Build().Perform();
            //element.Click();
            //Console.Write(ex.Message);
        }
	}
	
	public static void HoverOverElement(WebElement element){
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		
		((JavascriptExecutor) webdriver).executeScript(mouseOverScript, element);
	}
	
	public static void ClickElementsWithAction(WebElement element) {
		Actions action = new Actions(webdriver);
		//actions.moveToElement(driver.findElement(By.xpath("//span[normalize-space(text())='Navigation & Pages']"))).click().perform();
		action.moveToElement(element).click().perform();
		
	}
	
	public static void ScrollIntoView(WebElement webElement) {
		((JavascriptExecutor)webdriver).executeScript("arguments[0].scrollIntoView();"    ,webElement);
	}
	
	public static void ScrollWindow(int position){
		JavascriptExecutor jse = (JavascriptExecutor)webdriver;
		jse.executeScript("scroll(0, "+position+");");
//		((JavascriptExecutor) webdriver).executeScript("window.scrollBy(0, "+position+")", "");
	}
	
	public static void ScrollTo(int xPosition , int yPosition)
    {
        String js = "window.scrollTo("+xPosition+", "+yPosition+")";
        JavascriptExecutor jExecutor = (JavascriptExecutor)webdriver;
        jExecutor.executeScript(js);        
    }
	
	public static void ScrollToView(WebElement element)
    {
        if (element.getLocation().y > 50)
        {
            ScrollTo(0, element.getLocation().y - 250); // Make sure element is in the view but below the top navigation pane
        }

    }
	
	public static void ClickStaleElement(WebElement element){
		int attempts = 0;
        while(attempts < 10) {
            try {
                element.click();
                
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
	}
	
	public static void DoubleClickStaleElement(WebElement element){
		int attempts = 0;
        while(attempts < 10) {
            try {
                //element.click();
            	DoubleClick(element);
                
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
	}
	
	public static void SendKeys(WebElement element, String input){
		String inputText = "";
		do{
			element.clear();
			element.sendKeys(input);
			inputText = element.getAttribute("value").trim();			
		}while (!inputText.equalsIgnoreCase(input));
	}
}