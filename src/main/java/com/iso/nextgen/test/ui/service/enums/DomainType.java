package com.iso.nextgen.test.ui.service.enums;

public enum DomainType 
{
	ISONET("I"), APPLICATION("A");
	
	private String code;
	
	DomainType(String code)
	{
		this.code = code;
	}
	
	public String getCode()
	{
		return code;
	}
}