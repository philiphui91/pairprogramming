package tax;

import tax.setting.YearOfAssessment;

public class TaxUtil{

	private Tax taxMaster = new Tax();
	
	public TaxUtil(Double _husbandSalary, Double _wifeSalary, YearOfAssessment _YOA, String _id) {

		taxMaster.setId(_id);
		taxMaster.setYear(_YOA);
		taxMaster.setTaxSeparateHusband(new TaxDao());
		taxMaster.setTaxSeparateWife(new TaxDao());
		taxMaster.setTaxJoint(new TaxDao());
		
		//dirty code
		switch (_YOA) {
			case Y2012_13:
			case Y2013_14:
			case Y2014_15:
			case Y2015_16:
			case Y2016_17:
				taxMaster.setTaxGap(40000.0);
				taxMaster.setTaxRate(new Double[]{0.02, 0.07, 0.12, 0.17});
				taxMaster.setStandardRate(0.15);
			break;
			case Y2017_18:
				taxMaster.setTaxGap(45000.0);
				taxMaster.setTaxRate(new Double[]{0.02, 0.07, 0.12, 0.17});
				taxMaster.setStandardRate(0.15);
			break;
			case Y2018_19:
				taxMaster.setTaxGap(50000.0);
				taxMaster.setTaxRate(new Double[]{0.02, 0.06, 0.10, 0.14, 0.17});
				taxMaster.setStandardRate(0.15);
			break;
			default: 
				taxMaster.setYear(YearOfAssessment.Y2017_18);
				taxMaster.setTaxGap(45000.0);
				taxMaster.setTaxRate(new Double[]{0.02, 0.07, 0.12, 0.17});
				taxMaster.setStandardRate(0.15);
			break;
		}
		
		switch (_YOA) {
			case Y2012_13:
			case Y2013_14:
			case Y2014_15:
			case Y2015_16:
				taxMaster.setBasicAllowance(120000.0);
				taxMaster.setMarriedAllowance(240000.0);
			break;
			case Y2016_17:
			case Y2017_18:
			case Y2018_19:
				taxMaster.setBasicAllowance(132000.0);
				taxMaster.setMarriedAllowance(264000.0);
			break;
			default: 
				taxMaster.setBasicAllowance(0.0);
				taxMaster.setMarriedAllowance(0.0);
			break;
		}
		
		// Case 1
//		System.out.println("Case 1 Start");
		// MPF Calculation need to split up
		Double jointMpf = 0.0;
		taxMaster.getTaxJoint().setAllowances(taxMaster.getMarriedAllowance());
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
		taxMaster.getTaxSeparateHusband().setAllowances(taxMaster.getBasicAllowance());
		taxMaster.getTaxSeparateHusband().calMPFDeduction(taxMaster.getMPFMaxDeduction(), taxMaster.getYear());
		taxMaster.getTaxSeparateWife().setIncome(_wifeSalary);
		taxMaster.getTaxSeparateWife().setAllowances(taxMaster.getBasicAllowance());
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
		
		Double taxProgressRate = taxMaster.getTaxJoint().calProgressiveRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate());
		Double taxStandardRate = taxMaster.getTaxJoint().calStandardRateTax(taxMaster.getStandardRate());
		taxMaster.getTaxJoint().setTaxProgessRate(taxProgressRate);
		taxMaster.getTaxJoint().setTaxStandardRate(taxStandardRate);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxProgressRate,1));
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
		
		Double taxProgressRate = taxMaster.getTaxSeparateHusband().calProgressiveRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate());
		Double taxStandardRate = taxMaster.getTaxSeparateHusband().calStandardRateTax(taxMaster.getStandardRate());
		taxMaster.getTaxSeparateHusband().setTaxProgessRate(taxProgressRate);
		taxMaster.getTaxSeparateHusband().setTaxStandardRate(taxStandardRate);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxProgressRate,1));
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
		
		Double taxProgressRate = taxMaster.getTaxSeparateWife().calProgressiveRateTax(taxMaster.getTaxGap(), taxMaster.getTaxRate());
		Double taxStandardRate = taxMaster.getTaxSeparateWife().calStandardRateTax(taxMaster.getStandardRate());
		taxMaster.getTaxSeparateWife().setTaxProgessRate(taxProgressRate);
		taxMaster.getTaxSeparateWife().setTaxStandardRate(taxStandardRate);
		System.out.println("Progress Rate: " + taxMaster.RoundDouble(taxProgressRate ,1));
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
