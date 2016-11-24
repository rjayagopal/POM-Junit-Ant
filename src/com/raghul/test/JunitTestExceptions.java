package com.raghul.test;

import org.junit.Test;

public class JunitTestExceptions {

	@Test(expected = NullPointerException.class)
	public void testExceptions() {

		System.out.println("testExceptions");
		throw new NullPointerException("NullError");

	}

}
