package com.raghul.test;

import java.io.File;
import org.junit.Test;
import com.raghul.util.*;

public class TestJdbcExample {

	@Test
	public void TestJdbc() {

		try {
			JdbcUtilities test = new JdbcUtilities();
			String query = "SELECT * FROM EWPS.WMDM178_OUT FETCH FIRST 1 ROW ONLY";
			// String query = "SELECT * FROM GOLDTEST4.ADM_DICTIONARY";

			Log.info(test.toCsvString(test.executeSelect(query), '|',true));

			File file = new File("C:\\Users\\TPURJaya\\Desktop\\sample.csv");
			System.out.println(test.toCsvFile(test.getResultset(), ',', true,
					file));
			test.disconnect(test.getResultset(), test.getStatement(),
					test.getConnection());

		} catch (Exception e) {
			Log.fatal(e);
		}

	}

}
