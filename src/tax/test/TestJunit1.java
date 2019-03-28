package tax.test;

import org.junit.Test;

import tax.InOutJSonFile;

public class TestJunit1 {

	@Test
	public void Test20190327() throws Exception {
		String inputFileName = "test1.json";
		new InOutJSonFile(inputFileName);
	}
}
