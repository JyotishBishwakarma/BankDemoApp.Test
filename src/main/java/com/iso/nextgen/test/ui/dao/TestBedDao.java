package com.iso.nextgen.test.ui.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.iso.nextgen.test.ui.domain.*;
import com.iso.nextgen.test.ui.domain.NewCustomerData;



public interface TestBedDao 
{
	List<NewCustomerData> findTestsCustomerDetails(String sql, RowMapper<NewCustomerData> rowMapper) throws Exception;
	List<UserLoginData> findTestsUserName(String sql, RowMapper<UserLoginData> rowMapper) throws Exception;
	
}