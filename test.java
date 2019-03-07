
import org.junit.Test;

//@RunWith(Parameterized.class)
//@Category(TaxCalculate.class)
public class test {

	/*
	@Test
	public void testProp() throws IOException {
		Properties prop = new Properties();
		String rootpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println("A" + rootpath);
		FileInputStream fis = new FileInputStream("prop.properties");
		prop.load(fis);
		
		System.out.println(prop.get("A"));
	}
	*/
	
	//TODO: Tax Reduction 75%

	@Test
	public void SalaryTaxIn2012() {  //Year of Assessment 2012/13 to 2016/17
		Double husbandSalary = 0.0;
		Double wifeSalary = 100000.0;
		TaxUtil taxUtil = new TaxUtil(husbandSalary, wifeSalary, YearOfAssessment.Y2012_13);
		taxUtil.printJointTax();
		taxUtil.printSeparateTax();
		taxUtil.printRecommendation();
	}
	
	@Test
	public void SalaryTaxIn2017() {  //Year of Assessment 2017/18
		Double husbandSalary = 3000.0;
		Double wifeSalary = 2000.0;
		
		TaxUtil taxUtil = new TaxUtil(husbandSalary, wifeSalary, YearOfAssessment.Y2017_18);
		taxUtil.printJointTax();
		taxUtil.printSeparateTax();
		taxUtil.printRecommendation();
	}
	
	@Test
	public void SalaryTaxIn2018() {  //Year of Assessment 2018/19 onwards
		Double husbandSalary = 3000.0;
		Double wifeSalary = 2000.0;
		
		TaxUtil taxUtil = new TaxUtil(husbandSalary, wifeSalary, YearOfAssessment.Y2018_19);
		taxUtil.printJointTax();
		taxUtil.printSeparateTax();
		taxUtil.printRecommendation();
	}
	
	/*
	@Test
	public void testGetUser() {
	  Assert.assertTrue(_restService.isEnabled());
	  String result = _restService.get("/user/123.json");
	  JSONAssert.assertEquals("{id:123,name:\"Joe\"}", result, false);
		JSONObject data = getRESTData("/friends/367.json");
		String expected = "{friends:[{id:123,name:\"Corby Page\"},{id:456,name:\"Carter Page\"}]}";
		JSONAssert.assertEquals(expected, data, false);
	}
	*/

}
