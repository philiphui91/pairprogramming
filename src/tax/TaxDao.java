package tax;

import java.util.Map;

import tax.setting.YearOfAssessment;

public class TaxDao {
	
	private Double income;
	
	private Double deduction;
	
	private Double allowances;
	
	private Double taxStandardRate;
	
	private Double taxProgressRate;
	
	private Double taxPayable;
	
	private Double mpfDeduction;

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getDeduction() {
		return deduction;
	}

	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}

	public Double getAllowances() {
		if (allowances == null)
			allowances = 0.0;
		return allowances;
	}

	public void setAllowances(Double allowances) {
		this.allowances = allowances;
	}
	

	public Double getTaxStandardRate() {
		return taxStandardRate;
	}

	public void setTaxStandardRate(Double taxStandardRate) {
		this.taxStandardRate = taxStandardRate;
	}

	public Double getTaxProgressRate() {
		return taxProgressRate;
	}

	public void setTaxProgessRate(Double taxProgressRate) {
		this.taxProgressRate = taxProgressRate;
	}

	public Double getTaxPayable() {
		return taxPayable;
	}

	public void setTaxPayable(Double taxPayable) {
		this.taxPayable = taxPayable;
	}



	public Double getMpfDeduction() {
		return mpfDeduction;
	}

	public void setMpfDeduction(Double mpfDeduction) {
		this.mpfDeduction = mpfDeduction;
	}

	public Double calNetChargeableIncome() {
		Double netChargeableIncome;
		Double _totalIncome = CheckNull(getIncome());
		Double _deductions = CheckNull(getDeduction());
		Double _allowances = CheckNull(getAllowances());
		
		netChargeableIncome = _totalIncome - _deductions - _allowances;
		if (netChargeableIncome < 0.0) {
			netChargeableIncome = 0.0;
		}
		return netChargeableIncome;
	}
	
	public Double calNetIncome() {
		Double netIncome;
		Double _totalIncome = CheckNull(getIncome());
		Double _deductions = CheckNull(getDeduction());
		
		netIncome = _totalIncome - _deductions;
		if (netIncome < 0) 
			netIncome = 0.0;
		return netIncome;
	}
	
	public Double calProgressiveRateTax(Double taxGap, Double[] taxRate) {
		Double n = calNetChargeableIncome(); //netChargeableIncome
		Double tax = 0.0;
		
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
		
	public Double calStandardRateTax(Double standardRate) {
		Double n = calNetIncome();
		return n * standardRate;
	}
	
	public Double calTaxPayable() {
		if (taxStandardRate > taxProgressRate) {
			setTaxPayable(taxProgressRate);
			return taxProgressRate;
		} else {
			setTaxPayable(taxStandardRate);
			return taxStandardRate;
		}
	}
	
	public Double calMPFDeduction(Map<YearOfAssessment, Double> maxDeductionMap, YearOfAssessment year){
		Double _income = CheckNull(getIncome());
		Double mpfDeduction = _income * 0.05;
		
//		System.out.println("MPF Deduction Start");
		Double mpfMaxDeduction = maxDeductionMap.get(year);
		if (mpfMaxDeduction == null) {
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
		
		if (getDeduction() == null) 
			setDeduction(0.0);
		
		setMpfDeduction(mpfDeduction);
		setDeduction(getDeduction() + mpfDeduction);
		return mpfDeduction;
	}
	
	public Double CheckNull(Double d) {
		if (d == null) {
			d = 0.0;
		}
		
		if (d < 0) {
			d = 0.0;
		}
		
		return d;
	}

}
