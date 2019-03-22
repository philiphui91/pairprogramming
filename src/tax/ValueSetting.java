package tax;

public class ValueSetting {
	
	public static final Double defaultTaxGap = 40000.0;
	public static final Double defaultStandardRate = 0.15;
	public static final Double[] defaultTaxRate = {0.02, 0.07, 0.12, 0.17};
	public static final YearOfAssessment dafaultYear = YearOfAssessment.Y2018_19;
	public static final Double DeductionOfJointTax = 264000.0;
	public static final Double DeductionOfSeparateTax = 132000.0;
	
	// TaxMaster class keeps MPF MAX. Deduction and MPF MIN. Income
	
	public static final Double taxGap2012_16 = 40000.0;
	public static final Double standardRate2012_16 = 0.15;
	public static final Double[] taxRate2012_16 = {0.02, 0.07, 0.12, 0.17};

	public static final Double taxGap2017_18 = 45000.0;
	public static final Double standardRate2017_18 = 0.15;
	public static final Double[] taxRate2017_18 = {0.02, 0.07, 0.12, 0.17};

	public static final Double taxGap2018_19 = 50000.0;
	public static final Double standardRate2018_19 = 0.15;
	public static final Double[] taxRate2018_19 = {0.02, 0.06, 0.10, 0.14, 0.17};
	
	public static final String inFolderPath = "src/tax/in/";
	public static final String outFolderPath = "src/tax/out/";

}
