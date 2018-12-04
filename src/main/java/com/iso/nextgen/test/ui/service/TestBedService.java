package com.iso.nextgen.test.ui.service;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.iso.nextgen.test.ui.domain.NewCustomerData;
import com.iso.nextgen.test.ui.domain.UserLoginData;
import com.iso.nextgen.test.ui.mapper.NewCustomerDataMapper;
import com.iso.nextgen.test.ui.service.enums.DomainType;


public interface TestBedService 
{
	public String CUSTOMERDETAILS_SQL = "select * from dbo.customer_details";
	public String LOGINDETAILS_SQL = "select * from dbo.user_login";
		
	List<NewCustomerData> findTestsByName() throws Exception;
	List<UserLoginData> findTestsByUserName() throws Exception;
	
	
}
