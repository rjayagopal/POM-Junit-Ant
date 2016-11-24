package com.raghul.test;

import org.junit.Test;

public class JunitTestTimeout {

	@Test(timeout = 1000)
	public void testTimeout() throws InterruptedException {

		Thread.sleep(100000000);

	}

}
