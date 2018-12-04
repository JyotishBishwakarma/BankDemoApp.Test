package com.iso.nextgen.test.ui;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.*;

import com.iso.nextgen.test.navigator.EFDNavigator;
import com.iso.nextgen.test.navigator.impl.EFDNavigatorImpl;


@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional(propagation=Propagation.REQUIRED)
public abstract class BaseTestCase extends AbstractTransactionalJUnit4SpringContextTests
{
	public static final String ENVIRONMENT_KEY="TargetEnvironment";
	private EFDNavigator navigator;
	
	
	@Resource
	private List<String> sqlScripts;
	
	private String environment;
	
	private static final String ARGUMENTS = "RunArguments";
	private  String ITERATION;
	int startWith =0;
	int endWith=0;
	

	@Before
	public void start() throws IOException 
	{
		startBrowser();
		
		loadTestBed();
		
	
		if (System.getProperty(BaseTestCase.ENVIRONMENT_KEY) != null) {
			environment = System.getProperty(BaseTestCase.ENVIRONMENT_KEY);
		} else {
			environment = "A";
		}	
		
		if(System.getProperty(BaseTestCase.ARGUMENTS) != null){
			ITERATION = System.getProperty("RunArguments");
		}else{
			ITERATION = "All";
		}
		
	}
	
	public void startBrowser() 
	{
		navigator = new EFDNavigatorImpl();
		navigator.start();
		
	}
	
	public void loadTestBed()
	{
		
		for (String sqlScript : sqlScripts) 
		{
			executeSqlScript(sqlScript, true);
		}	
	}

	@After
	public void stop() 
	{
		navigator.stop();
	}
	
	public EFDNavigator getNavigator()
	{
		return navigator;
	}
	
	public String getEnvironment()
	{
		return environment;
	}
	public int getMin()
	{


		try {
			if (ITERATION.equalsIgnoreCase("All"))
			{
				 startWith = 0;
				
			}

			else if (ITERATION.matches("[0-9]*,[0-9]*"))
			{
				String[] spliter= ITERATION.split(",");
				startWith = Integer.parseInt(spliter[0])-1;
			
			}
			else if(ITERATION.matches("[0-9]*"))
			{
				startWith = Integer.parseInt(ITERATION)-1;
			}
			
			else
			{
				startWith = 0;
			}
		} catch (Exception e) {
			
			return 0;
		}
		return startWith;
		

	}

	public int getMax(int size)

	{
		

		try {
			if (ITERATION.equalsIgnoreCase("All"))
			{
				endWith= size;
			}
			else  if (ITERATION.matches("[0-9]*,[0-9]*"))
			{
				String[] spliter= ITERATION.split(",");
				endWith = Integer.parseInt(spliter[1]);;
				
			}
			
			else if(ITERATION.matches("[0-9]*"))
			{
				endWith = Integer.parseInt(ITERATION);
			}
			
			else
			{
				endWith= size;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return size;
		}
		return endWith;
	}
}