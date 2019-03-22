package tax.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.junit.Test;

import tax.InOutJSonFile;
import tax.TaxUtil;
import tax.ValueSetting;
import tax.YearOfAssessment;

//@RunWith(Parameterized.class)
//@Category(TaxCalculate.class)
public class TestMethod {

//	@Test
//	public void testProp()  {
//		
//	}
//	
//	//TODO: Tax Reduction 75%, allowance
//	
//	@Test
//	public void test() {
//		System.out.println(new SimpleDateFormat("'myJSON_'yyyyMMddHHmm'.json'").format(new Date()));
//	}
	
	@Test
	public void Test20190322() throws Exception {
		String inputFileName = "test.json";
		new InOutJSonFile(inputFileName);
	}
//
//	@Test
//	public void SalaryTaxIn2012() {  //Year of Assessment 2012/13 to 2016/17
//		Double husbandSalary = 0.0;
//		Double wifeSalary = 100000.0;
//		TaxUtil taxUtil = new TaxUtil(husbandSalary, wifeSalary, YearOfAssessment.Y2012_13, null);
//		taxUtil.printJointTax();
//		taxUtil.printSeparateTax();
//		taxUtil.printRecommendation();
//	}
//	
//	@Test
//	public void SalaryTaxIn2017() {  //Year of Assessment 2017/18
//		Double husbandSalary = 3000.0;
//		Double wifeSalary = 2000.0;
//		
//		TaxUtil taxUtil = new TaxUtil(husbandSalary, wifeSalary, YearOfAssessment.Y2017_18, null);
//		taxUtil.printJointTax();
//		taxUtil.printSeparateTax();
//		taxUtil.printRecommendation();
//	}
//	
//	@Test
//	public void SalaryTaxIn2018() {  //Year of Assessment 2018/19 onwards
//		Double husbandSalary = 3000.0;
//		Double wifeSalary = 2000.0;
//		
//		TaxUtil taxUtil = new TaxUtil(husbandSalary, wifeSalary, YearOfAssessment.Y2018_19, null);
//		taxUtil.printJointTax();
//		taxUtil.printSeparateTax();
//		taxUtil.printRecommendation();
//	}
//	
//	/*
//	@Test
//	public void testGetUser() {
//	  Assert.assertTrue(_restService.isEnabled());
//	  String result = _restService.get("/user/123.json");
//	  JSONAssert.assertEquals("{id:123,name:\"Joe\"}", result, false);
//		JSONObject data = getRESTData("/friends/367.json");
//		String expected = "{friends:[{id:123,name:\"Corby Page\"},{id:456,name:\"Carter Page\"}]}";
//		JSONAssert.assertEquals(expected, data, false);
//	}
//	*/

}
