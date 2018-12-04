package com.iso.nextgen.test.ui.common.enums;




/**
 * This class contains the acceptable browsers.
 */
public enum BrowserType 
{
	/**
	 * <code>Enum</code>
	 */
	Firefox(1), InternetExplorer(2), Chrome(3);
	
	/**
	 * <code>code</code>
	 */
	private int code;
	
	/**
	 * This is the default constructor.
	 * 
	 * @param code The code to set.
	 */
	BrowserType(int code)
	{
		this.code = code;
	}
	
	/**
	 * This method retrieves the code.
	 * 
	 * @return Returns the string.
	 */
	public int getCode()
	{
		return code;
	}
}
