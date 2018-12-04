package com.iso.nextgen.test.ui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.iso.nextgen.test.ui.dao.TestBedDao;
import com.iso.nextgen.test.ui.dao.impl.TestBedDaoImpl;
import com.iso.nextgen.test.ui.domain.NewCustomerData;
import com.iso.nextgen.test.ui.domain.UserLoginData;
import com.iso.nextgen.test.ui.mapper.NewCustomerDataMapper;
import com.iso.nextgen.test.ui.mapper.UserLoginDataMapper;
import com.iso.nextgen.test.ui.service.TestBedService;
import com.iso.nextgen.test.ui.service.enums.DomainType;


public class TestBedServiceImpl implements TestBedService 
{
	@Autowired
	private TestBedDao dao;
	
	public void setDao(TestBedDao dao)
	{
		this.dao = dao;
	}
	
	public List<NewCustomerData> findTestsByName() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("I am in findTestsByName");
		return dao.findTestsCustomerDetails(TestBedService.CUSTOMERDETAILS_SQL, new NewCustomerDataMapper());
	}
	
	public List<UserLoginData> findTestsByUserName() throws Exception {
		System.out.println("I am in findTestsByUserName");
		//return dao.findTestsCustomerDetails(TestBedService.LOGINDETAILS_SQL, new UserLoginDataMapper());
		return dao.findTestsUserName(TestBedService.LOGINDETAILS_SQL, new UserLoginDataMapper());
		
	}
}

