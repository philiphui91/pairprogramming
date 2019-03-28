package tax.test;

import org.junit.Test;

import tax.InOutJSonFile;

public class TestJunit2 {

	@Test
	public void Test20190327() throws Exception {
		String inputFileName = "test2.json";
		new InOutJSonFile(inputFileName);
	}
}
