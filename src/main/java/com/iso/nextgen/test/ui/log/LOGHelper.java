package com.iso.nextgen.test.ui.log;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 
 * This class is  to write the HTML Logs to the specified location . it extends the HTMLLAyout from Log4j
 */
public class LOGHelper extends HTMLLayout {
	protected final int BUF_SIZE = 256;
	protected final int MAX_CAPACITY = 1024;

	static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";

	// output buffer appended to when format() is invoked
	private StringBuffer sbuf = new StringBuffer(BUF_SIZE);

	
	//public static final String LOCATION_INFO_OPTION = "LocationInfo";

	
	public static final String TITLE_OPTION = "Title";

	// Print no location info by default
	boolean locationInfo = false;

	String title = "Log4J Log Messages";

	
	public
	String format(LoggingEvent event) {
		String fontColor = "";
		if(sbuf.capacity() > MAX_CAPACITY) {
			sbuf = new StringBuffer(BUF_SIZE);
		} else {
			sbuf.setLength(0);
		}
		
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String myDateString = sdf.format(myDate);

		sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);
		
		if (event.getLevel().equals(Level.DEBUG)) {
			fontColor = "#339933";			
		}
		else if(event.getLevel().isGreaterOrEqual(Level.WARN)) {
			fontColor = "#993300";			
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getMessage().toString().toUpperCase().contains("PASS")) {
			fontColor = "GREEN";			
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getMessage().toString().toUpperCase().contains("FAIL")) {
			fontColor = "RED";			
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getMessage().toString().toUpperCase().contains("ITERATION")) {
			fontColor = "BLUE";			
		} 
		
		else if(event.getLevel().equals(Level.INFO)&&event.getMessage().toString().toUpperCase().contains("EXECUTING")) {
			fontColor = "Maroon";			
		} 
		
		sbuf.append("<td>");
		sbuf.append("<font color=\""+fontColor+"\"><strong>");
		//sbuf.append(event.timeStamp - LoggingEvent.getStartTime());
		sbuf.append(myDateString);
		sbuf.append("</font></td>" + Layout.LINE_SEP);

		/*String escapedThread = Transform.escapeTags(event.getThreadName());
		sbuf.append("<td title=\"" + escapedThread + " thread\">");
		sbuf.append(escapedThread);
		sbuf.append("</td>" + Layout.LINE_SEP);*/

		sbuf.append("<td title=\"Level\">");
		
		if (event.getLevel().equals(Level.DEBUG)) {
			sbuf.append("<font color=\"#339933\">");
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sbuf.append("</font>");
		}
		else if(event.getLevel().isGreaterOrEqual(Level.WARN)) {
			sbuf.append("<font color=\"#993300\"><strong>");
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getMessage().toString().toUpperCase().contains("PASS")) {
			sbuf.append("<font color=\"GREEN\"><strong>");
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getMessage().toString().toUpperCase().contains("FAIL")) {
			sbuf.append("<font color=\"RED\"><strong>");
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getMessage().toString().toUpperCase().contains("ITERATION")) {
			sbuf.append("<font color=\"BLUE\"><strong>");
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sbuf.append("</strong></font>");
		} 
		
		else if(event.getLevel().equals(Level.INFO)&&event.getMessage().toString().toUpperCase().contains("EXECUTING")) {
			sbuf.append("<font color=\"Maroon\"><strong><b>");
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sbuf.append("</b></strong></font>");
		} 
		else {
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
		}
		sbuf.append("</td>" + Layout.LINE_SEP);
		
		
			
		String escapedLogger = Transform.escapeTags(event.getLoggerName());
		
		sbuf.append("<td title=\"" + escapedLogger + " category\">");
		
		if(event.getLevel().equals(Level.INFO)&& event.getRenderedMessage().toString().toUpperCase().contains("PASS")) {
			sbuf.append("<font color=\"GREEN\"><strong>");
			sbuf.append(escapedLogger);
			//sbuf.append(event.getFQNOfLoggerClass()+"."+event.getLoggerName());
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getRenderedMessage().toString().toUpperCase().contains("FAIL")) {
			sbuf.append("<font color=\"RED\"><strong>");
			sbuf.append(escapedLogger);
			//sbuf.append(event.getFQNOfLoggerClass()+"."+event.getLoggerName());
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getRenderedMessage().toString().toUpperCase().contains("ITERATION")) {
			sbuf.append("<font color=\"BLUE\"><strong>");
			sbuf.append(escapedLogger);
			//sbuf.append(event.getFQNOfLoggerClass()+"."+event.getLoggerName());
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getRenderedMessage().toString().toUpperCase().contains("EXECUTING")) {
			sbuf.append("<font color=\"Maroon\"><strong><b>");
			sbuf.append(escapedLogger);
			//sbuf.append(event.getFQNOfLoggerClass()+"."+event.getLoggerName());
			sbuf.append("</b></strong></font>");
		} 
		else
		{
		sbuf.append(escapedLogger);
		}
		
		sbuf.append("</td>" + Layout.LINE_SEP);

		if(locationInfo) {
			LocationInfo locInfo = event.getLocationInformation();
			sbuf.append("<td>");
			sbuf.append(Transform.escapeTags(locInfo.getFileName()));
			sbuf.append(':');
			sbuf.append(locInfo.getLineNumber());
			sbuf.append("</td>" + Layout.LINE_SEP);
		}

		sbuf.append("<td title=\"Message\">");
		if(event.getLevel().equals(Level.INFO)&& event.getRenderedMessage().toString().toUpperCase().contains("PASS")) {
			sbuf.append("<font color=\"GREEN\"><strong>");
			sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getRenderedMessage().toString().toUpperCase().contains("FAIL")) {
			sbuf.append("<font color=\"RED\"><strong>");
			sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getRenderedMessage().toString().toUpperCase().contains("ITERATION")) {
			sbuf.append("<font color=\"BLUE\"><strong>");
			sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
			sbuf.append("</strong></font>");
		} 
		else if(event.getLevel().equals(Level.INFO)&&event.getRenderedMessage().toString().toUpperCase().contains("EXECUTING")) {
			sbuf.append("<font color=\"Maroon\"><strong><b>");
			sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
			sbuf.append("</b></strong></font>");
		} 
		else
		{
		sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
		}
		sbuf.append("</td>" + Layout.LINE_SEP);
		sbuf.append("</tr>" + Layout.LINE_SEP);

		if (event.getNDC() != null) {
			sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
			sbuf.append("NDC: " + Transform.escapeTags(event.getNDC()));
			sbuf.append("</td></tr>" + Layout.LINE_SEP);
		}

		String[] s = event.getThrowableStrRep();
		if(s != null) {
			sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"6\">");
			appendThrowableAsHTML(s, sbuf);
			sbuf.append("</td></tr>" + Layout.LINE_SEP);
		}

		return sbuf.toString();
	}

	void appendThrowableAsHTML(String[] s, StringBuffer sbuf) {
		if(s != null) {
			int len = s.length;
			if(len == 0)
				return;
			sbuf.append(Transform.escapeTags(s[0]));
			sbuf.append(Layout.LINE_SEP);
			for(int i = 1; i < len; i++) {
				sbuf.append(TRACE_PREFIX);
				sbuf.append(Transform.escapeTags(s[i]));
				sbuf.append(Layout.LINE_SEP);
			}
		}
	}

	/**
        Returns appropriate HTML headers.
	 */
	public
	String getHeader() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"  + Layout.LINE_SEP);
		sbuf.append("<html>" + Layout.LINE_SEP);
		sbuf.append("<head>" + Layout.LINE_SEP);
		sbuf.append("<title>" + title + "</title>" + Layout.LINE_SEP);
		sbuf.append("<style type=\"text/css\">"  + Layout.LINE_SEP);
		sbuf.append("<!--"  + Layout.LINE_SEP);
		sbuf.append("body, table {font-family: arial,sans-serif; font-size: small;}" + Layout.LINE_SEP);
		sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
		sbuf.append("-->" + Layout.LINE_SEP);
		sbuf.append("</style>" + Layout.LINE_SEP);
		sbuf.append("</head>" + Layout.LINE_SEP);
		sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" + Layout.LINE_SEP);
		sbuf.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
		sbuf.append("Log session start time " + new java.util.Date() + "<br>" + Layout.LINE_SEP);
		sbuf.append("<br>" + Layout.LINE_SEP);
		sbuf.append("<table cellspacing=\"0\" cellpadding=\"5\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" + Layout.LINE_SEP);
		sbuf.append("<tr>" + Layout.LINE_SEP);
		sbuf.append("<th>Time</th>" + Layout.LINE_SEP);
		/*sbuf.append("<th>Thread</th>" + Layout.LINE_SEP);*/
		sbuf.append("<th>Level</th>" + Layout.LINE_SEP);
		//sbuf.append("<th>Execution Time</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Category</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Message</th>" + Layout.LINE_SEP);
		if(locationInfo) {
			sbuf.append("<th>File:Line</th>" + Layout.LINE_SEP);
		}
		sbuf.append("</tr>" + Layout.LINE_SEP);
		return sbuf.toString();
	}

}
