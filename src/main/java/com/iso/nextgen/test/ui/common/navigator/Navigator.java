package com.iso.nextgen.test.ui.common.navigator;


import java.net.URISyntaxException;

import com.iso.nextgen.test.ui.common.core.PageBase;



/**
 * This interface is implemented to allow navigation throughout the application.
 */
public interface Navigator 
{
	/**
	 * This method will open the page based on the target.
	 * 
	 * @param target The target to set.
	 * @return Returns the pagebase.
	 * @throws URISyntaxException
	 */
	PageBase open(PageBase target) throws URISyntaxException;
	
	/**
	 * This method will navigate to the target.
	 * 
	 * @param target The target to set.
	 * @return Returns the pagebase.
	 * @throws URISyntaxException
	 */
	PageBase get(PageBase target) throws URISyntaxException;
	
	/**
	 * This method will start the browser.
	 */
	void start();
	
	/**
	 * This method will stop the browser.
	 */
	void stop();
}