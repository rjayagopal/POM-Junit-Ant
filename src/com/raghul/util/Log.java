package com.raghul.util;

import org.apache.logging.log4j.*;

public class Log {

	private static final String PROPERTIES_PATH = System
			.getProperty("user.dir") + "\\src\\com\\raghul\\properties\\";
	private static final String LOG4J2_FILENAME = "log4j2.xml";
	
	public static final Logger Log = createLogger();

	private static Logger createLogger() {
		String filePath = PROPERTIES_PATH + LOG4J2_FILENAME;
		System.setProperty("log4j.configurationFile", filePath);
		Logger logger = LogManager.getLogger(Log.class.getName());
		return logger;
	}
	
	public static void startTestCase(String sTestCaseName) {
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
		Log.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName
				+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
	}

	public static void endTestCase(String sTestCaseName) {
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-"
				+ "             XXXXXXXXXXXXXXXXXXXXXX");
		Log.info("X");
		Log.info("X");
		Log.info("X");
		Log.info("X");
	}

	public static void debug(String message) {
		Log.debug(message);
	}

	public static void debug(Exception e) {
		Log.debug(StackTracer.getStackTrace(e));
	}

	public static void info(String message) {
		Log.info(message);
	}

	public static void warn(String message) {
		Log.warn(message);
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void fatal(Exception e) {
		Log.fatal(StackTracer.getFormattedStackTrace(e));
	}
}
