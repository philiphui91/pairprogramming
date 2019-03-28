package tax.test;

import org.junit.Test;

import tax.InOutJSonFile;

public class TestJunit4 {

	@Test
	public void Test20190327() throws Exception {
		String inputFileName = "test4.json";
		new InOutJSonFile(inputFileName);
	}
}
