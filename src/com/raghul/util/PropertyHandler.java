package com.raghul.util;

import java.io.*;
import java.util.*;

public class PropertyHandler {

	private static final String PROPERTIES_PATH = System
			.getProperty("user.dir") + "\\src\\com\\raghul\\properties\\";

	public static String getProperty(String FILENAME, String KEY) {

		String filePath = PROPERTIES_PATH + FILENAME;
		String PROPERTY_VALUE = null;
		Properties props = new Properties();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
			props.load(inputStream);
			inputStream.close();
			PROPERTY_VALUE = props.getProperty(KEY);
		} catch (IOException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					Log.debug(ex);
				} catch (Exception ex) {
					Log.fatal(ex);
				}
			}
		}
		return PROPERTY_VALUE;

	}

	private static void writeProperty(String WFILENAME, String WKEY,
			String WVALUE, Boolean WDUMMY) {

		String filePath = PROPERTIES_PATH + WFILENAME;
		Properties props = new Properties();
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;

		try {
			inputStream = new FileInputStream(filePath);
			props.load(inputStream);
			inputStream.close();

			outputStream = new FileOutputStream(filePath);
			props.setProperty(WKEY, WVALUE);
			props.store(outputStream, null);
		} catch (IOException ex) {
			Log.debug(ex);
		} catch (Exception ex) {
			Log.fatal(ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					Log.debug(ex);
				} catch (Exception ex) {
					Log.fatal(ex);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
					Log.debug(ex);
				} catch (Exception ex) {
					Log.fatal(ex);
				}
			}
		}
	}

	public static Boolean setProperty(String FILENAME, String KEY, String VALUE) {

		PropertyHandler.writeProperty(FILENAME, KEY, VALUE, false);
		String val = PropertyHandler.getProperty(FILENAME, KEY);
		if (val != null && val.equals(VALUE))
			return true;
		else
			return false;
	}

}
