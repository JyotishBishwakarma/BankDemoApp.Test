package com.iso.nextgen.test.ui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.iso.nextgen.test.ui.domain.NewCustomerData;
import com.iso.nextgen.test.ui.domain.UserLoginData;

public class UserLoginDataMapper implements RowMapper<UserLoginData>{

	public UserLoginData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		UserLoginData userlogindata=new UserLoginData();
		
		userlogindata.setUsername(StringUtils.trim(rs.getString("username")));
		userlogindata.setPassword(StringUtils.trim(rs.getString("password")));
		
		return userlogindata;
	
	}

}
