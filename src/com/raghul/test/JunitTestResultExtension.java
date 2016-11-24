package com.raghul.test;

import org.junit.Test;
import junit.framework.TestResult;

public class JunitTestResultExtension extends TestResult {

	@Test
	public void testResult() throws Throwable {

		System.out.println(super.wasSuccessful());

	}

}
