package com.raghul.test;

import org.junit.Test;
import com.raghul.util.*;

public class TestLog4j2Example {

	@Test
	public void TestLog4J2() {
		
		String s = PropertyHandler.getProperty("test.properties", "ORDEROLD");
		System.out.println("Property doesn't exist Scenario: "+ s);
		String s1 = PropertyHandler.getProperty("test.properties", "ORDERNEW");
		System.out.println("Property exist Scenario: "+ s1);
		Boolean r = PropertyHandler.setProperty("test.properties", "ORDERNEW",
				"9999");
		System.out.println("Replace existing property value Scenario: "+ r);
		Boolean r1 = PropertyHandler.setProperty("test.properties", "ORDEROLD",
				"7777");
		System.out.println("Write new property value Scenario: "+ r1);
		
		Log.info("Info Message Logged !!!");
		Log.warn("Warn Message Logged !!!");
		Log.error("Error Message Logged !!!");
		Log.debug("Debug Message Logged !!!");
		try {
			throw new NullPointerException("NullError");
		} catch (Exception e) {
			Log.fatal(e);
			Log.debug(e);

		}

	}
}