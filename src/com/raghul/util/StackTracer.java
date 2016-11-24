package com.raghul.util;

import java.io.*;

public final class StackTracer {

	public static String getStackTrace(Throwable throwable) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);
		return writer.toString();
	}

	public static String getFormattedStackTrace(Throwable throwable) {
		StringBuilder stringedStackTrace = new StringBuilder();
		stringedStackTrace.append("Root Cause of this Exception is:\n\t");
		stringedStackTrace.append(throwable.toString());
		stringedStackTrace
				.append("\n--------------------------------------------------------------------------------------\n");
		stringedStackTrace
				.append("\tStack Trace of this Exception is as follows...\n");
		int exceptionNumber;
		StackTraceElement[] stackTrace = throwable.getStackTrace();
		for (int i = 0; i < stackTrace.length; i++) {
			exceptionNumber = i + 1;
			stringedStackTrace.append("Exception ").append(exceptionNumber)
					.append(" occurred at:\n\t");
			stringedStackTrace.append("Class: ");
			stringedStackTrace.append(stackTrace[i].getClassName());
			stringedStackTrace.append("\n\tMethod: ");
			stringedStackTrace.append(stackTrace[i].getMethodName());
			stringedStackTrace.append("\n\tLine # ");
			stringedStackTrace.append(stackTrace[i].getLineNumber());
			stringedStackTrace.append("\n");
		}
		return stringedStackTrace.toString();
	}

	public static void printStackTrace(Throwable throwable) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);
		System.err.println(writer.toString());
	}

	public static void printFormattedStackTrace(Throwable throwable) {
		System.err.print("Root Cause of this Exception is:\n\t");
		System.err.print(throwable.toString());
		System.err
				.print("\n--------------------------------------------------------------------------------------\n");
		System.err
				.print("\t\t\tStack Trace of this Exception is as follows...\n");
		int exceptionNumber;
		StackTraceElement[] stackTrace = throwable.getStackTrace();
		for (int i = 0; i < stackTrace.length; i++) {
			exceptionNumber = i + 1;
			System.err.print("Exception ");
			System.err.print(exceptionNumber);
			System.err.print(" occurred at:\n\t");
			System.err.print("Class: ");
			System.err.print(stackTrace[i].getClassName());
			System.err.print("\n\tMethod: ");
			System.err.print(stackTrace[i].getMethodName());
			System.err.print("\n\tLine # ");
			System.err.print(stackTrace[i].getLineNumber());
			System.err.println();
		}
	}
}