package com.iso.nextgen.test.ui.common.core;


import org.openqa.selenium.support.ui.LoadableComponent;


/**
 * This abstract class is used by every page object to represent a page in the application.
 */
public abstract class PageBase extends LoadableComponent<PageBase>
{
	/**
	 * <code>pageUrl</code>
	 */
	private String pageUrl;

	/**
	 * This is the default constructor.
	 * 
	 * @param pageUrl The pageUrl to set.
	 */
	public PageBase(String pageUrl)
	{
		this.pageUrl = pageUrl;
	}
	
	/**
	 * This is an alternate constructor.
	 *
	 * @param domain The domain to set.
	 * @param pageUrl The pageUrl to set.
	 */
	public PageBase(String domain, String pageUrl)
	{
		this.pageUrl = pageUrl+domain;
	}
	
	/**
	 * This method retrieve the pageUrl.
	 * 
	 * @return Returns the string.
	 */
	public String getPageUrl() 
	{
		return pageUrl;
	}
}