package com.raghul.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
		JunitTestCase.class, 
		JunitTestSet.class,
		JunitTestTimeout.class, 
		JunitTestExceptions.class,
		JunitTestCaseExtension.class, 
		JunitTestResultExtension.class,
		JunitTestParameterization.class, 
		TestJdbcExample.class,
		TestLog4j2Example.class,
		JunitTestCaseExercise.class})
public class JunitTestSuite {

}
