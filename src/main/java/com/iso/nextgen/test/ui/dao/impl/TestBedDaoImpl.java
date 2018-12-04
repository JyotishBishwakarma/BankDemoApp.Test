package com.iso.nextgen.test.ui.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iso.nextgen.test.ui.domain.NewCustomerData;
import com.iso.nextgen.test.ui.domain.UserLoginData;
import com.iso.nextgen.test.ui.dao.TestBedDao;

import com.iso.nextgen.test.ui.domain.*;




public class TestBedDaoImpl extends JdbcDaoSupport implements TestBedDao 
{
		public List<NewCustomerData> findTestsCustomerDetails(String sql, RowMapper<NewCustomerData> rowMapper) throws Exception {
		System.out.println("I am in findTestsCustomerDetails Method");
		return getJdbcTemplate().query(sql, rowMapper);
	}
		
public List<UserLoginData> findTestsUserName(String sql, RowMapper<UserLoginData> rowMapper) throws Exception{
	
			System.out.println("I am in findTestsUserName Method");
			return getJdbcTemplate().query(sql, rowMapper);
	}


}