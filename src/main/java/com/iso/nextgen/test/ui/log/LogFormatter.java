package com.iso.nextgen.test.ui.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
	public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);
        
        
        buf.append("<tr>\n");

        // colorize any levels >= WARNING in red
        if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
            buf.append("\t<td style=\"color:red\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
        } else {
            buf.append("\t<td>");
            buf.append(rec.getLevel());
        }

        buf.append("</td>\n");
        buf.append("\t<td>");
        buf.append(calcDate(rec.getMillis()));
        buf.append("</td>\n");
        buf.append("\t<td>");
        buf.append(rec.getSourceClassName()+"."+rec.getSourceMethodName());
        buf.append("</td>\n");
        buf.append("\t<td>");
        buf.append(formatMessage(rec));
        buf.append("</td>\n");
        buf.append("</tr>\n");

        return buf.toString();
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }

    // this method is called just after the handler using this
    // formatter is created
    public String getHead(Handler h) {
    	StringBuffer sbuf = new StringBuffer();
    	
    	sbuf.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"  + "\n");
		sbuf.append("<html>" + "\n");
		sbuf.append("<head>" + "\n");
		sbuf.append("<title>NextGen Automation Logs</title>" + "\n");
		sbuf.append("<style type=\"text/css\">"  + "\n");
		sbuf.append("<!--"  + "\n");
		sbuf.append("body, table {font-family: arial,sans-serif; font-size: small;}" + "\n");
		sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + "\n");
		sbuf.append("-->" + "\n");
		sbuf.append("</style>" + "\n");
		sbuf.append("</head>" + "\n");
		sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" + "\n");
		sbuf.append("<hr size=\"1\" noshade>" + "\n");
		sbuf.append("Log session start time " + new java.util.Date() + "<br>" + "\n");
		sbuf.append("<br>" + "\n");
		sbuf.append("<table cellspacing=\"0\" cellpadding=\"5\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" + "\n");
		sbuf.append("<tr>" + "\n");
		sbuf.append("<th>Level</th>" + "\n");
		
		sbuf.append("<th>Execution Time</th>" + "\n");
		
		sbuf.append("<th>Category</th>" + "\n");
		sbuf.append("<th>Log Message</th>" + "\n");
		
		sbuf.append("</tr>" + "\n");
		return sbuf.toString();
      }

    // this method is called just after the handler using this
    // formatter is closed
    public String getTail(Handler h) {
        return "</table>\n</body>\n</html>";
    }
    
    public String formatMessage(LogRecord log){
    	StringBuffer sbuf = new StringBuffer(1000);
    	
    	
    	 
		if(log.getMessage().toString().toUpperCase().contains("PASS")){
			sbuf.append("<font color=\"GREEN\"><strong>");
			sbuf.append(log.getMessage());
			sbuf.append("</strong></font>");
		}else if(log.getMessage().toString().toUpperCase().contains("FAIL")){
			sbuf.append("<font color=\"RED\"><strong>");
			sbuf.append(log.getMessage());
			sbuf.append("</strong></font>");
		}else if(log.getMessage().toString().toUpperCase().contains("ITERATION")) {
			sbuf.append("<font color=\"BLUE\"><strong>");
			sbuf.append(log.getMessage());
			sbuf.append("</strong></font>");
		}else if(log.getMessage().toString().toUpperCase().contains("EXECUTING")) {
			sbuf.append("<font color=\"Maroon\"><strong><b>");
			sbuf.append(log.getMessage());
			sbuf.append("</b></strong></font>");
		}else{
			sbuf.append("<font color=\"Black\" >");
			sbuf.append(log.getMessage());
			sbuf.append("</b></strong></font>");
		}
    	return sbuf.toString();
    }
}
