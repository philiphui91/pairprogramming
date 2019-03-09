import java.text.NumberFormat;


public class TaxUtil{

	private Tax taxMaster = new Tax();
	public TaxUtil(Double _husbandSalary, Double _wifeSalary, YearOfAssessment _YOA) {
		
		taxMaster.setYear(_YOA);
		
		switch (_YOA) {
			case Y2012_13:
				taxMaster.setTaxGap(ValueSetting.taxGap2012_16);
				taxMaster.setTaxRate(ValueSetting.taxRate2012_16);
				taxMaster.setStandardRate(ValueSetting.standardRate2012_16);
			break;
			case Y2013_14:
				taxMaster.setTaxGap(ValueSetting.taxGap2012_16);
				taxMaster.setTaxRate(ValueSetting.taxRate2012_16);
				taxMaster.setStandardRate(ValueSetting.standardRate2012_16);
			break;
			case Y2014_15:
				taxMaster.setTaxGap(ValueSetting.taxGap2012_16);
				taxMaster.setTaxRate(ValueSetting.taxRate2012_16);
				taxMaster.setStandardRate(ValueSetting.standardRate2012_16);
			break;
			case Y2015_16:
				taxMaster.setTaxGap(ValueSetting.taxGap2012_16);
				taxMaster.setTaxRate(ValueSetting.taxRate2012_16);
				taxMaster.setStandardRate(ValueSetting.standardRate2012_16);
			break;
			case Y2016_17:
				taxMaster.setTaxGap(ValueSetting.taxGap2012_16);
				taxMaster.setTaxRate(ValueSetting.taxRate2012_16);
				taxMaster.setStandardRate(ValueSetting.standardRate2012_16);
			break;
			case Y2017_18:
				taxMaster.setTaxGap(ValueSetting.standardRate2017_18);
				taxMaster.setTaxRate(ValueSetting.taxRate2017_18);
				taxMaster.setStandardRate(ValueSetting.standardRate2017_18);
			break;
			case Y2018_19:
				taxMaster.setTaxGap(ValueSetting.standardRate2018_19);
				taxMaster.setTaxRate(ValueSetting.taxRate2018_19);
				taxMaster.setStandardRate(ValueSetting.standardRate2018_19);
			break;
			default: 
				taxMaster.setYear(ValueSetting.dafaultYear);
				taxMaster.setTaxGap(ValueSetting.defaultTaxGap);
				taxMaster.setTaxRate(ValueSetting.defaultTaxRate);
				taxMaster.setStandardRate(ValueSetting.defaultStandardRate);
			break;
		}
		
		// Case 1
//		System.out.println("Case 1 Start");
		taxMaster.taxJoint.setIncome(_husbandSalary + _wifeSalary);
		taxMaster.taxJoint.setDeduction(ValueSetting.DeductionOfJointTax);
		taxMaster.taxJoint.calMPFDeduction(taxMaster.getMPFMaxDeduction(), taxMaster.getYear());
//		System.out.println("Case 1 End");
		
		// Case 2
//		System.out.println("Case 2 Start");
		taxMaster.taxSeparateHusband.setIncome(_husbandSalary);
		taxMaster.taxSeparateHusband.calMPFDeduction(taxMaster.getMPFMaxDeduction(), taxMaster.getYear());
		taxMaster.taxSeparateHusband.setDeduction(ValueSetting.DeductionOfSeparateTax);
		taxMaster.taxSeparateWife.setIncome(_wifeSalary);
		taxMaster.taxSeparateWife.calMPFDeduction(taxMaster.getMPFMaxDeduction(), taxMaster.getYear());
		taxMaster.taxSeparateWife.setDeduction(ValueSetting.DeductionOfSeparateTax);
//		System.out.println("Case 2 End");
	}
	
	public void printJointTax() {
		System.out.println("Allowances: " + taxMaster.taxJoint.getAllowances());
		System.out.println("Deduction: " + taxMaster.taxJoint.getDeduction());
		System.out.println("TotalIncome: " + taxMaster.taxJoint.getIncome());
		Double output = taxMaster.taxJoint.calNetChargeableIncome();
		System.out.println("Net Chargeable Income: " + output);
		output = taxMaster.taxJoint.calNetIncome();
		System.out.println("Net Income: " + output);
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(1);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxMaster.taxJoint.calProgessRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate()),1));
		System.out.println("Standard Rate: " + taxMaster.RoundDouble(taxMaster.taxJoint.calStandardRateTax(taxMaster.getStandardRate()),1));
	}
	
	public void printSeparateTax() {
		System.out.println("----- Husband -----");
		printSeparateHusbandTax();
		
		System.out.println("----- Wife -----");
		printSeparateWifeTax();
		
	}
	
	public void printSeparateHusbandTax() {
		System.out.println("Allowances: " + taxMaster.taxSeparateHusband.getAllowances());
		System.out.println("Deduction: " + taxMaster.taxSeparateHusband.getDeduction());
		System.out.println("TotalIncome: " + taxMaster.taxSeparateHusband.getIncome());
		Double output = taxMaster.taxSeparateHusband.calNetChargeableIncome();
		System.out.println("Net Chargeable Income: " + output);
		output = taxMaster.taxSeparateHusband.calNetIncome();
		System.out.println("Net Income: " + output);
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(1);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxMaster.taxJoint.calProgessRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate()),1));
		System.out.println("Standard Rate: " + taxMaster.RoundDouble(taxMaster.taxJoint.calStandardRateTax(taxMaster.getStandardRate()),1));
	}
	
	public void printSeparateWifeTax() {
		System.out.println("Allowances: " + taxMaster.taxSeparateWife.getAllowances());
		System.out.println("Deduction: " + taxMaster.taxSeparateWife.getDeduction());
		System.out.println("TotalIncome: " + taxMaster.taxSeparateWife.getIncome());
		Double output = taxMaster.taxSeparateWife.calNetChargeableIncome();
		System.out.println("Net Chargeable Income: " + output);
		output = taxMaster.taxSeparateWife.calNetIncome();
		System.out.println("Net Income: " + output);
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(1);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxMaster.taxSeparateWife.calProgessRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate()),1));
		System.out.println("Standard Rate: " + taxMaster.RoundDouble(taxMaster.taxSeparateWife.calStandardRateTax(taxMaster.getStandardRate()),1));
	}
	
	public void printRecommendation() {
		System.out.println("---- recommendation -----");
		if (compareTax()) {
			System.out.println("Recommend using Joint assessment");
		} else {
			System.out.println("Recommend not using Joint assessment");
		}
	}
	
	private boolean compareTax() {
		Double taxS = taxMaster.taxSeparateHusband.calNetChargeableIncome() + taxMaster.taxSeparateWife.calNetChargeableIncome();
		Double taxJ = taxMaster.taxJoint.calNetChargeableIncome();
		
		return taxJ > taxS;
	}
	
}
