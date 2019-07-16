package tax;

import java.util.Map;

import tax.setting.YearOfAssessment;

public class TaxDao {
	
	private double income;
	
	private double deduction;
	
	private double allowances;
	
	private double taxStandardRate;
	
	private double taxProgressRate;
	
	private double taxPayable;
	
	private double mpfDeduction;

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getDeduction() {
		return deduction;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	public double getAllowances() {
		return allowances;
	}

	public void setAllowances(double allowances) {
		this.allowances = allowances;
	}
	

	public double getTaxStandardRate() {
		return taxStandardRate;
	}

	public void setTaxStandardRate(double taxStandardRate) {
		this.taxStandardRate = taxStandardRate;
	}

	public double getTaxProgressRate() {
		return taxProgressRate;
	}

	public void setTaxProgessRate(double taxProgressRate) {
		this.taxProgressRate = taxProgressRate;
	}

	public double getTaxPayable() {
		return taxPayable;
	}

	public void setTaxPayable(double taxPayable) {
		this.taxPayable = taxPayable;
	}



	public double getMpfDeduction() {
		return mpfDeduction;
	}

	public void setMpfDeduction(double mpfDeduction) {
		this.mpfDeduction = mpfDeduction;
	}

	public double calNetChargeableIncome() {
		double netChargeableIncome;
		double _totalIncome = getIncome();
		double _deductions = getDeduction();
		double _allowances = getAllowances();
		
		netChargeableIncome = _totalIncome - _deductions - _allowances;
		if (netChargeableIncome < 0.0) {
			netChargeableIncome = 0.0;
		}
		return netChargeableIncome;
	}
	
	public double calNetIncome() {
		double netIncome;
		double _totalIncome = getIncome();
		double _deductions = getDeduction();
		
		netIncome = _totalIncome - _deductions;
		if (netIncome < 0) 
			netIncome = 0.0;
		return netIncome;
	}
	
	public double calProgressiveRateTax(Double taxGap, Double[] taxRate) {
		double n = calNetChargeableIncome(); //netChargeableIncome
		double tax = 0.0;
		
		int period = 0;
		while (n > 0.0 && period < taxRate.length) {
			if (n - taxGap > 0 && period != taxRate.length - 1) {
				tax += taxGap * taxRate[period];
			} else {
				tax += n * taxRate[period];
			}
			n -= taxGap;
			period ++;
		}
		return tax;
	}
		
	public double calStandardRateTax(double standardRate) {
		Double n = calNetIncome();
		return n * standardRate;
	}
	
	public double calTaxPayable() {
		if (taxStandardRate > taxProgressRate) {
			setTaxPayable(taxProgressRate);
			return taxProgressRate;
		} else {
			setTaxPayable(taxStandardRate);
			return taxStandardRate;
		}
	}
	
	public double calMPFDeduction(Map<YearOfAssessment, Double> maxDeductionMap, YearOfAssessment year){
		double _income = getIncome();
		double mpfDeduction = _income * 0.05;
		
//		System.out.println("MPF Deduction Start");
		double mpfMaxDeduction = maxDeductionMap.get(year);
		if (mpfMaxDeduction == 0.0) {
			try {
				throw new Exception("Year of Assessment out of range");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (mpfMaxDeduction <= mpfDeduction) {
			mpfDeduction = mpfMaxDeduction;
		}
		
		Double mpfMinDeduction = 7100.0 * 12;
		if (_income < mpfMinDeduction) {
			mpfDeduction = 0.0;
		}
		
		if (getDeduction() == 0.0) 
			setDeduction(0.0);
		
		setMpfDeduction(mpfDeduction);
		setDeduction(getDeduction() + mpfDeduction);
		return mpfDeduction;
	}
	
}
