package tax.test;

import org.junit.Test;

import tax.InOutJSonFile;

public class TestJunit5 {

	@Test
	public void Test20190327() throws Exception {
		String inputFileName = "test5.json";
		new InOutJSonFile(inputFileName);
	}
}
