package controller;

import org.apache.log4j.Logger;
import java.io.*;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

public class ErrorLogController {
	
	public void error(String log) throws IOException{
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/log4j/log4j.properties"));
		PropertyConfigurator.configure(props);
		Logger log1 = Logger.getLogger(ErrorLogController.class.getName());
	    log1.error(log);
	}
}
