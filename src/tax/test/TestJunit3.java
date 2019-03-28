package tax.test;

import org.junit.Test;

import tax.InOutJSonFile;

public class TestJunit3 {

	@Test
	public void Test20190327() throws Exception {
		String inputFileName = "test3.json";
		new InOutJSonFile(inputFileName);
	}
}
