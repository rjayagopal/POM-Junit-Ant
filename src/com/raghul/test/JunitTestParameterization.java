package com.raghul.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class JunitTestParameterization {
	private Integer inputNumber;
	private Boolean expectedResult;
	private PrimeNumberChecker primeNumberChecker;

	@Before
	public void initialize() {
		primeNumberChecker = new PrimeNumberChecker();
	}

	// Each parameter should be placed as an argument here
	// Every time runner triggers, it will pass the arguments
	// from parameters we defined in primeNumbers() method
	public JunitTestParameterization(Integer inputNumber,
			Boolean expectedResult) {
		this.inputNumber = inputNumber;
		this.expectedResult = expectedResult;
	}

	@Parameters
	public static Collection<Object[]> inputData() {
		return Arrays.asList(new Object[][] { { 2, true }, { 3, false },
				{ 6, false }, { 19, true }, { 22, false }, { 23, true } });
	}

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	// This test will run 4 times since we have 5 parameters defined
	@Test
	public void testPrimeNumberChecker() {
		System.out.println("Parameterized Number is : " + inputNumber);
		try {
			assertEquals(expectedResult,
					primeNumberChecker.validate(inputNumber));
			System.out.println(expectedResult+ "," + primeNumberChecker.validate(inputNumber));
		} catch (Throwable t) {
			error.addError(t);
		}
		

	}

	public class PrimeNumberChecker {
		public Boolean validate(final Integer primeNumber) {
			for (int i = 2; i < (primeNumber / 2); i++) {
				if (primeNumber % i == 0) {
					return false;
				}
			}
			return true;
		}
	}

}
