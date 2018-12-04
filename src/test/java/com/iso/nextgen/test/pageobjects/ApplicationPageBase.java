package com.iso.nextgen.test.pageobjects;

import org.openqa.selenium.support.PageFactory;

import com.iso.nextgen.test.ui.common.core.PageBase;
import com.iso.nextgen.test.ui.common.driver.BrowserDriver;



public abstract class ApplicationPageBase extends PageBase
{
	
	
	
	public ApplicationPageBase(String pageUrl) 
	{
		super(pageUrl);
	}
	
	public ApplicationPageBase(String pageUrl, String domain) 
	{
		super(pageUrl, domain);
	}
	
	@Override
	protected void load() 
	{

	}
	
}