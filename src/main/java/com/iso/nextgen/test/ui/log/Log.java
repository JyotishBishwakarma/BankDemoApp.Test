package com.iso.nextgen.test.ui.log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/*
 * Author Sujendra (i80552)
 */
public class Log {
	static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    private FileHandler fileHTML;
    private Formatter formatterHTML;
    
    public Log(){
    	
    	try{
    		// get the global logger to configure it
            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

            Handler[] handlers = Logger.getLogger( "" ).getHandlers();
    	    for ( int index = 0; index < handlers.length; index++ ) {
    	      handlers[index].setLevel( Level.INFO );
    	    }

            logger.setLevel(Level.INFO);

            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MMM-dd-HHmm");
            Date resultdate = new Date();
            String stamp = date_format.format(resultdate);
            
            String separator = "\\";
            if(System.getProperty("os.name")!="Windows"){
            	separator = "//";
            }
            
            String destinationFolder = "target"+separator;

            fileHTML = new FileHandler(destinationFolder+"NextGen_"+stamp +".html");
           
            // create an HTML formatter
            formatterHTML = new LogFormatter();
            fileHTML.setFormatter(formatterHTML);
            logger.addHandler(fileHTML);

    	}catch(IOException e){
    		System.out.println("Error creating logs");
    	}

            }

}
