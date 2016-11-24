package com.raghul.test;

import org.junit.Test;

import junit.framework.TestCase;


public class JunitTestCaseExtension extends TestCase  {
	
	 @Test
	 public void testCase() throws Throwable {
		 
		 this.getName();
		 
		 System.out.println(this.getClass());
		 System.out.println(this.getName());
		 this.setName("New Name");
		 System.out.println(this.getName());
		 
		 //this.countTestCases();
		 //this.getName();
		 //this.run();
		 //this.run(result);
		 //this.runBare();
		 //this.setName(name);
		 
		 

		 
	 }
	

}
