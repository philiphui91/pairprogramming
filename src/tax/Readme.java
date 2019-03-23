package tax;

import tax.setting.ValueSetting;
import tax.test.TestMethod;

// For Tester Use
public interface Readme {

	/* 
	 * Common Value such as ID, Tax Period (YearOfAssessment), Tax Gap, Tax Rate, Standard Rate, MPF MaxDeduction, MPF MinIncome, Recommendation 
	 */
	TaxMaster taxMaster = new TaxMaster();
	
	/* Recorded 3 TaxDao (Joint Tax, Separate Husband Tax, Separate Wife Tax) 
	 * Initialize Valuable MPF Deduction for each year
	 * Initialize Valuable MPF MIN Income for each year
	 */
	Tax tax = new Tax();
	
	/*
	 * Value of Tax Income, Deduction, Allowances, Tax StandardRate, Tax ProgressiveRate, Tax Payable, MPF Deduction
	 * Calculate Net Chargeable Income(NCI), Net Income, Progress Rate Tax, Standard Rate Tax, Tax Payable, MPF Deduction
	 * ** MPF MIN deduction hard coded 7100 * 12 in Tax DAO
	 */
	TaxDao taxDao = new TaxDao();
	
	/*
	 * Input Parameter:
	 * 1. Husband Salary (Double)
	 * 2. Wife Salary (Double)
	 * 3. Year of Tax Period (Format: Y2018_19)
	 * 4. ID (String), use for identify Tax records
	 * 
	 * Progress
	 * Step 1: Call function Calculate Joint MPF, Income
	 * Step 2: Call function Calculate Separate MPF, Income
	 * Step 3a: Calculate Joint NCI
	 * Step 3b: Calculate Joint Progressive Rate Tax
	 * Step 3c: Calculate Joint Standard Rate Tax   ** Note: Add back deduction (264,000)
	 * Step 4a: Calculate Husband NCI
	 * Step 4b: Calculate Husband Progressive Rate Tax
	 * Step 4c: Calculate Husband Standard Rate Tax   ** Note: Add back deduction (132,000)
	 * Step 5a: Calculate Wife NCI
	 * Step 5b: Calculate Wife Progressive Rate Tax
	 * Step 5c: Calculate Wife Standard Rate Tax   ** Note: Add back deduction (132,000)
	 * Step 6: Compare Joint Tax and Separate Tax
	 */
	TaxUtil taxUtil = null;
	
	/*
	 * Input
	 * List Of Java Class "Tax"
	 * 
	 * Output JSON File
	 * 1. ID
	 * 2. Joint MPF Deduction
	 * 3. Separate Husband MPF Deduction
	 * 4. Separate Wife MPF Deduction
	 * 5. Joint Tax Payable
	 * 6. Separate Husband Tax Payable
	 * 7. Separate Wife Tax Payable
	 * 8. Separate Husband Tax Payable after 75% deduction (Hard coded max Deduction here)
	 * 9. Separate Wife Tax Payable after 75% deduction (Hard coded max Deduction here)
	 * 10. Recommendation (Recommend using Joint Tax of not)
	 */
	CreateJSONFile createJSONFile = null;
	
	/*
	 * Hard coded value:
	 * DeductionOfJointTax (264,000), DeductionOfSeparateTax (132,000)  
	 * In Folder Path (JSonFile), Out Folder Path (JSonFile)
	 * 
	 * Tax Gap, Tax Progressive Rate, Tax Standard Rate setting in TaxUtil.java
	 * 75 percent Deduction and max Deduction in CreateJSONFile.java  
	 */
	ValueSetting valueSetting = new ValueSetting(); 
	
	/*
	 * Input: File Name (File must under Tax.In Folder, File format: .JSON)
	 * Output: JSonFile (File will put it in Tax.Out Folder), File Name: myJSON_{YYYYMMDDHHmmsss}.json
	 * 
	 * Input File Format Example: 
	[
    	{
	        "id": "1",
	        "husband_income": "50000.0",
	        "wife_income": "0.0",
	        "assessment_year" : "Y2018_19"
    	}
    ]
	 * 
	 * Output File Format Example: (Reformat JSON please using: https://jsoneditoronline.org/) 
	 	[{"10_recommendation":"Not recommend using Joint assessment","6_separate_husband_tax_payable":"0","4_separate_wife_mpf_deduction":"0","2_joint_mpf_deduction":"0","7_separate_wife_tax_payable":"0","8_sep_husband_75%_tax_payable":"0","9_sep_wife_75%_tax_payable":"0","5_joint_tax_payable":"0","3_separate_husband_mpf_deduction":"0","1_id":"1"},{"10_recommendation":"Not recommend using Joint assessment","6_separate_husband_tax_payable":"0","4_separate_wife_mpf_deduction":"0","2_joint_mpf_deduction":"0","7_separate_wife_tax_payable":"0","8_sep_husband_75%_tax_payable":"0","9_sep_wife_75%_tax_payable":"0","5_joint_tax_payable":"0","3_separate_husband_mpf_deduction":"0","1_id":"2"}]	 
	 */
	InOutJSonFile inOutJSonFile = null;
	
	/*
	 * package: tax.test
	 * Test Starting Point (Test: Test20190322)
	 */
	TestMethod t = new TestMethod();
}
