package tax;


public class TaxUtil{

	private Tax taxMaster = new Tax();
	
	public TaxUtil(Double _husbandSalary, Double _wifeSalary, YearOfAssessment _YOA, String _id) {

		taxMaster.setId(_id);
		taxMaster.setYear(_YOA);
		taxMaster.setTaxSeparateHusband(new TaxDao());
		taxMaster.setTaxSeparateWife(new TaxDao());
		taxMaster.setTaxJoint(new TaxDao());
		
		switch (_YOA) {
			case Y2012_13:
			case Y2013_14:
			case Y2014_15:
			case Y2015_16:
			case Y2016_17:
				taxMaster.setTaxGap(ValueSetting.taxGap2012_16);
				taxMaster.setTaxRate(ValueSetting.taxRate2012_16);
				taxMaster.setStandardRate(ValueSetting.standardRate2012_16);
			break;
			case Y2017_18:
				taxMaster.setTaxGap(ValueSetting.taxGap2017_18);
				taxMaster.setTaxRate(ValueSetting.taxRate2017_18);
				taxMaster.setStandardRate(ValueSetting.standardRate2017_18);
			break;
			case Y2018_19:
				taxMaster.setTaxGap(ValueSetting.taxGap2018_19);
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
		// MPF Calculation need to split up
		Double jointMpf = 0.0;
		taxMaster.getTaxJoint().setDeduction(ValueSetting.DeductionOfJointTax);
		taxMaster.getTaxJoint().setIncome(_husbandSalary);
		jointMpf += taxMaster.getTaxJoint().calMPFDeduction(taxMaster.getMPFMaxDeduction(), taxMaster.getYear());
		taxMaster.getTaxJoint().setIncome(_wifeSalary);
		jointMpf += taxMaster.getTaxJoint().calMPFDeduction(taxMaster.getMPFMaxDeduction(), taxMaster.getYear());
		taxMaster.getTaxJoint().setMpfDeduction(jointMpf);
		
		taxMaster.getTaxJoint().setIncome(_husbandSalary + _wifeSalary);
		
//		System.out.println("Case 1 End");
		
		// Case 2
//		System.out.println("Case 2 Start");
		taxMaster.getTaxSeparateHusband().setIncome(_husbandSalary);
		taxMaster.getTaxSeparateHusband().setDeduction(ValueSetting.DeductionOfSeparateTax);
		taxMaster.getTaxSeparateHusband().calMPFDeduction(taxMaster.getMPFMaxDeduction(), taxMaster.getYear());
		taxMaster.getTaxSeparateWife().setIncome(_wifeSalary);
		taxMaster.getTaxSeparateWife().setDeduction(ValueSetting.DeductionOfSeparateTax);
		taxMaster.getTaxSeparateWife().calMPFDeduction(taxMaster.getMPFMaxDeduction(), taxMaster.getYear());
//		System.out.println("Case 2 End");
		
		printAll();
		compareTax();

	}
	
	public void printAll() {
		System.out.println("ID: " + taxMaster.getId() + " ----- Joint Tax -----");
		printJointTax();
		System.out.println("ID: " + taxMaster.getId() + " ----- Separate Husband Tax -----");
		printSeparateHusbandTax();
		System.out.println("ID: " + taxMaster.getId() + " ----- Separate Wife Tax -----");
		printSeparateWifeTax();
		System.out.println("ID: " + taxMaster.getId() + " ----- Recommendation -----");
		printRecommendation();
	}
	
	public void printJointTax() {
		System.out.println("Allowances: " + taxMaster.getTaxJoint().getAllowances());
		System.out.println("Deduction: " + taxMaster.getTaxJoint().getDeduction());
		System.out.println("TotalIncome: " + taxMaster.getTaxJoint().getIncome());
		Double output = taxMaster.getTaxJoint().calNetChargeableIncome();
		System.out.println("Net Chargeable Income: " + output);
		output = taxMaster.getTaxJoint().calNetIncome();
		System.out.println("Net Income: " + output);
		
		Double taxProgessRate = taxMaster.getTaxJoint().calProgessRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate());
		Double taxStandardRate = taxMaster.getTaxJoint().calStandardRateTax(taxMaster.getStandardRate(), true);
		taxMaster.getTaxJoint().setTaxProgessRate(taxProgessRate);
		taxMaster.getTaxJoint().setTaxStandardRate(taxStandardRate);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxProgessRate,1));
		System.out.println("Standard Rate: " + taxMaster.RoundDouble(taxStandardRate,1));
	}
	
	public void printSeparateTax() {
		System.out.println("----- Husband -----");
		printSeparateHusbandTax();
		
		System.out.println("----- Wife -----");
		printSeparateWifeTax();
		
	}
	
	public void printSeparateHusbandTax() {
		System.out.println("Allowances: " + taxMaster.getTaxSeparateHusband().getAllowances());
		System.out.println("Deduction: " + taxMaster.getTaxSeparateHusband().getDeduction());
		System.out.println("TotalIncome: " + taxMaster.getTaxSeparateHusband().getIncome());
		Double output = taxMaster.getTaxSeparateHusband().calNetChargeableIncome();
		System.out.println("Net Chargeable Income: " + output);
		output = taxMaster.getTaxSeparateHusband().calNetIncome();
		System.out.println("Net Income: " + output);
		
		Double taxProgessRate = taxMaster.getTaxSeparateHusband().calProgessRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate());
		Double taxStandardRate = taxMaster.getTaxSeparateHusband().calStandardRateTax(taxMaster.getStandardRate(), false);
		taxMaster.getTaxSeparateHusband().setTaxProgessRate(taxProgessRate);
		taxMaster.getTaxSeparateHusband().setTaxStandardRate(taxStandardRate);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxProgessRate,1));
		System.out.println("Standard Rate: " + taxMaster.RoundDouble(taxStandardRate, 1));
	}
	
	public void printSeparateWifeTax() {
		System.out.println("Allowances: " + taxMaster.getTaxSeparateWife().getAllowances());
		System.out.println("Deduction: " + taxMaster.getTaxSeparateWife().getDeduction());
		System.out.println("TotalIncome: " + taxMaster.getTaxSeparateWife().getIncome());
		Double output = taxMaster.getTaxSeparateWife().calNetChargeableIncome();
		System.out.println("Net Chargeable Income: " + output);
		output = taxMaster.getTaxSeparateWife().calNetIncome();
		System.out.println("Net Income: " + output);
		
		Double taxProgessRate = taxMaster.getTaxSeparateWife().calProgessRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate());
		Double taxStandardRate = taxMaster.getTaxSeparateWife().calStandardRateTax(taxMaster.getStandardRate(), false);
		taxMaster.getTaxSeparateWife().setTaxProgessRate(taxProgessRate);
		taxMaster.getTaxSeparateWife().setTaxStandardRate(taxStandardRate);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxProgessRate ,1));
		System.out.println("Standard Rate: " + taxMaster.RoundDouble(taxStandardRate, 1));
			}
	
	public boolean printRecommendation() {
		System.out.println("---- recommendation -----");
		if (compareTax()) {
			System.out.println("Recommend using Joint assessment");
			taxMaster.setRecommendation("Recommend using Joint assessment");
			return true;
		} else {
			System.out.println("Not recommend using Joint assessment");
			taxMaster.setRecommendation("Not recommend using Joint assessment");
			return false;
		}
	}
	
	private boolean compareTax() {
		Double taxS = taxMaster.getTaxSeparateHusband().calTaxPayable() + taxMaster.getTaxSeparateWife().calTaxPayable();
		Double taxJ = taxMaster.getTaxJoint().calTaxPayable();
		
		return taxJ < taxS;
	}
	
	public Tax returnObj() {
		return taxMaster;
	}
	
}
