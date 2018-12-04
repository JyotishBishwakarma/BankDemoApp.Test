package com.iso.nextgen.test.ui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;



import org.springframework.jdbc.core.RowMapper;
import org.apache.commons.lang3.StringUtils;

import com.iso.nextgen.test.ui.domain.NewCustomerData;

public class NewCustomerDataMapper implements RowMapper<NewCustomerData>{
	
	public NewCustomerData mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		NewCustomerData newcustomerdata=new NewCustomerData();
		
		//newcustomerdata.setName(StringUtils.trim(rs.getString("name")));
		
		newcustomerdata.setName(StringUtils.trim(rs.getString("name")));
		newcustomerdata.setGender(StringUtils.trim(rs.getString("gender")));
		newcustomerdata.setDob(StringUtils.trim(rs.getString("dob")));
		newcustomerdata.setAddress(StringUtils.trim(rs.getString("address")));
		newcustomerdata.setCity(StringUtils.trim(rs.getString("city")));
		newcustomerdata.setState(StringUtils.trim(rs.getString("state")));
		newcustomerdata.setPin(StringUtils.trim(rs.getString("pin")));
		newcustomerdata.setMobno(StringUtils.trim(rs.getString("mobno")));
		newcustomerdata.setEmail(StringUtils.trim(rs.getString("email")));
		newcustomerdata.setPassword(StringUtils.trim(rs.getString("password")));
		
		
		//System.out.println("Jyotish :"+newcustomerdata.getName());
		
		
		return newcustomerdata;
		
		
	}
	

	

}
