package com.iso.nextgen.test.navigator.impl;

import java.net.URISyntaxException;

import com.iso.nextgen.test.navigator.EFDNavigator;
import com.iso.nextgen.test.ui.common.core.PageBase;
import com.iso.nextgen.test.ui.common.driver.BrowserDriver;


public class EFDNavigatorImpl implements EFDNavigator 
{
	public String getCurrentUrl()
    {
        return BrowserDriver.getUrl();
    }
	
	public void sleep(int t) throws InterruptedException
    {
        Thread.sleep(t);
    }

  
    public PageBase switchWindow(PageBase target, String windowName)
    {
        BrowserDriver.switchWindow(windowName);
        BrowserDriver.waitForPageToLoad();

        return target;
    }

    public void closeWindow(String windowName)
    {
        BrowserDriver.closeWindow(windowName);
    }

    public String getWindow(int index)
    {
    	return (String) BrowserDriver.getWindowHandles().toArray()[index];
    }

    public PageBase get(PageBase target) throws URISyntaxException {
		BrowserDriver.waitForPageToLoad();
		
		return target.get();
		
	}

	public PageBase open(PageBase target) throws URISyntaxException {
		System.out.println(target.getPageUrl());
		BrowserDriver.open(target.getPageUrl());
		
		BrowserDriver.waitForPageToLoad();
		
		
		return target;
		
	}

	public void start() {
		BrowserDriver.start();
		BrowserDriver.maximizeWindow();
		
	}

	public void stop() {
		
		BrowserDriver.stop();
	}
}